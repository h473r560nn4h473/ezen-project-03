package pigi.common.basket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.BasketBean;

@Service("basketService")
public class BasketServiceImpl implements BasketService {
	
	@Resource(name="basketDAO")
	private BasketDAO basketDAO;

	@Override
	public List<Map<String, Object>> basketList(BasketBean basket) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", basket.getBEMAIL());
						
		return basketDAO.basketList(map);
	}

	@Override
	public void updateBasket(BasketBean basket) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("BCOUNT", basket.getBCOUNT());
		map.put("BIDX", basket.getBIDX());
						
		basketDAO.updateBasket(map);	
	}

	@Override
	public void deleteBasket(BasketBean basket) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BIDX", basket.getBIDX());
						
		basketDAO.deleteBasket(map);			
	}
	
	@Override
	public Map<String, Object> selectBasketBIDX(BasketBean basket) throws Exception {
		Map<String, Object> map  = new HashMap<String, Object>();
		
		map.put("BIDX", basket.getBIDX());
		
		return basketDAO.selectBasketBIDX(map);
	}

	@Override
	public void deleteBasketBid(BasketBean basket) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BID", basket.getBID());	
		
		basketDAO.deleteBasketBid(map);	
	}
}