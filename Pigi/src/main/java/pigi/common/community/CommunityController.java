package pigi.common.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pigi.model.CommentBean;
import pigi.model.CommunityBean;
import pigi.util.MapToBean;
import pigi.util.Paging;

@Controller
public class CommunityController {

	@Resource(name = "communityService")
	private CommunityService communityService;

	@RequestMapping(value = "/noticeList.al")
	public String noticeList(Model model, HttpServletRequest request) throws Exception {
		
		/* 페이징을 위한 변수 */
		int pageSize = 5; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int noticeListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "noticeList.al";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<CommunityBean> noticeBeanList = new ArrayList<CommunityBean>();
		list = communityService.noticeListPaging(START, END);

		noticeListCount = communityService.noticeListCount();
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 공지의 수, 현재 페이지, 이동주소, 검색시 사용할 주소 입력
		Paging paging = new Paging(noticeListCount,	pageBlock,
				pageSize ,currentPage, url, searchUrl);
		
		
		for (Map<String, Object> mapObject : list) {
			noticeBeanList.add(MapToBean.mapToCommunity(mapObject));
		}
		
		model.addAttribute("noticeBeanList", noticeBeanList);
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		
		return "noticeList";
	}

	@RequestMapping(value = "/noticeDetail.al")
	public String noticeDetail(CommunityBean community, Model model, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		CommunityBean communityBean = new CommunityBean();
		
		map = communityService.selectNoticeId(community);
		communityBean = MapToBean.mapToCommunity(map); 
		
		model.addAttribute("communityBean", communityBean);

		return "noticeDetail";
	}

	@RequestMapping(value = "/qnaList.al")
	public String qnaList(Model model, HttpServletRequest request)
			throws Exception {
		/* 페이징을 위한 변수 */
		int pageSize = 5; // 페이지당 출력할 qna의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int qnaListCount; // 전체 qna글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "qnaList.al";
		String searchUrl = "";

		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<CommunityBean> qnaBeanList = new ArrayList<CommunityBean>();
		list = communityService.qnaListPaging(START, END);
		
		qnaListCount = communityService.qnaListCount();

		for (Map<String, Object> mapObject : list) {
			qnaBeanList.add(MapToBean.mapToCommunity(mapObject));
		}
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 공지의 수, 현재 페이지, 이동주소, 검색시 사용할 주소 입력
		Paging paging = new Paging(qnaListCount,pageBlock,
				pageSize ,currentPage, url, searchUrl);
		

		model.addAttribute("qnaBeanList", qnaBeanList);
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		
		return "qnaList";
	}

	@RequestMapping(value = "/qnaDetail.al")
	public String qnaDetail(CommunityBean community, 
			CommentBean comment,
			Model model, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		CommunityBean communityBean = new CommunityBean();
		
		map = communityService.selectQnaId(community);
		
		communityBean = MapToBean.mapToCommunity(map);
		
		model.addAttribute("communityBean", communityBean);
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<CommentBean> commentBeanList = new ArrayList<CommentBean>(); 
		comment.setARTICLEIDX(community.getCIDX()); //CIDX값을 받아옴
		list= communityService.commentListId(comment);
	
		for(Map<String, Object> mapObject : list) {
			commentBeanList.add( MapToBean.mapToComment(mapObject) );
		}
		int comCount = list.size();
		
		
		model.addAttribute("commentBeanList", commentBeanList);
		model.addAttribute("comCount", comCount);

		return "qnaDetail";
	}

	@RequestMapping(value = "/qnaForm.al")
	public String qnaForm(Model model) {
		
		return "qnaForm";
	}

	@RequestMapping(value = "/qna.al")
	public String qna( CommunityBean community, Model model, HttpServletRequest request) throws Exception {
		
		// member에 세션에서 사용자 이메일을 가져와서 저장
		String email = (String) request.getSession().getAttribute("EMAIL");
		
		community.setCWRITER(email);

		model.addAttribute("msg", "게시글 작성이 완료되었습니다");
		model.addAttribute("url", "/qnaList.al");
		communityService.insertQna(community);

		return "/community/qna";
	}
}