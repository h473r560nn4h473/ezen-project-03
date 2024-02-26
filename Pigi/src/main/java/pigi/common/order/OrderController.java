package pigi.common.order;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pigi.admin.product.AdminProductService;
import pigi.common.basket.BasketService;
import pigi.common.member.MyInfoService;
import pigi.common.product.ProductService;
import pigi.event.EventService;
import pigi.model.MemberBean;
import pigi.model.OrderBean;
import pigi.model.Payment;
import pigi.model.ProductBean;
import pigi.model.BasketBean;
import pigi.model.PIGI_EVENT;
import pigi.model.PIGI_POINT;
import pigi.util.MapToBean;

@Controller
public class OrderController {
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="adminProductService")
	private AdminProductService adminProductService;
	
	@Resource(name="myInfoService")
	private MyInfoService myInfoService;
	
	@Resource(name="basketService")
	private BasketService basketService;
	
	@Resource(name = "eventService")
	private EventService eventService;	
	
	@RequestMapping("/pOrderForm.al")
	public String pOrderForm (ProductBean product, HttpServletRequest request, Model model) throws Exception {
		/* PID를 넘겨받아 상품 정보를 읽어옴 */
		Map<String, Object> pMap = new HashMap<String, Object>();
		ProductBean productBean = new ProductBean();
		pMap = productService.selectProductId(product);
		productBean = MapToBean.mapToProduct(pMap);
		
		/* 로그인한 사용자의 정보를 세션을 이용해서 읽어옴 */
		String loginEmail = (String) request.getSession().getAttribute("EMAIL");
		MemberBean member = new MemberBean();
		member.setEMAIL(loginEmail);
		Map<String, Object> mMap = new HashMap<String, Object>();
		mMap = myInfoService.selectMemberId(member);
		MemberBean memberBean = MapToBean.mapToMember(mMap); 
		
		/* 주문 수량 정보를 읽어옴 */
		String PCOUNT = request.getParameter("PCOUNT");
		
		/* 이벤트 정보를 읽어옴 */
		PIGI_EVENT eventBean = eventService.selectEventId(loginEmail);
		
		model.addAttribute("productBean", productBean);
		model.addAttribute("memberBean", memberBean);
		model.addAttribute("PCOUNT", PCOUNT);
		model.addAttribute("eventBean", eventBean);
		
		return "pOrderForm";
	}
	
	
	@RequestMapping("/basketOrderForm.al")
	public String basketOrderForm (BasketBean basket,HttpServletRequest request, Model model) throws Exception {
		
		List<Map<String, Object>> list = basketService.basketList(basket); 
		List<BasketBean> basketBeanList = new ArrayList<BasketBean>();
		for(Map<String, Object> mapObject : list) {
			basketBeanList.add(MapToBean.mapToBasket(mapObject));
		} 
		
		List<ProductBean> proInfoList = new ArrayList<ProductBean>();
		for(BasketBean basPro : basketBeanList) {
			
			ProductBean pro = new ProductBean();
			pro.setPID(basPro.getBID());			
			Map<String, Object> proInfoMap = productService.selectProductId(pro);
			ProductBean proInfo = MapToBean.mapToProduct(proInfoMap);
			
			proInfoList.add(proInfo);			
		}

		String loginEmail = (String) request.getSession().getAttribute("EMAIL");
		MemberBean member = new MemberBean();
		member.setEMAIL(loginEmail);
		Map<String, Object> mMap = new HashMap<String, Object>();
		mMap = myInfoService.selectMemberId(member);
		MemberBean memberBean = MapToBean.mapToMember(mMap);
		
		String BCOUNT = request.getParameter("BCOUNT");
		
		/* 이벤트 정보를 읽어옴 */
		PIGI_EVENT eventBean = eventService.selectEventId(loginEmail);
		
		model.addAttribute("basketBeanList",basketBeanList);
		model.addAttribute("Size", basketBeanList.size());
		model.addAttribute("memberBean", memberBean);
		model.addAttribute("proInfoList", proInfoList);
		model.addAttribute("BCOUNT", BCOUNT);
		model.addAttribute("eventBean", eventBean);
		
		return "basketOrderForm";
	}
	
	@RequestMapping("/pOrder.al")
	public String pOrder(HttpServletRequest request, OrderBean order, Model model) throws Exception {
		
		orderService.insertOrderDirect(order);
		
		/* 주문 후 주문한 수량만큼 product의 stock을 감소 : 재고 감소 */
		// OPID = PID이므로 OPID를 이용해 상품 정보를 읽어온다
		ProductBean pro = new ProductBean();
		pro.setPID(order.getOPID());
		Map<String, Object> proMap = productService.selectProductId(pro);
		ProductBean infoPro = MapToBean.mapToProduct(proMap); // infoPro는 PID의 값을 가지는 상품 정보
		
		// 읽어온 상품 정보의 stock을 변경
		// infoPro의 PSTOCK - order의 OCOUNT
		int changedStock = infoPro.getPSTOCK() - order.getOCOUNT();
		infoPro.setPSTOCK(changedStock);
		
		// 상품의 판매량을 증가
		int updateSell = infoPro.getPSELL() + order.getOCOUNT();
		infoPro.setPSELL(updateSell);

		// 바뀐 상품 정보를 이용해서 상품 정보를 업데이트
		adminProductService.updateProduct(infoPro);
		
		/* 입력한 주문 번호를 구한다 */
		int OID = orderService.selectOIDMax();
		
		/* 쿠폰/포인트 사용 처리 */
		int point = 0; // 포인트
		if(request.getParameter("point")!=null && !request.getParameter("point").equals("")) {
			point = Integer.parseInt(request.getParameter("point"));	
		}
		String coupon = request.getParameter("coupon"); // 쿠폰 
		int TOTALSUM = Integer.parseInt(request.getParameter("totalPrice")); // 주문 금액
		int saled = Integer.parseInt(request.getParameter("saled")); // 할인 금액
		
		// 사용자의 이벤트 테이블 정보를 가져온다
		String email = (String) request.getSession().getAttribute("EMAIL");
		PIGI_EVENT eventInfo = eventService.selectEventId(email);
		
		PIGI_EVENT updateEvent = eventInfo; // 업데이트에 사용할 새 객체 선언
		// 1. 쿠폰을 사용했으면 쿠폰 내역을 업데이트
		int couponValue = 0;
		if(coupon != null) {
			if(coupon.equals("1K")) {
				updateEvent.setCOUPON1K("N");
				couponValue = 1000;
			} else if(coupon.equals("2K")) {
				updateEvent.setCOUPON2K("N");
				couponValue = 2000;
			} else if(coupon.equals("3K")) {
				updateEvent.setCOUPON3K("N");
				couponValue = 3000;
			} else if(coupon.equals("5K")) {
				updateEvent.setCOUPON5K("N");
				couponValue = 5000;
			} else if(coupon.equals("10K")) {
				updateEvent.setCOUPON10K("N");
				couponValue = 10000;
			} 
		}
		eventService.updateCouponId(updateEvent);
		
		// 2. 포인트를 사용했으면 포인트 내역을 업데이트
		updateEvent.setPIGI_POINT(eventInfo.getPIGI_POINT()-point);
		eventService.updatePointId(updateEvent);		
		
		// 3. 결제 테이블 등록 OID, OBNUMBER = null, TOTALSUM, TOTALPAYMENT, COUPON, POINT
		int OBNUMBER = -1; // 직접 주문이기 때문에 장바구니 번호는 null
		/* 총 금액 : totalPrice(주문금액) - saled(할인금액) - eventValue(쿠폰/포인트) + 3000(배송비) */
		int TOTALPAYMENT = TOTALSUM - saled - couponValue - point + 3000;
		
		Payment payment = new Payment();
		payment.setOID(OID);
		payment.setOBNUMBER(OBNUMBER);
		payment.setTOTALSUM(TOTALSUM);
		payment.setTOTALPAYMENT(TOTALPAYMENT);
		payment.setCOUPON(coupon);
		payment.setPOINT(point);
		
		eventService.insertPayment(payment);
		
		// 4. 포인트 획득 처리
		int gainPoint = (int) ((double) TOTALPAYMENT * 0.01);
		
		// 포인트 업데이트
		PIGI_EVENT newEvent = new PIGI_EVENT(); // 포인트 업데이트에 사용할 새 객체 선언
		// 기존 보유 포인트 + 획득 포인트가 10만 이상이면 10만으로 설정
		if((eventInfo.getPIGI_POINT() + gainPoint) > 100000) {
			newEvent.setPIGI_POINT(100000);
		} else {
			newEvent.setPIGI_POINT(eventInfo.getPIGI_POINT() + gainPoint);
		}
		newEvent.setEMAIL(email);
		eventService.updatePointId(newEvent);
		
		// 포인트 획득 테이블에 등록
		// EMAIL, PIGI_POINT(gainPoint), ROULETTEDATE(8글자 계산)
		PIGI_POINT pigi_point = new PIGI_POINT();
		
		pigi_point.setEMAIL(email);
		pigi_point.setPIGI_POINT(gainPoint);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		// 오늘 날짜를 구해서 캘린더객체로 생성한 다음에
		// 오늘 날짜의 8자리 yyyyMMdd값을 구한다
		Calendar cal = Calendar.getInstance();
		
		String today = formatDate.format(cal.getTime()); // 오늘
		
		pigi_point.setROULETTEDATE(today);
				
		eventService.insertPigiPointID(pigi_point);
		
		model.addAttribute("msg", "주문을 완료했습니다");
		
		String urlParam = "/orderResult.al?OID=" + OID;
		model.addAttribute("url", urlParam);
		
		return "/order/pOrder";
	}	
	
	@RequestMapping("/basketOrder.al")
	public String basksetOrder (OrderBean order, HttpServletRequest request,
			Model model) throws Exception {
		// 사용자 이메일을 세션으로 가져온다
		BasketBean basket = new BasketBean();
		String email = (String) request.getSession().getAttribute("EMAIL");
		basket.setBEMAIL(email);
		
		// 장바구니 리스트를 사용자 이메일을 이용해서 가져온다
		List<Map<String,Object>> list = basketService.basketList(basket);
		List<BasketBean> basketBeanList = new ArrayList<BasketBean>();
		  
		for(Map<String, Object> mapObject : list) {
			basketBeanList.add(MapToBean.mapToBasket(mapObject));
		}
		
		// 장바구니 리스트의 크기만큼 루프를 돌면서 주문
		// orderBean에는 주문 정보가 들어있으므로 orderBean을 사용해서 insertOrderBasket
		
		int TOTALSUM = 0; // 주문 금액
		int saled = 0; // 할인 금액
		
		boolean checkOBNUMBER = false;
		int OBNUMBER = -1; 
		for(BasketBean infoBasket :	basketBeanList) {
		  
			if(checkOBNUMBER == false) {
				OBNUMBER= infoBasket.getBNUMBER();
				checkOBNUMBER= true;
			}
		 		
			// 3. 장바구니 리스트의 정보를 insert를 할 때 사용할 orderBean에 입력 - 컨트롤러
			// BNUMBER->OBNUMBER, BNAME-> OPRODUCT, BID-> OPID, BPRICE-> OPRICE
			// BSALE->OSALE, BEMAIL->OMAIL, BCOUNT->OCOUNT
		
			order.setOBNUMBER(infoBasket.getBNUMBER());
			order.setOPRODUCT(infoBasket.getBNAME()); 
			order.setOPID(infoBasket.getBID());
			order.setOPRICE(infoBasket.getBPRICE());
			order.setOSALE(infoBasket.getBSALE());
			order.setOMAIL(infoBasket.getBEMAIL());
			order.setOCOUNT(infoBasket.getBCOUNT());
			
			/* 총 주문금액/할인가격에 각각의 장바구니 주문금액/할인가격을 더함 */
			TOTALSUM += order.getOPRICE() * order.getOCOUNT(); 
			saled += (order.getOPRICE() * order.getOSALE() / 100) * order.getOCOUNT(); 

			// 장바구니 주문 폼에서 넘어오는 값들 (따로 설정할 필요없음) - jsp
			// ONAME:받는사람정보, OTOTAL:상품가격*수량, OMOBILE, OPOSTCODE, OADDRESS1, OADDRESS2
			// orderBean에는 주문 정보가 들어있으므로 
			// orderBean을 사용해서 insertOrderBasket
			orderService.insertOrderBasket(order); 
			
			/* 주문 후 주문한 수량만큼 product의 stock을 감소 : 재고 감소 */
			// OPID = PID이므로 OPID를 이용해 상품 정보를 읽어온다
			ProductBean pro = new ProductBean();
			pro.setPID(order.getOPID());
			Map<String, Object> proMap = productService.selectProductId(pro);
			ProductBean infoPro = MapToBean.mapToProduct(proMap); // infoPro는 PID의 값을 가지는 상품 정보

			// 읽어온 상품 정보의 stock을 변경
			// infoPro의 PSTOCK - order의 OCOUNT
			int changedStock = infoPro.getPSTOCK() - order.getOCOUNT();
			infoPro.setPSTOCK(changedStock);
			
			// 상품의 판매량을 증가
			int updateSell = infoPro.getPSELL() + order.getOCOUNT();
			infoPro.setPSELL(updateSell);
			
			// 바뀐 상품 정보를 이용해서 상품 정보를 업데이트
			adminProductService.updateProduct(infoPro);
		}
		
		/* 쿠폰/포인트 사용 처리 */
		int point = 0; // 포인트
		if(request.getParameter("point")!=null && !request.getParameter("point").equals("")) {
			point = Integer.parseInt(request.getParameter("point"));	
		}
		String coupon = request.getParameter("coupon"); // 쿠폰 
		
		// 사용자의 이벤트 테이블 정보를 가져온다
		PIGI_EVENT eventInfo = eventService.selectEventId(email);
		
		PIGI_EVENT updateEvent = eventInfo; // 업데이트에 사용할 새 객체 선언
		// 1. 쿠폰을 사용했으면 쿠폰 내역을 업데이트
		int couponValue = 0;
		if(coupon != null) {
			if(coupon.equals("1K")) {
				updateEvent.setCOUPON1K("N");
				couponValue = 1000;
			} else if(coupon.equals("2K")) {
				updateEvent.setCOUPON2K("N");
				couponValue = 2000;
			} else if(coupon.equals("3K")) {
				updateEvent.setCOUPON3K("N");
				couponValue = 3000;
			} else if(coupon.equals("5K")) {
				updateEvent.setCOUPON5K("N");
				couponValue = 5000;
			} else if(coupon.equals("10K")) {
				updateEvent.setCOUPON10K("N");
				couponValue = 10000;
			} 
		}
		eventService.updateCouponId(updateEvent);
		
		// 2. 포인트를 사용했으면 포인트 내역을 업데이트
		updateEvent.setPIGI_POINT(eventInfo.getPIGI_POINT()-point);
		eventService.updatePointId(updateEvent);		
		
		// 3. 결제 테이블 등록 OID = null, OBNUMBER, TOTALSUM, TOTALPAYMENT, COUPON, POINT
		int OID = -1; // 직접 주문이기 때문에 장바구니 번호는 null
		/* 총 금액 : totalPrice(주문금액) - saled(할인금액) - eventValue(쿠폰/포인트) + 3000(배송비) */
		int TOTALPAYMENT = TOTALSUM - saled - couponValue - point + 3000;
		
		Payment payment = new Payment();
		payment.setOID(OID);
		payment.setOBNUMBER(OBNUMBER);
		payment.setTOTALSUM(TOTALSUM);
		payment.setTOTALPAYMENT(TOTALPAYMENT);
		payment.setCOUPON(coupon);
		payment.setPOINT(point);
		
		eventService.insertPayment(payment);
		
		// 4. 포인트 획득 처리
		int gainPoint = (int) ((double) TOTALPAYMENT * 0.01);
		
		// 포인트 업데이트
		PIGI_EVENT newEvent = new PIGI_EVENT(); // 포인트 업데이트에 사용할 새 객체 선언
		// 기존 보유 포인트 + 획득 포인트가 10만 이상이면 10만으로 설정
		if((eventInfo.getPIGI_POINT() + gainPoint) > 100000) {
			newEvent.setPIGI_POINT(100000);
		} else {
			newEvent.setPIGI_POINT(eventInfo.getPIGI_POINT() + gainPoint);
		}
		newEvent.setEMAIL(email);
		eventService.updatePointId(newEvent);
		
		// 포인트 획득 테이블에 등록
		// EMAIL, PIGI_POINT(gainPoint), ROULETTEDATE(8글자 계산)
		PIGI_POINT pigi_point = new PIGI_POINT();
		
		pigi_point.setEMAIL(email);
		pigi_point.setPIGI_POINT(gainPoint);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		// 오늘 날짜를 구해서 캘린더객체로 생성한 다음에
		// 오늘 날짜의 8자리 yyyyMMdd값을 구한다
		Calendar cal = Calendar.getInstance();
		
		String today = formatDate.format(cal.getTime()); // 오늘
		
		pigi_point.setROULETTEDATE(today);
				
		eventService.insertPigiPointID(pigi_point);
		
		model.addAttribute("msg", "주문을 완료했습니다");
		String urlParam = "/orderResult.al?OBNUMBER=" + OBNUMBER;
		model.addAttribute("url", urlParam);
			 
		return "/order/basketOrder";
	}
	
	@RequestMapping("/orderResult.al")
	public String orderResult (HttpServletRequest request, 
			OrderBean order, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		OrderBean orderBean = new OrderBean();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
		
		/* 주문을 통해 최근 적립된 포인트 내역 */ 
		String email = (String) request.getSession().getAttribute("EMAIL");
		PIGI_POINT pigi_point = eventService.selectLastPigiPointID(email);
		
		model.addAttribute("pigi_point", pigi_point);		
		
		if(order.getOBNUMBER() == 0) {
			// 만약 OBNUMBER가 null일 경우 직접 주문의 결과
			// 파라미터로 넘겨받은 OID를 이용해서 주문정보를 구한다
			map = orderService.selectOrderOId(order);
			orderBean  = MapToBean.mapToOrder(map);
			
			/* 주문정보의 OMAIL을 이용해서 주문자 정보를 구함*/
			MemberBean member = new MemberBean();
			member.setEMAIL(orderBean.getOMAIL());
			Map<String, Object> mMap = new HashMap<String, Object>();
			mMap = myInfoService.selectMemberId(member);
			MemberBean memberBean = MapToBean.mapToMember(mMap); 			
			
			model.addAttribute("result","direct");
			model.addAttribute("orderBean", orderBean);
			model.addAttribute("memberBean", memberBean);			
		} else {
			// OBNUMBER가 null이 아닐 경우 장바구니 주문의 결과
			// 파라미터로 넘겨받은 OBNUMBER를 이용해 주문정보의 리스트를 구한다
			list = orderService.selectOrderOBNumber(order);
			for(Map<String, Object> mapToOrder : list) {
				orderBeanList.add(MapToBean.mapToOrder(mapToOrder));
			}
			
			/* orderBeanList의 첫번째 주문정보의 OMAIL을 이용해서 주문자 정보를 구함 */
			MemberBean member = new MemberBean();
			member.setEMAIL(orderBeanList.get(0).getOMAIL());
			Map<String, Object> mMap = new HashMap<String, Object>();
			mMap = myInfoService.selectMemberId(member);
			MemberBean memberBean = MapToBean.mapToMember(mMap);
			
			model.addAttribute("result","basket");
			model.addAttribute("orderBeanList", orderBeanList);
			model.addAttribute("memberBean", memberBean);			
		}		
		return "orderResult";
	}
}