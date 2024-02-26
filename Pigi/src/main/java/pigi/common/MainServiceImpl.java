package pigi.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import pigi.common.product.ProductDAO;

@Service("mainService")
public class MainServiceImpl implements MainService {

	@Resource(name="productDAO")
	private ProductDAO productDAO;
	
	@Override
	public List<Map<String, Object>> selectProductBest() 
			throws Exception {
		return productDAO.selectProductBest();
	}
}