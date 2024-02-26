package pigi.admin.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.OrderBean;

@Service("adminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService{

	@Resource(name="adminOrderDAO")
	private AdminOrderDAO adminOrderDAO;
	
	@Override
	public List<Map<String, Object>> orderList() throws Exception {
		return adminOrderDAO.orderList();
	}

	@Override
	public List<Map<String, Object>> orderListSearch(String condition, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", condition);
		map.put("KEYWORD", keyword);		
		
		return adminOrderDAO.orderListSearch(map);
	}

	@Override
	public Map<String, Object> selectOrderOId(OrderBean order) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OID", order.getOID());
		return adminOrderDAO.selectOrderOId(map);
	}

	@Override
	public void updateOrderId(OrderBean order) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OSTATUS", order.getOSTATUS());
		map.put("OID", order.getOID());
		adminOrderDAO.updateOrderId(map);
	}

}