package pigi.common.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pigi.common.basket.BasketService;
import pigi.common.order.OrderService;
import pigi.model.BasketBean;
import pigi.model.CommunityBean;
import pigi.model.ProductBean;
import pigi.util.MapToBean;
import pigi.util.Paging;

@Controller
public class ProductController {

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "basketService")
	private BasketService basketService;

	@Resource(name = "orderService")
	private OrderService orderService;

	@RequestMapping("/allList.al")
	public String allList(HttpServletRequest request, Model model) throws Exception {

		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 상품의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countProductAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "allList.al";
		String searchUrl = "";

		/* 검색 조건일 경우 사용 변수 */
		// -1, null일 경우 검색 조건에서 빠진다
		String PKIND = null; // 식자재의 종류
		int pWeightMin = -1; // 무게 최소값
		int pWeightMax = -1; // 무게 최소값
		int pPriceMin = -1; // 가격 최소값
		int pPriceMax = -1; // 가격 최대값
		String pOrder = null; // 가격순 정렬값, 최소값 정렬(ASC) ROW, 최대값 정렬(DESC) HIGH로 설정

		String PSELL = null; // 판매량 순 정렬을 할 때
		String PDATE = null; // 날짜 순 정렬을 할 때
		String searchPrint = ""; // 검색 조건이 있을 경우 출력해줄 메세지

		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}

		/* 분류 검색 조건일 경우 */
		if (request.getParameter("PKIND") != null) {
			PKIND = request.getParameter("PKIND");
			searchPrint += "분류 : " + PKIND + " ";
		}

		/* 무게 검색 조건일 경우 */
		if (request.getParameter("dMax") != null) {
			int dMax = Integer.parseInt(request.getParameter("dMax"));
			if (dMax < 4) {
				pWeightMax = (dMax + 1) * 3;
				pWeightMin = dMax * 3;
			} else {
				pWeightMax = 100;
				pWeightMin = 12;
			}
			searchPrint += "무게 : " + pWeightMin + "-" + pWeightMax + " ";
		}

		/* 가격 검색 조건일 경우 */
		if (request.getParameter("pMax") != null) {
			int pMax = Integer.parseInt(request.getParameter("pMax"));

			switch (pMax) {
			case 0:
				pPriceMin = 0;
				pPriceMax = 5000;
				break;
			case 1:
				pPriceMin = 5000;
				pPriceMax = 10000;
				break;
			case 2:
				pPriceMin = 10000;
				pPriceMax = 20000;
				break;
			case 3:
				pPriceMin = 20000;
				pPriceMax = 30000;
				break;
			case 4:
				pPriceMin = 30000;
				pPriceMax = 10000000;
				break;
			}
			searchPrint += "가격 : " + pPriceMin + "-" + pPriceMax + " ";
		}

		pOrder = request.getParameter("pOrder");
		PSELL = request.getParameter("PSELL");
		PDATE = request.getParameter("PDATE");

		/* 만약 가격순 검색/무게 검색일 경우 pOrder값을 설정해주어 정렬해준다 */
		if (request.getParameter("PKIND") != null || request.getParameter("dMax") != null
				|| request.getParameter("pMax") != null) {
			pOrder = "LOW";
		}

		/* url 값 설정 */
		if (PKIND != null) {
			searchUrl += "&PKIND=" + PKIND;
		}
		if (request.getParameter("dMax") != null) {
			searchUrl += "&dMax=" + request.getParameter("dMax");
		}
		if (request.getParameter("pMax") != null) {
			searchUrl += "&pMax=" + request.getParameter("pMax");
		}
		if (pOrder != null) {
			searchUrl += "&pOrder=" + pOrder;
		} else if (PSELL != null) {
			searchUrl += "&PSELL=" + PSELL;
		} else if (PDATE != null) {
			searchUrl += "&PDATE=" + PDATE;
		}

		/* 페이징을 위한 값 계산 */
		if (PKIND != null || pWeightMin != -1 || pWeightMax != -1 || pPriceMin != -1 || pPriceMax != -1
				|| pOrder != null || PSELL != null || PDATE != null) {
			// 조건이 있을 경우 조건 검색에 따른 수량 계산
			countProductAll = productService.allListSearchCount(PKIND, pWeightMin, pWeightMax, pPriceMin, pPriceMax);
		} else {
			countProductAll = productService.allListCount();
		}

		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countProductAll, pageBlock, pageSize, currentPage, url, searchUrl);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (PKIND != null || pWeightMin != -1 || pWeightMax != -1 || pPriceMin != -1 || pPriceMax != -1
				|| pOrder != null || PSELL != null || PDATE != null) {
			// 조건이 있을 경우 조건 검색
			list = productService.allListSearchPaging(PKIND, pWeightMin, pWeightMax, pPriceMin, pPriceMax, pOrder,
					PSELL, PDATE, START, END);
		} else {
			list = productService.allListPaging(START, END);
		}

		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		for (Map<String, Object> mapObject : list) {
			productBeanList.add(MapToBean.mapToProduct(mapObject));
		}

		model.addAttribute("productBeanList", productBeanList);

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);

		/* 검색 조건 출력을 위한 메세지 삽입 */
		model.addAttribute("searchPrint", searchPrint);

		/* 검색 체크박스 출력을 위한 조건 삽입 */
		model.addAttribute("PKIND", request.getParameter("PKIND"));
		model.addAttribute("dMax", request.getParameter("dMax"));
		model.addAttribute("pMax", request.getParameter("pMax"));

		return "allList";
	}

	@RequestMapping("/aclList.al")
	public String aclList(HttpServletRequest request, Model model) throws Exception {

		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 상품의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countProductAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "aclList.al";
		String searchUrl = "";

		/* 검색 조건일 경우 사용 변수 */
		// -1, null일 경우 검색 조건에서 빠진다
		String PKIND = null; // 식자재의 종류
		int pWeightMin = -1; // 무게 최소값
		int pWeightMax = -1; // 무게 최소값
		int pPriceMin = -1; // 가격 최소값
		int pPriceMax = -1; // 가격 최대값
		String pOrder = null; // 가격순 정렬값, 최소값 정렬(ASC) ROW, 최대값 정렬(DESC) HIGH로 설정

		String PSELL = null; // 판매량 순 정렬을 할 때
		String PDATE = null; // 날짜 순 정렬을 할 때
		String searchPrint = ""; // 검색 조건이 있을 경우 출력해줄 메세지

		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}

		/* 분류 검색 조건일 경우 */
		if (request.getParameter("PKIND") != null) {
			PKIND = request.getParameter("PKIND");
			searchPrint += "분류 : " + PKIND + " ";
		}

		/* 무게 검색 조건일 경우 */
		if (request.getParameter("dMax") != null) {
			int dMax = Integer.parseInt(request.getParameter("dMax"));
			if (dMax < 4) {
				pWeightMax = dMax * 10 + 10;
				pWeightMin = dMax * 10;
			} else {
				pWeightMax = 100;
				pWeightMin = 40;
			}
			searchPrint += "무게 : " + pWeightMin + "-" + pWeightMax + " ";
		}

		/* 가격 검색 조건일 경우 */
		if (request.getParameter("pMax") != null) {
			int pMax = Integer.parseInt(request.getParameter("pMax"));

			switch (pMax) {
			case 0:
				pPriceMin = 0;
				pPriceMax = 5000;
				break;
			case 1:
				pPriceMin = 5000;
				pPriceMax = 10000;
				break;
			case 2:
				pPriceMin = 10000;
				pPriceMax = 20000;
				break;
			case 3:
				pPriceMin = 20000;
				pPriceMax = 30000;
				break;
			case 4:
				pPriceMin = 30000;
				pPriceMax = 10000000;
				break;
			}
			searchPrint += "가격 : " + pPriceMin + "-" + pPriceMax + " ";
		}

		pOrder = request.getParameter("pOrder");
		PSELL = request.getParameter("PSELL");
		PDATE = request.getParameter("PDATE");

		/* 만약 가격순 검색/무게 검색일 경우 pOrder값을 설정해주어 정렬해준다 */
		if (request.getParameter("PKIND") != null || request.getParameter("dMax") != null
				|| request.getParameter("pMax") != null) {
			pOrder = "LOW";
		}

		/* url 값 설정 */
		if (PKIND != null) {
			searchUrl += "&PKIND=" + PKIND;
		}
		if (request.getParameter("dMax") != null) {
			searchUrl += "&dMax=" + request.getParameter("dMax");
		}
		if (request.getParameter("pMax") != null) {
			searchUrl += "&pMax=" + request.getParameter("pMax");
		}
		if (pOrder != null) {
			searchUrl += "&pOrder=" + pOrder;
		} else if (PSELL != null) {
			searchUrl += "&PSELL=" + PSELL;
		} else if (PDATE != null) {
			searchUrl += "&PDATE=" + PDATE;
		}

		/* 페이징을 위한 값 계산 */
		if (PKIND != null || pWeightMin != -1 || pWeightMax != -1 || pPriceMin != -1 || pPriceMax != -1
				|| pOrder != null || PSELL != null || PDATE != null) {
			// 조건이 있을 경우 조건 검색에 따른 수량 계산
			countProductAll = productService.aclListSearchCount(PKIND, pWeightMin, pWeightMax, pPriceMin, pPriceMax);
		} else {
			countProductAll = productService.aclListCount();
		}

		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countProductAll, pageBlock, pageSize, currentPage, url, searchUrl);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (PKIND != null || pWeightMin != -1 || pWeightMax != -1 || pPriceMin != -1 || pPriceMax != -1
				|| pOrder != null || PSELL != null || PDATE != null) {
			// 조건이 있을 경우 조건 검색
			list = productService.aclListSearchPaging(PKIND, pWeightMin, pWeightMax, pPriceMin, pPriceMax, pOrder,
					PSELL, PDATE, START, END);
		} else {
			list = productService.aclListPaging(START, END);
		}

		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		for (Map<String, Object> mapObject : list) {
			productBeanList.add(MapToBean.mapToProduct(mapObject));
		}

		model.addAttribute("productBeanList", productBeanList);

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);

		/* 검색 조건 출력을 위한 메세지 삽입 */
		model.addAttribute("searchPrint", searchPrint);

		return "aclList";
	}

	@RequestMapping("/etcList.al")
	public String etcList(HttpServletRequest request, Model model) throws Exception {

		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 상품의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countProductAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "aclList.al";
		String searchUrl = "";

		/* 검색 조건일 경우 사용 변수 */
		String pOrder = null; // 가격순 정렬값, 최소값 정렬(ASC) ROW, 최대값 정렬(DESC) HIGH로 설정
		String PSELL = null; // 판매량 순 정렬을 할 때
		String PDATE = null; // 날짜 순 정렬을 할 때

		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}

		pOrder = request.getParameter("pOrder");
		PSELL = request.getParameter("PSELL");
		PDATE = request.getParameter("PDATE");

		/* url 값 설정 */
		if (pOrder != null) {
			searchUrl += "&pOrder=" + pOrder;
		} else if (PSELL != null) {
			searchUrl += "&PSELL=" + PSELL;
		} else if (PDATE != null) {
			searchUrl += "&PDATE=" + PDATE;
		}

		/* 페이징을 위한 값 계산 */
		countProductAll = productService.etcListCount();

		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countProductAll, pageBlock, pageSize, currentPage, url, searchUrl);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (pOrder != null || PSELL != null || PDATE != null) {
			// 조건이 있을 경우 조건 검색
			list = productService.etcListSearchPaging(pOrder, PSELL, PDATE, START, END);
		} else {
			list = productService.etcListPaging(START, END);
		}

		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		for (Map<String, Object> mapObject : list) {
			productBeanList.add(MapToBean.mapToProduct(mapObject));
		}

		model.addAttribute("productBeanList", productBeanList);

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);

		return "etcList";
	}

	@RequestMapping("/pDetail.al") 
	public String pDetail(ProductBean product, CommunityBean community, Model model) throws Exception {

		Map<String, Object> map = productService.selectProductId(product);
		ProductBean productBean = new ProductBean();

		productBean = MapToBean.mapToProduct(map);

		List<Map<String, Object>> list = productService.selectReviewProduct(community);
		List<CommunityBean> reviewBeanList = new ArrayList<CommunityBean>();

		for (Map<String, Object> mapObject : list) {
			reviewBeanList.add(MapToBean.mapToCommunity(mapObject));
		}

		model.addAttribute("productBean", productBean);
		model.addAttribute("reviewBeanList", reviewBeanList);

		return "pDetailAjax";
		// return "pDetail";
	}

	@RequestMapping(value = "/putBasket.al")
	public String putBasket(BasketBean basket, HttpServletRequest request, Model model) throws Exception {

		// BID와 BCOUNT를 파라미터로 넘겨받음
		// BID를 이용해서 상품 정보를 읽어옴
		ProductBean pro = new ProductBean();
		pro.setPID(basket.getBID()); // BID의 값을 PID로 갖는 상품 객체
		Map<String, Object> mapProduct = productService.selectProductId(pro); // BID값을 이용하여 DB에서 MAP객체를 가져옴
		ProductBean productInfo = MapToBean.mapToProduct(mapProduct); // MAP객체를 상품 객체로 변환, PID값이 BID인 상품 정보를 갖고 있다

		// 장바구니에 담을 수량이 상품의 재고 수량보다 많을 경우 오류 메세지 출력 후 돌아간다
		if (basket.getBCOUNT() > productInfo.getPSTOCK()) {
			model.addAttribute("msg", "재고 수량보다 많은 상품을 장바구니에 담을 수 없습니다");
			String urlParam = "/pDetail.al?PID=" + basket.getBID();
			model.addAttribute("url", urlParam);
			return "/product/putBasket";
		}
		// 장바구니에 담을 수량이 0개 이하일 경우 오류 메세지 출력 후 돌아간다
		if (basket.getBCOUNT() <= 0) {
			model.addAttribute("msg", "장바구니에 상품을 담을 수 없습니다");
			String urlParam = "/pDetail.al?PID=" + basket.getBID();
			model.addAttribute("url", urlParam);
			return "/product/putBasket";
		}

		// 상품에 입력할 EMAIL값은 세션으로부터 읽어온다
		String email = (String) request.getSession().getAttribute("EMAIL");
		basket.setBEMAIL(email);

		// 만약 장바구니에 아무것도 담겨져 있지 않다면 BNUMBER = 장바구니 테이블에서 BNUMBER의 최대값 + 1
		Map<String, Object> maxMap = productService.selectBasketBnumberMaxBemail(basket);
		int newBnumber = 0;
		if (maxMap == null) {
			newBnumber = orderService.selectOrderOBnumberMax() + 1;
		} else {
			// 내 장바구니에 상품이 담겨져있다면 BNUMBER = 내 장바구니의 BNUMBER
			newBnumber = Integer.parseInt(String.valueOf(maxMap.get("MAX")));
		}
		basket.setBNUMBER(newBnumber);

		// 만약 장바구니에 이미 상품이 존재한다면 상품의 수량만 변경한다
		// 이 때 합계 상품 수량이 재고 수량보다 많을 경우 오류 메시지 출력 후 돌아간다
		Map<String, Object> selectBasketMap = productService.selectBasketBID(basket);
		/* 장바구니에 같은 상품이 담겨 있을 경우 */
		if (selectBasketMap != null) {
			BasketBean selectBasket = MapToBean.mapToBasket(selectBasketMap);

			// 장바구니에 담을 수량과 기존 담겨있던 수량의 합이 상품의 재고 수량보다 많을 경우 오류 메세지 출력 후 돌아간다
			if ((basket.getBCOUNT() + selectBasket.getBCOUNT()) > productInfo.getPSTOCK()) {
				model.addAttribute("msg", "재고 수량보다 많은 상품을 장바구니에 담을 수 없습니다");
				String urlParam = "/pDetail.al?PID=" + basket.getBID();
				model.addAttribute("url", urlParam);
				return "/product/putBasket";
			}

			// 장바구니 수량 수정에 BCOUNT와 BNUMBER 필요
			// BCOUNT는 파라미터로 넘겨받은 bakset의 BCOUNT와 selectBasket의 BCOUNT를 합한다
			basket.setBCOUNT(basket.getBCOUNT() + selectBasket.getBCOUNT());

			// BIDX는 검색한 selectBasket의 BIDX를 사용
			basket.setBIDX(selectBasket.getBIDX());

			basketService.updateBasket(basket);
			/* 장바구니에 같은 상품이 없을 경우 */
		} else { // 장바구니에 해당 상품이 존재하지 않음.
			basket.setBNAME(productInfo.getPNAME());
			basket.setBPRICE(productInfo.getPPRICE());
			basket.setBSALE(productInfo.getPSALE());

			productService.insertBasket(basket);
		}

		model.addAttribute("msg", "장바구니에 넣었습니다");

		/* list parameter를 넘겨받았을 경우는 list에서 직접 한개의 상품을 넣어줌 */
		// 이 경우 리스트로 돌아가게 url을 설정
		// all : allList, acl : aclList, etc : etcList
		String urlBack = request.getParameter("list");
		if (urlBack == null) {
			// /pDetail.al은 파라미터값을 필요로 하기 때문에 url에 파라미터를 추가
			String urlParam = "/pDetail.al?PID=" + basket.getBID();
			model.addAttribute("url", urlParam);
		} else if (urlBack != null && urlBack.equals("all")) {
			// allList.al로 보내준다
			model.addAttribute("url", "/allList.al");
		} else if (urlBack != null && urlBack.equals("acl")) {
			// aclList.al로 보내준다
			model.addAttribute("url", "/aclList.al");
		} else if (urlBack != null && urlBack.equals("etc")) {
			// etcList.al로 보내준다
			model.addAttribute("url", "/etcList.al");
		}

		return "/product/putBasket";
	}

	@ResponseBody
	@RequestMapping(value = "/putBasketAjax.al")
	public Map<String, String> putBasketAjax(BasketBean basket, HttpServletRequest request,
			@RequestBody String message) throws Exception {
		
		Map<String, String> msg = new HashMap<String, String>();
		
		// BID와 BCOUNT를 파라미터로 넘겨받음
		// BID를 이용해서 상품 정보를 읽어옴
		ProductBean pro = new ProductBean();
		pro.setPID(basket.getBID()); // BID의 값을 PID로 갖는 상품 객체
		Map<String, Object> mapProduct = productService.selectProductId(pro); // BID값을 이용하여 DB에서 MAP객체를 가져옴
		ProductBean productInfo = MapToBean.mapToProduct(mapProduct); // MAP객체를 상품 객체로 변환, PID값이 BID인 상품 정보를 갖고 있다
		
		// 장바구니에 담을 수량이 상품의 재고 수량보다 많을 경우 오류 메세지 출력 후 돌아간다
		if (basket.getBCOUNT() > productInfo.getPSTOCK()) {
			msg.put("message", "재고 수량보다 많은 상품을 장바구니에 담을 수 없습니다");

			return msg;
		}
		
		// 장바구니에 담을 수량이 0개 이하일 경우 오류 메세지 출력 후 돌아간다
		if (basket.getBCOUNT() <= 0) {
			msg.put("message", "장바구니에 상품을 담을 수 없습니다");
			
			return msg;
		}

		// 상품에 입력할 EMAIL값은 세션으로부터 읽어온다
		String email = (String) request.getSession().getAttribute("EMAIL");
		basket.setBEMAIL(email);

		// 만약 장바구니에 아무것도 담겨져 있지 않다면 BNUMBER = 장바구니 테이블에서 BNUMBER의 최대값 + 1
		Map<String, Object> maxMap = productService.selectBasketBnumberMaxBemail(basket);
		int newBnumber = 0;
		if (maxMap == null) {
			newBnumber = orderService.selectOrderOBnumberMax() + 1;
		} else {
			// 내 장바구니에 상품이 담겨져있다면 BNUMBER = 내 장바구니의 BNUMBER
			newBnumber = Integer.parseInt(String.valueOf(maxMap.get("MAX")));
		}
		basket.setBNUMBER(newBnumber);

		// 만약 장바구니에 이미 상품이 존재한다면 상품의 수량만 변경한다
		// 이 때 합계 상품 수량이 재고 수량보다 많을 경우 오류 메시지 출력 후 돌아간다
		Map<String, Object> selectBasketMap = productService.selectBasketBID(basket);
		/* 장바구니에 같은 상품이 담겨 있을 경우 */
		if (selectBasketMap != null) {
			BasketBean selectBasket = MapToBean.mapToBasket(selectBasketMap);

			// 장바구니에 담을 수량과 기존 담겨있던 수량의 합이 상품의 재고 수량보다 많을 경우 오류 메세지 출력 후 돌아간다
			if ((basket.getBCOUNT() + selectBasket.getBCOUNT()) > productInfo.getPSTOCK()) {
				msg.put("message", "재고 수량보다 많은 상품을 장바구니에 담을 수 없습니다");
				
				return msg;
			}

			// 장바구니 수량 수정에 BCOUNT와 BNUMBER 필요
			// BCOUNT는 파라미터로 넘겨받은 bakset의 BCOUNT와 selectBasket의 BCOUNT를 합한다
			basket.setBCOUNT(basket.getBCOUNT() + selectBasket.getBCOUNT());

			// BIDX는 검색한 selectBasket의 BIDX를 사용
			basket.setBIDX(selectBasket.getBIDX());

			basketService.updateBasket(basket);
			/* 장바구니에 같은 상품이 없을 경우 */
		} else { // 장바구니에 해당 상품이 존재하지 않음.
			basket.setBNAME(productInfo.getPNAME());
			basket.setBPRICE(productInfo.getPPRICE());
			basket.setBSALE(productInfo.getPSALE());

			productService.insertBasket(basket);
		}

		msg.put("message", "장바구니에 넣었습니다");

		return msg;
	}
		

		

	@RequestMapping("/pReviewForm.al")
	public String pReviewForm(HttpServletRequest request, Model model) throws Exception {

		int PID = Integer.parseInt(request.getParameter("PID"));
		String CWRITER = (String) request.getSession().getAttribute("EMAIL");

		// 상품 정보를 출력하기 위한 PID의 상품 정보를 가져옴
		ProductBean pro = new ProductBean();
		pro.setPID(PID); // PID만를 갖는 상품 객체
		Map<String, Object> map = productService.selectProductId(pro);
		ProductBean productBean = MapToBean.mapToProduct(map);

		model.addAttribute("PID", PID);
		model.addAttribute("CWRITER", CWRITER);
		model.addAttribute("productBean", productBean);

		return "pReviewForm";
	}

	@RequestMapping("/pReview.al")
	public String pReview(CommunityBean community, HttpServletRequest request, Model model) throws Exception {

		productService.insertReview(community);

		model.addAttribute("msg", "후기를 등록하였습니다");
		String urlParam = "/pDetail.al?PID=" + request.getParameter("PID");
		model.addAttribute("url", urlParam);

		return "/product/pReview";
	}

}