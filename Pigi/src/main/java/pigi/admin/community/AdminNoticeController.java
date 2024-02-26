package pigi.admin.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pigi.model.CommunityBean;
import pigi.model.MemberBean;
import pigi.util.MapToBean;
import pigi.util.Paging;
import pigi.util.validator.CommunityValidator;
 
@Controller
public class AdminNoticeController {

	@Resource(name="adminCommunityService")
	private AdminCommunityService adminComService;


	
	@RequestMapping(value="/adminNoticeList.al")
	public String adminNoticeList(
	/*		@RequestParam("START") int START,
			@RequestParam("END") int END	*/	
			Model model) throws Exception {
		
		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int noticeListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "noticeList.al";
		String searchUrl = "";
		
		List<Map<String, Object>> list = adminComService.noticeListPaging(START, END);
		List<CommunityBean> noticeList = new ArrayList<CommunityBean>();
		
		for(Map<String, Object> mapObject : list) {
			noticeList.add( MapToBean.mapToCommunity(mapObject) );
		}
		
		noticeListCount = adminComService.noticeListCount();
		
		Paging paging = new Paging(noticeListCount,	pageBlock,
				pageSize ,currentPage, url, searchUrl);
	
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		
		model.addAttribute("noticeList", noticeList);
		
		return "adminNoticeListAjax";
	}
	
	@RequestMapping(value="/adminNoticeWriteForm.al")
	public String adminNoticeWriteForm(Model model) {
		
		
		return "adminNoticeWriteForm";
	}
	
	
	@RequestMapping(value="/adminNoticeWrite.al")
	public String adminNoticeWrite(CommunityBean community, BindingResult result, Model model) throws Exception {
		
		// Validator로 유효성 검사
		new CommunityValidator().validate(community, result);
		
		if(result.hasErrors()) {
			return "adminNoticeWriteForm";
			// adminNoticeModifyForm 페이지로 넘어가기 위해서는 CommunityBean 객체를 넘겨줘야하는데 바로 리턴하면 오류발생
		}	
		
		model.addAttribute("msg", "게시글 작성이 완료되었습니다");
		model.addAttribute("url", "/adminNoticeList.al");
		adminComService.insertNotice(community);
		
		return "admin/community/adminNoticeWrite";
	}
	
	
	@RequestMapping(value="/adminNoticeModifyForm.al")
	public String adminNoticeModifyForm(CommunityBean community, Model model)
			throws Exception {
		
		Map<String, Object> noticeMap = adminComService.selectNoticeId(community);
		CommunityBean noticeBean = MapToBean.mapToCommunity(noticeMap);
		
		model.addAttribute("noticeBean", noticeBean);		
		
		return "adminNoticeModifyForm";
	}
	
	
	@RequestMapping(value="/adminNoticeModify.al")
	public String adminNoticeModify(CommunityBean community, Model model) throws Exception {

		adminComService.updateNoticeId(community);
		model.addAttribute("msg", "게시글 수정이 완료되었습니다");
		model.addAttribute("url", "/adminNoticeList.al");
		
		return "admin/community/adminNoticeModify";
	}
	
	
	@RequestMapping(value="/adminNoticeDelete.al")
	public String adminNoticeDelete(CommunityBean community, Model model)
			throws Exception {

		model.addAttribute("msg", "게시글 삭제가 완료되었습니다");
		model.addAttribute("url", "/adminNoticeList.al");
		adminComService.deleteCommunityId(community);
		
		return "admin/community/adminNoticeDelete";
	}
	
	@RequestMapping(value="/adminNoticeDeleteAjax.al")
	public String adminNoticeDeleteAjax(String CIDX) throws Exception {
		CommunityBean community = new CommunityBean();
		community.setCIDX(Integer.parseInt(CIDX));
		
		adminComService.deleteCommunityId(community);
		
		return "adminNoticeList";
	}
	
	
	@RequestMapping(value="/adminNoticeDetail.al")
	public String adminNoticeDetail(CommunityBean community, Model model)
			throws Exception {
		
		Map<String, Object> noticeMap = adminComService.selectNoticeId(community);
		CommunityBean noticeBean = MapToBean.mapToCommunity(noticeMap);
		
		noticeMap = adminComService.selectNoticeId(community);
		noticeBean = MapToBean.mapToCommunity(noticeMap);
		
		model.addAttribute("noticeBean", noticeBean);	
		
		return "adminNoticeDetail";
	}
	
}