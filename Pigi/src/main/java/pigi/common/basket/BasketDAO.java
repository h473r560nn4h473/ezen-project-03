package pigi.common.basket;

import java.util.Map;

import javax.annotation.Resource;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("basketDAO")
public class BasketDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Map<String,Object>> basketList(Map<String,Object> map)throws Exception{
		return sqlSessionTemplate.selectList("basket.basketList",map);
	}
	
	public void updateBasket(Map<String,Object> map)throws Exception{
		sqlSessionTemplate.update("basket.updateBasket", map);
	}
	
	public void deleteBasket(Map<String,Object> map) throws Exception{
		sqlSessionTemplate.delete("basket.deleteBasket", map);
	}
	
	public Map<String, Object> selectBasketBIDX(Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectOne("basket.selectBasketBIDX", map);
	}
	
	public void deleteBasketBid(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.delete("basket.deleteBasketBid", map);
	}
}