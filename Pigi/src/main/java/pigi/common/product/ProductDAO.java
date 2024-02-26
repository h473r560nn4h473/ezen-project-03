package pigi.common.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository("productDAO")
public class ProductDAO {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Map<String, Object>> selectProductBest()
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.selectProductBest");
	}
	
	public Map<String, Object> selectProductId(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"product.selectProductId", map);
	}
	
	public List<Map<String, Object>> allList()
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.allList");
	}

	public List<Map<String, Object>> allListSearch(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.allListSearch", map);
	}
	
	public List<Map<String, Object>> aclList()
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.aclList");
	}	
	
	public List<Map<String, Object>> aclListSearch(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.aclListSearch", map);
	}
	
	public List<Map<String, Object>> etcList()
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.etcList");
	}	
	
	public List<Map<String, Object>> etcListSearch(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.etcListSearch", map);
	}
	
	public List<Map<String, Object>> selectReviewProduct(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"community.selectReviewProduct", map);
	}
	
	public void insertBasket(Map<String, Object> map) 
			throws Exception {
		sqlSessionTemplate.insert("basket.insertBasket", map);
	}
	
	public Map<String,Object> selectBasketBID(Map<String,Object> map)
			throws Exception{
		return sqlSessionTemplate.selectOne("basket.selectBasketBID", map);
	}
	
	public Map<String, Object> selectBasketBnumberMaxBemail(Map<String,Object> map)
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"basket.selectBasketBnumberMaxBemail", map);
	}
	
	public Map<String, Object> selectBasketBnumberMax()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"basket.selectBasketBnumberMax");
	}

	public void insertReview(Map<String, Object> map)
			throws Exception {
		sqlSessionTemplate.insert("community.insertReview", map);
	}	
	
	/* 페이징 */
	public List<Map<String, Object>> allListPaging(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.allListPaging", map);
	}
	
	public List<Map<String, Object>> allListSearchPaging(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.allListSearchPaging", map);
	}
	
	public List<Map<String, Object>> aclListPaging(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.aclListPaging", map);
	}	
	
	public List<Map<String, Object>> aclListSearchPaging(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.aclListSearchPaging", map);
	}
	
	public List<Map<String, Object>> etcListPaging(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.etcListPaging", map);
	}	
	
	public List<Map<String, Object>> etcListSearchPaging(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectList(
			"product.etcListSearchPaging", map);
	}	
	
	public Map<String, Object> allListCount()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"product.allListCount");
	}
	
	public Map<String, Object> allListSearchCount(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"product.allListSearchCount", map);
	}	
	
	public Map<String, Object> aclListCount()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"product.aclListCount");
	}
	
	public Map<String, Object> aclListSearchCount(Map<String, Object> map)
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"product.aclListSearchCount", map);
	}	
	
	public Map<String, Object> etcListCount()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"product.etcListCount");
	}	
}