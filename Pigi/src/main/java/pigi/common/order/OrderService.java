package pigi.common.order;

import java.util.Map;
import java.util.List;

import pigi.model.MemberBean;
import pigi.model.OrderBean;

public interface OrderService {
	
	// 상품 바로 구매 기능
	public void insertOrderDirect(OrderBean order) throws Exception;
	    
	// 장바구니 상품 구매 폼
	public Map<String, Object> selectMemberId(MemberBean member) throws Exception;
	   
	// 장바구니 상품 구매 기능
	public void insertOrderBasket(OrderBean order) throws Exception;
	   
	// 상품 구매 결과(selectOrderOId)
	public Map<String, Object> selectOrderOId(OrderBean order) throws Exception;
	   
	// 상품 구매 결과(selectOrderOBNumber)
	public List<Map<String, Object>> selectOrderOBNumber(OrderBean order) throws Exception;
	   
	// 가장 마지막에 주문한 주문의 OID값
	public int selectOIDMax() throws Exception;	   
	
	// 주문 테이블에서 가장 높은 ORDER값
	public int selectOrderOBnumberMax() throws Exception;
}