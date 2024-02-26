package pigi.admin.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pigi.model.CommentBean;
import pigi.model.CommunityBean;
import pigi.util.MapToBean;
import pigi.util.Paging;
import pigi.util.validator.CommentValidator;

@Controller
public class AdminQnaController {
	
	@Resource(name="adminCommunityService")
	private AdminCommunityService adminComService;
	
	@RequestMapping(value="/adminQnaList.al")
	public String adminQnaList(Model model) throws Exception {
		
		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int qnaListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "qnaList.al";
		String searchUrl = "";
		
		List<Map<String, Object>> list = adminComService.qnaListPaging(START, END);
		List<CommunityBean> qnaList = new ArrayList<CommunityBean>();
		
		for(Map<String, Object> mapObject : list) {
			qnaList.add( MapToBean.mapToCommunity(mapObject) );
		}
		
		qnaListCount = adminComService.qnaListCount();
		
		Paging paging = new Paging(qnaListCount, pageBlock,
		pageSize ,currentPage, url, searchUrl);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		model.addAttribute("qnaList", qnaList);
		
		return "adminQnaListAjax";
	}
	
	@RequestMapping(value="/adminQnaDelete.al")
	public String adminQnaDelete(CommunityBean community,
			Model model) throws Exception{

		
		model.addAttribute("msg", "게시글 삭제가 완료되었습니다");
		model.addAttribute("url", "/adminQnaList.al?CIDX="+community.getCIDX());
		adminComService.deleteCommunityId(community);
		
		return "/admin/community/adminQnaDelete";
	}
	
	@ResponseBody
	@RequestMapping(value="/adminQnaDeleteAjax.al")
	public String adminQnaDeleteAjax(CommunityBean community,
			Model model) throws Exception{
	
		
		
		adminComService.deleteCommunityId(community);
		
		return "success";
	}
	
	@RequestMapping(value="/adminQnaDetail.al")
	public String adminQnaDetail(CommunityBean community,
			CommentBean comment,
			Model model) throws Exception {
		
		Map<String, Object> qnaMap = adminComService.selectQnaId(community);
		comment.setARTICLEIDX(community.getCIDX());
		List<Map<String, Object>> list = adminComService.commentListId(comment);
	
		CommunityBean qnaBean = MapToBean.mapToCommunity(qnaMap);
		List<CommentBean> comList = new ArrayList<CommentBean>();
		for(Map<String, Object> mapObject : list) {
			comList.add( MapToBean.mapToComment(mapObject) );
		}
		model.addAttribute("qnaBean", qnaBean);
		model.addAttribute("comList", comList);		
		
		return "adminQnaDetailAjax";
	}

	@RequestMapping(value="/adminQnaComWrite.al")
	public String adminQnaComWrite(CommentBean comment,
			Model model) throws Exception {	

		
		
		model.addAttribute("msg", "댓글 작성이 완료되었습니다");
		model.addAttribute("url", "/adminQnaDetail.al?CIDX="+comment.getARTICLEIDX());
		adminComService.insertComment(comment);		
		
		return "/admin/community/adminQnaComWrite";
	}
	
	@RequestMapping(value="/adminQnaComWriteAjax.al")
	@ResponseBody
	public String adminQnaComWriteAjax(CommentBean comment) throws Exception {	

		
		adminComService.insertComment(comment);		
		
		return "good";
	}

	@RequestMapping(value="/adminQnaComModify.al")
	public String adminQnaComModify(CommentBean comment, Model model) throws Exception {

		

		model.addAttribute("msg", "댓글 수정이 완료되었습니다");
		model.addAttribute("url", "/adminQnaDetail.al?CIDX="+comment.getARTICLEIDX());
		adminComService.updateComment(comment);				
		
		return "/admin/community/adminQnaComModify";
	}
	
	@RequestMapping(value="/adminQnaComDelete.al")
	public String adminQnaComDelete(CommentBean comment, Model model) 
			throws Exception {

		
		model.addAttribute("msg", "댓글 삭제가 완료되었습니다");
		model.addAttribute("url", "/adminQnaDetail.al?CIDX="+comment.getARTICLEIDX());
		adminComService.deleteComment(comment);				
		
		return "/admin/community/adminQnaComDelete";
	}
	
	@RequestMapping(value="/adminQnaComDeleteAjax.al")
	@ResponseBody
	public String adminQnaComDeleteAjax(CommentBean comment) 
			throws Exception {

		adminComService.deleteComment(comment);				
		
		return "good";
	}
}