package pigi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pigi.common.MainService;
import pigi.model.ProductBean;
import pigi.util.MapToBean;

@Controller
public class MainController {
	
	@Resource(name="mainService")
	private MainService mainService;
	
	@RequestMapping(value="/main.al")
	public String main_layout(Model model) {
		return "main_layout";
	}
	
	
	 @RequestMapping(value="/main.al", method=RequestMethod.GET) 
	 public String selectProductBest(HttpServletRequest request, Model model) throws Exception{
	 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 List<ProductBean> mainBList = new ArrayList<ProductBean>(); 
	 
	 list = mainService.selectProductBest();
	 
	 for(Map<String, Object> mapObject : list) {
	 mainBList.add(MapToBean.mapToProduct(mapObject)); 
	 }
	 
	 model.addAttribute("mainBList", mainBList);

	 return "main_layout";
	 }
	 
	
	@RequestMapping(value="/adminMain.al")
	public String adminMain(Model model) {
		return "admin_layout";
	}
}