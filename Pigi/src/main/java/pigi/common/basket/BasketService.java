package pigi.common.basket;

import java.util.List;
import java.util.Map;

import pigi.model.BasketBean;

public interface BasketService {
	
	public List<Map<String,Object>> basketList(BasketBean basket) throws Exception;

	public void updateBasket(BasketBean basket) throws Exception;

	public void deleteBasket(BasketBean basket) throws Exception;
	
	public Map<String, Object> selectBasketBIDX(BasketBean basket) throws Exception;
	
	public void deleteBasketBid(BasketBean basket) throws Exception;
}