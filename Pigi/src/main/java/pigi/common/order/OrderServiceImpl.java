package pigi.common.order;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.common.member.MemberDAO;
import pigi.model.MemberBean;
import pigi.model.OrderBean;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;
	
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;

	@Override
	public void insertOrderDirect(OrderBean order) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OMAIL", order.getOMAIL());
		map.put("ONAME", order.getONAME());
		map.put("OPID", order.getOPID());
		map.put("OPRODUCT", order.getOPRODUCT());
		map.put("OCOUNT", order.getOCOUNT());
		map.put("OPRICE", order.getOPRICE());
		map.put("OSALE", order.getOSALE());
		map.put("OTOTAL", order.getOTOTAL());
		map.put("OMOBILE", order.getOMOBILE());
		map.put("OPOSTCODE", order.getOPOSTCODE());
		map.put("OADDRESS1", order.getOADDRESS1());
		map.put("OADDRESS2", order.getOADDRESS2());
		
		orderDAO.insertOrderDirect(map);
	}

	@Override
	public Map<String, Object> selectMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EMAIL", member.getEMAIL());
		return orderDAO.selectMemberId(map);
	}

	@Override
	public void insertOrderBasket(OrderBean order) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OBNUMBER", order.getOBNUMBER());
		map.put("OMAIL", order.getOMAIL());
		map.put("ONAME", order.getONAME());
		map.put("OPID", order.getOPID());
		map.put("OPRODUCT", order.getOPRODUCT());
		map.put("OCOUNT", order.getOCOUNT());
		map.put("OPRICE", order.getOPRICE());
		map.put("OSALE", order.getOSALE());
		map.put("OTOTAL", order.getOTOTAL());
		map.put("OMOBILE", order.getOMOBILE());
		map.put("OPOSTCODE", order.getOPOSTCODE());
		map.put("OADDRESS1", order.getOADDRESS1());
		map.put("OADDRESS2", order.getOADDRESS2());
		
		orderDAO.insertOrderBasket(map);
	}

	@Override
	public Map<String, Object> selectOrderOId(OrderBean order) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OID", order.getOID());
		
		return orderDAO.selectOrderOId(map);
	}

	@Override
	public List<Map<String, Object>> selectOrderOBNumber(OrderBean order) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OBNUMBER", order.getOBNUMBER());
		return orderDAO.selectOrderOBNumber(map);
	}

	@Override
	public int selectOIDMax() throws Exception {
		Map<String, Object> maxMap = new HashMap<String, Object>();
		maxMap = orderDAO.selectOIDMax();
		
		int maxOID;
		if(maxMap == null) {
			maxOID = 0;
		} else {
			maxOID = Integer.parseInt(String.valueOf(maxMap.get("MAX")));
		}
				
		return maxOID;
	}

	@Override
	public int selectOrderOBnumberMax() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = orderDAO.selectOrderOBnumberMax();
		
		int maxOBnumber;
		if(map!=null) {
			maxOBnumber = Integer.parseInt(String.valueOf(map.get("MAX")));
		} else {
			maxOBnumber = 0;
		}
		
		return maxOBnumber;
	}
}