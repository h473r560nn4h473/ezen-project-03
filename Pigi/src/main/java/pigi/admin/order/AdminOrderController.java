package pigi.admin.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.eclipse.core.internal.localstore.HistoryBucket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pigi.model.MemberBean;
import pigi.model.OrderBean;
import pigi.util.MapToBean;
import pigi.util.validator.OrderValidator;

@Controller
public class AdminOrderController {

	@Resource(name="adminOrderService")
	private AdminOrderService adminOrderService;
	
	@RequestMapping("/adminOrderList.al")
	public String adminOrderList (Model model, HttpServletRequest request) throws Exception{
		// 검색 조건
		String condition = null;
		String keyword = null;
		
		condition = request.getParameter("condition");
		keyword = request.getParameter("keyword");
		if(keyword!=null && keyword.equals("")) {
			keyword = null;
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
		
		if(keyword == null || keyword.trim() == "") {
			list = adminOrderService.orderList();
		} else {
			list = adminOrderService.orderListSearch(condition, keyword);
		}
		
		for(Map<String, Object> mapObject : list) {
			orderBeanList.add(MapToBean.mapToOrder(mapObject));
		}
		
		model.addAttribute("orderBeanList", orderBeanList);
		
		model.addAttribute("condition", condition);
		
		return "adminOrderList";
	}
	
	
	@RequestMapping("/adminOrderDetail.al")
	public String adminOrderDetail (OrderBean order, Model model) throws Exception{
		Map<String, Object> map = adminOrderService.selectOrderOId(order);
		OrderBean orderBean = new OrderBean();
		
		orderBean = MapToBean.mapToOrder(map);
		
		model.addAttribute("orderBean", orderBean);
		
		return "adminOrderDetail";
	}
	
	@RequestMapping("/adminOrderModify.al")
	public String adminOrderModify (OrderBean order, 
			Model model) throws Exception{
		
		adminOrderService.updateOrderId(order);
		
		model.addAttribute("msg", "주문 정보가 수정되었습니다");
		model.addAttribute("url", "/adminOrderList.al");
		
		return "/admin/order/adminOrderModify";
	}
	
}

