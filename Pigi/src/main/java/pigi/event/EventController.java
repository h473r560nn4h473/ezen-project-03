package pigi.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import pigi.model.PIGI_EVENT;
import pigi.model.PIGI_POINT;
import pigi.util.Paging;

@Controller
public class EventController {

	@Resource(name="eventService")
	EventService eventService;
	
	@RequestMapping("/point.al")
	public String point(HttpServletRequest request, Model model) throws Exception {
		
		/* 현재 로그인한 사용자의 email을 읽어와서 이벤트 테이블의 정보를 읽어온다 */
		String email = (String) request.getSession().getAttribute("EMAIL");
		PIGI_EVENT eventBean = eventService.selectEventId(email);
		
		/* 페이징을 위한 변수 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countEventAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "point.al";
		String searchUrl = "";
		
		/* 검색 조건일 경우 사용 변수 */
		// 날짜는 8자리 "yyyyMMdd"
		String MINDATE = ""; // 검색 날짜 최소값
		String MAXDATE = ""; // 검색 날짜 최대값
		
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* MINDATE와 MAXDATE 계산 */
		// "maxDate" 파라미터가 null이 아니면 검색
		if(request.getParameter("maxDate")!=null) {
			int maxDateInt = Integer.parseInt(request.getParameter("maxDate"));
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
			// 오늘 날짜를 구해서 캘린더객체로 생성한 다음에
			// 오늘 날짜의 8자리 yyyyMMdd값을 구한다
			Calendar cal = Calendar.getInstance();
			
			String today = formatDate.format(cal.getTime()); // 오늘
			
			cal.add(Calendar.MONTH, -1);
			String oneMonthAgo = formatDate.format(cal.getTime()); // 1달전

			cal.add(Calendar.MONTH, -1);
			String twoMonthAgo = formatDate.format(cal.getTime()); // 2달전

			cal.add(Calendar.MONTH, -1);
			String threeMonthAgo = formatDate.format(cal.getTime()); // 3달전
			
			cal.add(Calendar.YEAR, -100);
			String stoneAge = formatDate.format(cal.getTime()); // 100년전
			
			if(maxDateInt == 0) {
				// maxDate값이 0이면 1개월전 ~ 오늘
				MAXDATE = today;
				MINDATE = oneMonthAgo;
			} else if(maxDateInt == 1) {
				// maxDate값이 1이면 2개월전 ~ 1개월전
				MAXDATE = oneMonthAgo;		
				MINDATE = twoMonthAgo;		
			} else if(maxDateInt == 2) {
				// maxDate값이 2이면 3개월전 ~ 2개월전
				MAXDATE = twoMonthAgo;		
				MINDATE = threeMonthAgo;	
			} else if(maxDateInt == 3) {
				// maxDate값이 3이면 100년전~3개월 이전
				MAXDATE = threeMonthAgo;		
				MINDATE = stoneAge;		
			}			
		}		
		
		/* 페이징을 위한 값 계산 */
		if(request.getParameter("maxDate")!= null) {
			// 조건이 있을 경우 조건 검색에 따른 수량 계산
			countEventAll = eventService.pointIdSearchCount(MINDATE, MAXDATE, email);
		} else {
			countEventAll = eventService.pointIdCount(email);
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countEventAll, pageBlock, pageSize, currentPage, url, searchUrl);

		List<PIGI_POINT> list = new ArrayList<PIGI_POINT>();
		if(request.getParameter("maxDate")!= null) {
			// 조건이 있을 경우 조건 검색
			list = eventService.pointIdListSearchPaging(email, MINDATE, MAXDATE, START, END);
		} else {
			list = eventService.pointIdListPaging(email, START, END);
		}
		
		model.addAttribute("eventBean", eventBean);
		model.addAttribute("pointList", list);
		model.addAttribute("size", list.size());
		model.addAttribute("maxDate", request.getParameter("maxDate"));
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		return "point";
	}
	
	@RequestMapping("/coupon.al")
	public String coupon(HttpServletRequest request, Model model) throws Exception {
		
		/* 현재 로그인한 사용자의 email을 읽어와서 이벤트 테이블의 정보를 읽어온다 */
		String email = (String) request.getSession().getAttribute("EMAIL");
		PIGI_EVENT eventBean = eventService.selectEventId(email);
		
		model.addAttribute("eventBean", eventBean);		
		
		return "coupon";
	}
	
	@RequestMapping("/roulette.al")
	public String roulette(HttpServletRequest request, Model model) throws Exception {
	
		/* 현재 로그인한 사용자의 email을 읽어와서 이벤트 테이블의 정보를 읽어온다 */
		String email = (String) request.getSession().getAttribute("EMAIL");
		PIGI_EVENT eventBean = eventService.selectEventId(email);
		
		model.addAttribute("eventBean", eventBean);		
		
		return "roulette";
	}
	
	// Ajax를 사용한 point 수정
	@ResponseBody
	@RequestMapping("/updatePoint.al")
	public Map<String, String> updatePoint(@RequestBody String message, PIGI_EVENT eventBean) throws Exception {
		
		Map<String, String> msg = new HashMap<String, String>();
		
		int point = eventBean.getPIGI_POINT(); // 입력한 포인트
		int updatePoint; // 수정될 포인트
		
		int gainPoint; // 획득한 포인트
		
		/* 현재 로그인한 유저의 이벤트 정보를 읽어온다 */
		String email = eventBean.getEMAIL(); // 포인트를 수정할 회원의 이메일
		PIGI_EVENT event = eventService.selectEventId(email);
		
		if((event.getPIGI_POINT() + point) >= 100000) {
			// 추가할 포인트와 기존의 포인트의 총합이 10만 이상이면 10만으로 설정
			updatePoint = 100000;
			gainPoint = 100000 - event.getPIGI_POINT();
			
			msg.put("message", "포인트는 최대 10만까지 적립이 됩니다 현재 보유 포인트 : 10만 Point");
		} else {
			// 추가할 포인트와 기존의 포인트의 총합이 10만 이하이면 포인트를 두 값의 합으로 설정
			updatePoint = event.getPIGI_POINT() + point;
			gainPoint = point;
			
			msg.put("message", "포인트를 적립하였습니다 현재 보유 포인트 :" + updatePoint + "Point");
		}
		
		eventBean.setPIGI_POINT(updatePoint); // 수정할 포인트를 가진 event 객체
		
		eventService.updatePointId(eventBean);
		
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
		
		msg.put("point", "" + point);		
		msg.put("updatePoint", "" + updatePoint);
		
		return msg;
	}

	// Ajax를 사용한 coupon 수정
	@ResponseBody
	@RequestMapping("/updateCoupon.al")
	public Map<String, String> updateCoupon(String inputCoupon , String page, PIGI_EVENT eventBean) throws Exception {
		
		Map<String, String> msg = new HashMap<String, String>();
		String message = "";
		
		/* 현재 로그인한 유저의 이벤트 정보를 읽어온다 */
		String email = eventBean.getEMAIL(); // 포인트를 수정할 회원의 이메일
		PIGI_EVENT eventInfo = eventService.selectEventId(email);
		
		/* 포인트 획득시 포인트 획득 내역을 저장하기 위해 사용할 변수들 */
		// 포인트 획득 테이블에 등록
		// EMAIL, PIGI_POINT(gainPoint), ROULETTEDATE(8글자 계산)
		PIGI_POINT pigi_point = new PIGI_POINT();
		int gainPoint = 0; // 획득한 포인트
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		// 오늘 날짜를 구해서 캘린더객체로 생성한 다음에
		// 오늘 날짜의 8자리 yyyyMMdd값을 구한다
		Calendar cal = Calendar.getInstance();
		
		String today = formatDate.format(cal.getTime()); // 오늘
		
		pigi_point.setEMAIL(email);
		pigi_point.setROULETTEDATE(today);
		
		if(inputCoupon.equals("1K")) {
			eventBean.setCOUPON1K("Y");
			
			/* 현재 유저의 1000원 쿠폰이 있을 때 */
			if(eventInfo.getCOUPON1K().equals("Y")) {
				
				if(page.equals("coupon")) {
					// 쿠폰 페이지에서 1천원 쿠폰을 또 획득했을 때
					message += "이미 1천원 쿠폰이 있습니다";
					
				} else if(page.equals("roulette")) {	
					message += "이미 1천원 쿠폰이 있습니다 포인트로 전환됩니다\n";
					
					/* 보유한 포인트 + 1000포인트가 10만이 넘었을 때*/
					if((eventInfo.getPIGI_POINT()+1000) >= 100000) {
						message += "현재 보유 포인트 : 10만 Point\n최대 보유 포인트는 10만 Point입니다";
						eventBean.setPIGI_POINT(100000);
						gainPoint = 100000 - eventInfo.getPIGI_POINT();
					} else {
						eventBean.setPIGI_POINT(eventInfo.getPIGI_POINT()+1000);
						gainPoint = 1000;
						message += "현재 보유 포인트 : " + (eventInfo.getPIGI_POINT()+1000) + " Point";
					}
									
					eventService.updatePointId(eventBean);
					
					pigi_point.setPIGI_POINT(gainPoint);
					eventService.insertPigiPointID(pigi_point);
				}
				
			/* 현재 유저의 1000원 쿠폰이 없을 때 */
			} else {
				eventBean.setCOUPON2K(eventInfo.getCOUPON2K());
				eventBean.setCOUPON3K(eventInfo.getCOUPON3K());
				eventBean.setCOUPON5K(eventInfo.getCOUPON5K());
				eventBean.setCOUPON10K(eventInfo.getCOUPON10K());
				
				eventService.updateCouponId(eventBean);
				
				message += "1천원 쿠폰이 발급되었습니다";
				
				msg.put("coupon", "1K");
			}
		} else if(inputCoupon.equals("2K")) {
			eventBean.setCOUPON2K("Y");
			
			/* 현재 유저의 2000원 쿠폰이 있을 때 */
			if(eventInfo.getCOUPON2K().equals("Y")) {
				
				if(page.equals("coupon")) {
					// 쿠폰 페이지에서 2천원 쿠폰을 또 획득했을 때
					message += "이미 2천원 쿠폰이 있습니다";
				} else if(page.equals("roulette")) {	
					message += "이미 2천원 쿠폰이 있습니다 포인트로 전환됩니다\n";
					
					/* 보유한 포인트 + 2000포인트가 10만이 넘었을 때*/
					if((eventInfo.getPIGI_POINT()+2000) >= 100000) {
						message += "현재 보유 포인트 : 10만 Point\n최대 보유 포인트는 10만 Point입니다";
						eventBean.setPIGI_POINT(100000);
						gainPoint = 100000 - eventInfo.getPIGI_POINT();
					} else {
						eventBean.setPIGI_POINT(eventInfo.getPIGI_POINT()+2000);
						message += "현재 보유 포인트 : " + (eventInfo.getPIGI_POINT()+2000) + " Point";
						gainPoint = 2000;
					}
									
					eventService.updatePointId(eventBean);
					
					pigi_point.setPIGI_POINT(gainPoint);
					eventService.insertPigiPointID(pigi_point);
				}
				
			/* 현재 유저의 2000원 쿠폰이 없을 때 */
			} else {
				eventBean.setCOUPON1K(eventInfo.getCOUPON1K());
				eventBean.setCOUPON3K(eventInfo.getCOUPON3K());
				eventBean.setCOUPON5K(eventInfo.getCOUPON5K());
				eventBean.setCOUPON10K(eventInfo.getCOUPON10K());
				
				eventService.updateCouponId(eventBean);
				
				message += "2천원 쿠폰이 발급되었습니다";
				
				msg.put("coupon", "2K");
			}
		} else if(inputCoupon.equals("3K")) {
			eventBean.setCOUPON3K("Y");
			
			/* 현재 유저의 3000원 쿠폰이 있을 때 */
			if(eventInfo.getCOUPON3K().equals("Y")) {
				message += "이미 3천원 쿠폰이 있습니다 포인트로 전환됩니다\n";
				
				/* 보유한 포인트 + 3000포인트가 10만이 넘었을 때*/
				if((eventInfo.getPIGI_POINT()+3000) >= 100000) {
					message += "현재 보유 포인트 : 10만 Point\n최대 보유 포인트는 10만 Point입니다";
					eventBean.setPIGI_POINT(100000);
					gainPoint = 100000 - eventInfo.getPIGI_POINT();
				} else {
					eventBean.setPIGI_POINT(eventInfo.getPIGI_POINT()+3000);
					message += "현재 보유 포인트 : " + (eventInfo.getPIGI_POINT()+3000) + " Point";
					gainPoint = 3000;
				}
								
				eventService.updatePointId(eventBean);
				
				pigi_point.setPIGI_POINT(gainPoint);
				eventService.insertPigiPointID(pigi_point);
				
			/* 현재 유저의 3000원 쿠폰이 없을 때 */
			} else {
				eventBean.setCOUPON1K(eventInfo.getCOUPON1K());
				eventBean.setCOUPON2K(eventInfo.getCOUPON2K());
				eventBean.setCOUPON5K(eventInfo.getCOUPON5K());
				eventBean.setCOUPON10K(eventInfo.getCOUPON10K());
				
				eventService.updateCouponId(eventBean);
				
				message += "3천원 쿠폰이 발급되었습니다";
				
				msg.put("coupon", "3K");
			}
			
		} else if(inputCoupon.equals("5K")) {
			eventBean.setCOUPON5K("Y");
			
			/* 현재 유저의 5000원 쿠폰이 있을 때 */
			if(eventInfo.getCOUPON5K().equals("Y")) {
				message += "이미 5천원 쿠폰이 있습니다 포인트로 전환됩니다\n";
				
				/* 보유한 포인트 + 5000포인트가 10만이 넘었을 때*/
				if((eventInfo.getPIGI_POINT()+5000) >= 100000) {
					message += "현재 보유 포인트 : 10만 Point\n최대 보유 포인트는 10만 Point입니다";
					eventBean.setPIGI_POINT(100000);
					gainPoint = 100000 - eventInfo.getPIGI_POINT();
				} else {
					eventBean.setPIGI_POINT(eventInfo.getPIGI_POINT()+5000);
					message += "현재 보유 포인트 : " + (eventInfo.getPIGI_POINT()+5000) + " Point";
					gainPoint = 5000;
				}
								
				eventService.updatePointId(eventBean);
				
				pigi_point.setPIGI_POINT(gainPoint);
				eventService.insertPigiPointID(pigi_point);
				
			/* 현재 유저의 5000원 쿠폰이 없을 때 */
			} else {
				eventBean.setCOUPON1K(eventInfo.getCOUPON1K());
				eventBean.setCOUPON2K(eventInfo.getCOUPON2K());
				eventBean.setCOUPON3K(eventInfo.getCOUPON3K());
				eventBean.setCOUPON10K(eventInfo.getCOUPON10K());
				
				eventService.updateCouponId(eventBean);
				
				message += "5천원 쿠폰이 발급되었습니다";
				
				msg.put("coupon", "5K");
			}
			
		} else if(inputCoupon.equals("10K")) {
			eventBean.setCOUPON10K("Y");
			
			/* 현재 유저의 10000원 쿠폰이 있을 때 */
			if(eventInfo.getCOUPON10K().equals("Y")) {
				message += "이미 1만원 쿠폰이 있습니다 포인트로 전환됩니다\n";
				
				/* 보유한 포인트 + 10000포인트가 10만이 넘었을 때*/
				if((eventInfo.getPIGI_POINT()+10000) >= 100000) {
					message += "현재 보유 포인트 : 10만 Point\n최대 보유 포인트는 10만 Point입니다";
					eventBean.setPIGI_POINT(100000);
					gainPoint = 100000 - eventInfo.getPIGI_POINT();
				} else {
					eventBean.setPIGI_POINT(eventInfo.getPIGI_POINT()+10000);
					message += "현재 보유 포인트 : " + (eventInfo.getPIGI_POINT()+10000) + " Point";
					gainPoint = 10000;
				}
								
				eventService.updatePointId(eventBean);
				
				pigi_point.setPIGI_POINT(gainPoint);
				eventService.insertPigiPointID(pigi_point);
				
			/* 현재 유저의 10000원 쿠폰이 없을 때 */
			} else {
				eventBean.setCOUPON1K(eventInfo.getCOUPON1K());
				eventBean.setCOUPON2K(eventInfo.getCOUPON2K());
				eventBean.setCOUPON3K(eventInfo.getCOUPON3K());
				eventBean.setCOUPON5K(eventInfo.getCOUPON5K());
				
				eventService.updateCouponId(eventBean);
				
				message += "1만원 쿠폰이 발급되었습니다";
				
				msg.put("coupon", "10K");
			}
		}		
		
		msg.put("message", message);
		
		return msg;
	}	
	
	// Ajax를 사용한 룰렛 사용 날짜 업데이트
	@ResponseBody
	@RequestMapping("/updateRouletteDate.al")
	public Map<String, String> updateRouletteDate(PIGI_EVENT eventBean) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 현재 로그인한 유저의 이벤트 정보를 읽어온다 */
		String email = eventBean.getEMAIL(); // 포인트를 수정할 회원의 이메일
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		// 오늘 날짜를 구해서 캘린더객체로 생성한 다음에
		// 오늘 날짜의 8자리 yyyyMMdd값을 구한다
		Calendar cal = Calendar.getInstance();
		
		String today = formatDate.format(cal.getTime()); // 오늘날짜
		
		/* 쿠폰을 획득하거나 포인트로 전환된 후에 룰렛을 돌린 날짜를 설정 */
		PIGI_EVENT dateEvent = new PIGI_EVENT();
		dateEvent.setEMAIL(email);
		dateEvent.setROULETTEDATE(today);
		eventService.updateRoulettedateId(dateEvent);
		
		return msg;
	}
	
	// Ajax를 사용해 룰렛을 돌린 날짜 리턴
	@ResponseBody
	@RequestMapping("/getRouletteDate.al")
	public Map<String, String> getRouletteDate(PIGI_EVENT eventBean) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 현재 로그인한 유저의 이벤트 정보를 읽어온다 */
		String email = eventBean.getEMAIL(); // 포인트를 수정할 회원의 이메일
		
		PIGI_EVENT eventInfo = eventService.selectEventId(email);
		
		msg.put("rouletteDate" ,eventInfo.getROULETTEDATE());
		
		return msg;
	}
}