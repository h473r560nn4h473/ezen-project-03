package pigi.admin.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.ProductBean;

@Service("adminProductService")
public class AdminProductServiceImpl implements AdminProductService {

	@Resource(name="adminProductDAO")
	private AdminProductDAO adminProductDAO; 
	
	@Override
	public List<Map<String, Object>> allListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminProductDAO.allListPaging(map);
	}

	@Override
	public List<Map<String, Object>> allListPSELLDescPaging(int START, int END) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminProductDAO.allListPSELLDescPaging(map);
	}
	
	@Override
	public List<Map<String, Object>> allListKeyWordSearch(String KEYWORD, String ISNUMBER) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// KEYWORD가 숫자인지 아닌지를 검사해서 ISNUMBER값 설정
		// KEYWORD가 숫자이면 map에 KEYWORD값 숫자로 변환해서 입력
		map.put("KEYWORD", KEYWORD);
		map.put("ISNUMBER", ISNUMBER);		
						
		return adminProductDAO.allListKeyWordSearch(map);		
	}

	@Override
	public void insertProduct(ProductBean product) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("PNAME", product.getPNAME());
		map.put("PIMAGE", product.getPIMAGE());
		map.put("PPRICE", product.getPPRICE());
		map.put("PSALE", product.getPSALE());
		map.put("PSTOCK", product.getPSTOCK());
		
		map.put("PCOM", product.getPCOM());
		map.put("PLOC", product.getPLOC());
		
		// PWEIGHT가 -1이면 null을 입력
		if(product.getPWEIGHT() == -1) {
			map.put("PWEIGHT", null);
		} else {
			map.put("PWEIGHT", product.getPWEIGHT());
		}
		
		map.put("PKIND", product.getPKIND());
		map.put("PTYPE", product.getPTYPE());
						
		adminProductDAO.insertProduct(map);		
	}
	
	@Override
	public int selectPIDMax() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = adminProductDAO.selectPIDMax();
		
		int maxPID;
		if(map == null) {
			maxPID = 0;
		} else {
			maxPID = Integer.parseInt(String.valueOf(map.get("PIDMAX")));	
		}
		
		return maxPID;
	}	

	@Override
	public Map<String, Object> selectProductId(ProductBean product) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("PID", product.getPID());
						
		return adminProductDAO.selectProductId(map);
	}

	@Override
	public void updateProduct(ProductBean product) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("PNAME", product.getPNAME());
		map.put("PIMAGE", product.getPIMAGE());
		map.put("PPRICE", product.getPPRICE());
		map.put("PSALE", product.getPSALE());
		map.put("PSTOCK", product.getPSTOCK());
		
		map.put("PCOM", product.getPCOM());
		map.put("PLOC", product.getPLOC());
		// PWEIGHT가 -1이면 null을 입력
		if(product.getPWEIGHT() == -1) {
			map.put("PWEIGHT", null);
		} else {
			map.put("PWEIGHT", product.getPWEIGHT());
		}
		map.put("PKIND", product.getPKIND());
		map.put("PTYPE", product.getPTYPE());
		map.put("PID", product.getPID());
		map.put("PSELL", product.getPSELL());
						
		adminProductDAO.updateProduct(map);
	}

	@Override
	public void deleteProduct(ProductBean product) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("PID", product.getPID());
						
		adminProductDAO.deleteProduct(map);
	}

	@Override
	public List<Map<String, Object>> allListKeywordPaging(String KEYWORD, int KEYNUMBER, int START, int END)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("KEYWORD", KEYWORD);
		if(KEYNUMBER == -1) {
			map.put("KEYNUMBER", null);
		} else {
			map.put("KEYNUMBER", KEYNUMBER);			
		}
		map.put("START", START);
		map.put("END", END);
		
		
		System.out.println("---- allListKeywordPaging ----");
		System.out.println("");
		System.out.println("");
		System.out.println("---- allListKeywordPaging ----");
		
		return adminProductDAO.allListKeywordPaging(map);
	}

	@Override
	public int allListKeywordCount(String KEYWORD, int KEYNUMBER) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("KEYWORD", KEYWORD);
		if(KEYNUMBER == -1) {
			map.put("KEYNUMBER", null);
		} else {
			map.put("KEYNUMBER", KEYNUMBER);			
		}
		Map<String, Object> countMap = adminProductDAO.allListKeywordCount(map);
		
		int keywordCount = Integer.parseInt(String.valueOf(countMap.get("COUNT")));
		return keywordCount;
	}
}