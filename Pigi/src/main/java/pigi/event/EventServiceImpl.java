package pigi.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.PIGI_EVENT;
import pigi.model.PIGI_POINT;
import pigi.model.Payment;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Resource(name="eventDAO")
	EventDAO eventDAO;
	
	@Override
	public PIGI_EVENT selectEventId(String email) throws Exception {
		return eventDAO.selectEventId(email);
	}

	@Override
	public List<PIGI_POINT> pointIdListPaging(String email, int START, int END) throws Exception {
		List<PIGI_POINT> list = new ArrayList<PIGI_POINT>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("EMAIL", email);
		paramMap.put("START", START);
		paramMap.put("END", END);		
		
		List<Map<String, Object>> mapList = eventDAO.pointIdListPaging(paramMap); 
		
		for(Map<String, Object> map : mapList) {
			PIGI_POINT point = new PIGI_POINT();
		
			point.setPOINT_KEY(Integer.parseInt(String.valueOf(map.get("POINT_KEY"))));
			point.setEMAIL((String) map.get("EMAIL"));
			point.setPIGI_POINT(Integer.parseInt(String.valueOf(map.get("PIGI_POINT"))));
			point.setROULETTEDATE((String) map.get("ROULETTEDATE"));
						
			list.add(point);
		}
		
		return list;
	}

	@Override
	public List<PIGI_POINT> pointIdListSearchPaging(String email, String mindate,
			String maxdate, int START, int END) throws Exception {
		List<PIGI_POINT> list = new ArrayList<PIGI_POINT>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("EMAIL", email);
		paramMap.put("MINDATE", mindate);
		paramMap.put("MAXDATE", maxdate);		
		paramMap.put("START", START);
		paramMap.put("END", END);		
		
		List<Map<String, Object>> mapList = eventDAO.pointIdListSearchPaging(paramMap); 

		for(Map<String, Object> map : mapList) {
			PIGI_POINT point = new PIGI_POINT();
			
			point.setPOINT_KEY(Integer.parseInt(String.valueOf(map.get("POINT_KEY"))));
			point.setEMAIL((String) map.get("EMAIL"));
			point.setPIGI_POINT(Integer.parseInt(String.valueOf(map.get("PIGI_POINT"))));
			point.setROULETTEDATE((String) map.get("ROULETTEDATE"));
						
			list.add(point);
		}
		
		return list;
	}
	
	@Override
	public int pointIdCount(String email) throws Exception {
		return Integer.parseInt(String.valueOf(eventDAO.pointIdCount(email))); 
	}
	
	@Override
	public int pointIdSearchCount(String email, String MINDATE,String MAXDATE) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("EMAIL",email);
		paramMap.put("MINDATE",MINDATE);
		paramMap.put("MAXDATE",MAXDATE);
		
		return  Integer.parseInt(String.valueOf(eventDAO.pointIdSearchCount(paramMap))); 
	}
	
	@Override
	// insertPointId : Email 회원 가입시 point 지급시 사용
	public void insertPointId(PIGI_EVENT event) throws Exception {
		eventDAO.insertPointId(event);		
	}

	@Override
	// updatePointId : Email 회원의 point를 수정
	public void updatePointId(PIGI_EVENT event) throws Exception {
		eventDAO.updatePointId(event);		
	}

	@Override
	// updateCouponId : Email 회원의 쿠폰을 수정
	public void updateCouponId(PIGI_EVENT event) throws Exception {
		eventDAO.updateCouponId(event);		
	}
	
	@Override
	public void updateRoulettedateId(PIGI_EVENT event) throws Exception {
		eventDAO.updateRoulettedateId(event);
	}

	@Override
	public Payment selectPaymentOID(int OID) throws Exception {
		return eventDAO.selectPaymentOID(OID);
	}

	@Override
	public Payment selectPaymentOBNUMBER(int OBNUMBER) throws Exception {
		return eventDAO.selectPaymentOBNUMBER(OBNUMBER);
	}

	@Override
	public void insertPayment(Payment payment) throws Exception {
		eventDAO.insertPayment(payment);
	}

	@Override
	public void insertPigiPointID(PIGI_POINT pigi_point) throws Exception {
		eventDAO.insertPigiPointID(pigi_point);		
	}

	@Override
	public PIGI_POINT selectLastPigiPointID(String email) throws Exception {
		return eventDAO.selectLastPigiPointID(email);
	}
}