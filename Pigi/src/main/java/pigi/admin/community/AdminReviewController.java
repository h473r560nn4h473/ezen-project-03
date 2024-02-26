package pigi.admin.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pigi.model.CommunityBean;
import pigi.util.MapToBean;
import pigi.util.Paging;

@Controller
public class AdminReviewController {
	
	@Resource(name="adminCommunityService")
	private AdminCommunityService adminCommunityService;

	@RequestMapping(value="/adminReviewList.al")
	public String adminReviewList(Model model) throws Exception{
		
		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int reviewListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "noticeList.al";
		String searchUrl = "";
		
		List<Map<String, Object>> list = adminCommunityService.reviewListPaging(START, END);
		List<CommunityBean> reviewList = new ArrayList<CommunityBean>();
		
		for(Map<String,Object> mapObject : list) {
			reviewList.add(MapToBean.mapToCommunity(mapObject));
		}
		
		reviewListCount = adminCommunityService.reviewListCount();
		
		Paging paging = new Paging(reviewListCount,	pageBlock,
		pageSize ,currentPage, url, searchUrl);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		model.addAttribute("reviewList", reviewList);
		
		return "adminReviewListAjax";
	}
	
	@RequestMapping(value="/adminReviewDelete.al")
	public String adminReviewDelete(CommunityBean community, Model model) throws Exception{
		
		model.addAttribute("msg", "후기 삭제가 완료되었습니다");
		model.addAttribute("url", "/adminReviewList.al");
		adminCommunityService.deleteCommunityId(community);
		
		return "admin/community/adminReviewDelete";
	}	

	@RequestMapping(value="/adminReviewDeleteAjax.al")
	public String adminReviewDeleteAjax(String CIDX) throws Exception{
		CommunityBean community = new CommunityBean();
		community.setCIDX(Integer.parseInt(CIDX));
		
		adminCommunityService.deleteCommunityId(community);
	
		return "adminReviewList";
	}	
}