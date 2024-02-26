package pigi.common.basket;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pigi.common.product.ProductService;
import pigi.model.BasketBean;
import pigi.model.ProductBean;
import pigi.util.MapToBean;

@Controller
public class BasketController {

	@Resource(name="basketService")
	private BasketService basketService;
	
	@Resource(name="productService")
	private ProductService productService;
	
	@RequestMapping(value="/basketList.al")
	public String basketList(BasketBean basket, HttpServletRequest request, Model model) throws Exception {
		
		// basket에 세션에서 사용자 이메일을 가져와서 저장
		String email = (String) request.getSession().getAttribute("EMAIL");
		basket.setBEMAIL(email);
		
		List<Map<String, Object>> list = basketService.basketList(basket); 
		
		List<BasketBean> basketBeanList = new ArrayList<BasketBean>();
		for(Map<String, Object> mapObject : list) {
			basketBeanList.add(MapToBean.mapToBasket(mapObject));
		}
		
		List<ProductBean> proInfoList = new ArrayList<ProductBean>();
		for(BasketBean basPro : basketBeanList) {
			
			ProductBean pro = new ProductBean();
			pro.setPID(basPro.getBID());			
			Map<String, Object> proInfoMap = productService.selectProductId(pro);
			ProductBean proInfo = MapToBean.mapToProduct(proInfoMap);
			
			proInfoList.add(proInfo);			
		}
		
		
		model.addAttribute("basketBeanList", basketBeanList);
		model.addAttribute("Size", basketBeanList.size());
		model.addAttribute("proInfoList", proInfoList);
		
		return "basketListAjax";
	}
	
	@RequestMapping(value="/basketModify.al")
	public String basketModify(BasketBean basket, HttpServletRequest request,
			Model model) throws Exception {

		Map<String, Object> map = basketService.selectBasketBIDX(basket);
		BasketBean basketInfo = MapToBean.mapToBasket(map);
		
		ProductBean product = new ProductBean(); 
		product.setPID(basketInfo.getBID());
		Map<String, Object> mapProduct = productService.selectProductId(product);
		ProductBean productInfo = MapToBean.mapToProduct(mapProduct);
		  
		if(basket.getBCOUNT() > productInfo.getPSTOCK()) {
			model.addAttribute("msg", "재고 수량보다 많은 상품을 장바구니에 담을 수 없습니다"); 
			return "/basket/basketModify";
		}
		  
		if(basket.getBCOUNT() <= 0) {
			model.addAttribute("msg", "장바구니에 상품을 담을 수 없습니다"); 
			return "/basket/basketModify";
		}
		 
		basketService.updateBasket(basket);	 	
		return "/basket/basketModify";
	}
	
	@ResponseBody
	@RequestMapping(value="/basketModifyAjax.al")
	public Map<String,String> basketModifyAjax(BasketBean basket, HttpServletRequest request,
			@RequestBody String message) throws Exception {
		
		System.out.println("basket bidx : " + basket.getBIDX());
		System.out.println("basket BCOUNT : " + basket.getBCOUNT());
		
		Map<String, String> msg = new HashMap<String, String>();

		Map<String, Object> map = basketService.selectBasketBIDX(basket);
		BasketBean basketInfo = MapToBean.mapToBasket(map);
		
		ProductBean product = new ProductBean(); 
		product.setPID(basketInfo.getBID());
		Map<String, Object> mapProduct = productService.selectProductId(product);
		ProductBean productInfo = MapToBean.mapToProduct(mapProduct);
		  
		if(basket.getBCOUNT() > productInfo.getPSTOCK()) {
			msg.put("message", "재고 수량보다 많은 상품을 장바구니에 담을 수 없습니다");
			return msg;
		}
		  
		if(basket.getBCOUNT() <= 0) {
			msg.put("message", "0 이하로 수정할 수 없습니다");
			return msg;
		}
		 
		basketService.updateBasket(basket);	 	
		msg.put("message", "수정하였습니다");
		return msg;
	}
	
	@RequestMapping(value="/basketDelete.al")
	public String basketDelete(BasketBean basket, Model model) throws Exception {
		
		basketService.deleteBasket(basket);
		
		return "/basket/basketDelete";
	}
	
	@ResponseBody
	@RequestMapping(value="/basketDeleteAjax.al")
	public String basketDeleteAjax(BasketBean basket) throws Exception {
		
		basketService.deleteBasket(basket);
		
		return "success";
	}
	
}