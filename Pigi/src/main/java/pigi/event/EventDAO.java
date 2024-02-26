package pigi.event;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import pigi.model.PIGI_EVENT;
import pigi.model.PIGI_POINT;
import pigi.model.Payment;

@Repository("eventDAO")
public class EventDAO {

	@Resource(name="sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;

	public PIGI_EVENT selectEventId(String EMAIL) throws Exception{
		return sqlSessionTemplate.selectOne("event.selectEventId", EMAIL);
	}
	
	public List<Map<String,Object>> pointIdListPaging (Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectList("event.pointIdListPaging", map);
	}
	
	public List<Map<String,Object>> pointIdListSearchPaging (Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectList("event.pointIdListSearchPaging",map);
	}	
	
	public int pointIdCount(String EMAIL) throws Exception {
		return sqlSessionTemplate.selectOne("event.pointIdCount", EMAIL);
	}
	
	public int pointIdSearchCount(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("event.pointIdSearchCount", map);
	}
	
	// insertPointId : Email 회원 가입시 point 지급시 사용
	public void insertPointId(PIGI_EVENT event) throws Exception {
		sqlSessionTemplate.insert("event.insertPointId", event);
	}	
	
	// updatePointId : Email 회원의 point를 수정
	public void updatePointId(PIGI_EVENT event) throws Exception {
		sqlSessionTemplate.update("event.updatePointId", event);
	}	
	
	// updateCouponId : Email 회원의 쿠폰을 수정
	public void updateCouponId(PIGI_EVENT event) throws Exception {
		sqlSessionTemplate.update("event.updateCouponId", event);
	}	
	
	// updateRoulettedateId : 룰렛을 돌린 날짜를 수정
	public void updateRoulettedateId(PIGI_EVENT event) throws Exception {
		sqlSessionTemplate.update("event.updateRoulettedateId", event);
	}
	
	// selectPaymentOID : OID 값으로 결제 정보를 읽어옴
	public Payment selectPaymentOID(int OID) throws Exception {
		return sqlSessionTemplate.selectOne("event.selectPaymentOID", OID);
	}
	
	// selectPaymentOBNUMBER : OBNUMBER 값으로 결제 정보를 읽어옴
	public Payment selectPaymentOBNUMBER(int OBNUMBER) throws Exception {
		return sqlSessionTemplate.selectOne("event.selectPaymentOBNUMBER", OBNUMBER);
	}
	
	// insertPayment : 결제 정보를 입력
	public void insertPayment(Payment payment) throws Exception {
		sqlSessionTemplate.insert("event.insertPayment", payment);
	}
	
	// insertPigiPointID : 포인트 획득 내역 저장
	public void insertPigiPointID(PIGI_POINT pigi_point) throws Exception {
		sqlSessionTemplate.insert("event.insertPigiPointID", pigi_point); 
	}
	
	// selectLastPigiPointID : ID 회원이 가장 최근에 획득한 포인트 내역 읽어오기
	public PIGI_POINT selectLastPigiPointID(String EMAIL) throws Exception {
		return sqlSessionTemplate.selectOne("event.selectLastPigiPointID", EMAIL);
	}
}