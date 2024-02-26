package pigi.admin.community;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.CommentBean;
import pigi.model.CommunityBean;

@Service("adminCommunityService")
public class AdminCommunityServiceImpl implements AdminCommunityService {

	@Resource(name = "adminCommunityDAO")
	private AdminCommunityDAO adminCommunityDAO;
	
	
	@Override
	public List<Map<String, Object>> noticeList() throws Exception {
		return adminCommunityDAO.noticeList();
	}

	@Override
	public Map<String, Object> selectNoticeId(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CIDX", community.getCIDX());
		
		return adminCommunityDAO.selectNoticeId(map);
	}

	@Override
	public void insertNotice(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CTITLE", community.getCTITLE());
		map.put("CWRITER", community.getCWRITER());
		map.put("CCONTENT", community.getCCONTENT());
		
		adminCommunityDAO.insertNotice(map);
	}

	@Override
	public void updateNoticeId(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CTITLE", community.getCTITLE());
		map.put("CCONTENT", community.getCCONTENT());
		map.put("CIDX", community.getCIDX());
		
		adminCommunityDAO.updateNoticeId(map);
	}

	@Override
	public List<Map<String, Object>> reviewList() throws Exception {
		return adminCommunityDAO.reviewList();
		
	}

	@Override
	public List<Map<String, Object>> qnaList() throws Exception {
		return adminCommunityDAO.qnaList();
		
	}

	@Override
	public Map<String, Object> selectQnaId(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CIDX", community.getCIDX());
		
		return adminCommunityDAO.selectQnaId(map);
	}

	@Override
	public List<Map<String, Object>> commentListId(CommentBean comment) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ARTICLEIDX", comment.getARTICLEIDX());
		
		return adminCommunityDAO.commentListId(map);
	}

	@Override
	public void insertComment(CommentBean comment) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ARTICLEIDX", comment.getARTICLEIDX());
		map.put("COMMENTWRITER", comment.getCOMMENTWRITER());
		map.put("COMMENTT", comment.getCOMMENTT());
		
		adminCommunityDAO.insertComment(map);
	}

	@Override
	public void updateComment(CommentBean comment) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("COMMENTT", comment.getCOMMENTT());
		map.put("COMENTIDX", comment.getCOMMENTIDX());
		
		adminCommunityDAO.updateComment(map);
	}

	@Override
	public void deleteComment(CommentBean comment) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("COMMENTIDX", comment.getCOMMENTIDX());
		
		adminCommunityDAO.deleteComment(map);
		
	}

	@Override
	public void deleteCommunityId(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CIDX", community.getCIDX());
		
		adminCommunityDAO.deleteCommunityId(map);
	}
	
	/* 페이징 */
	@Override
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminCommunityDAO.noticeListPaging(map);
	}

	@Override
	public List<Map<String, Object>> reviewListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminCommunityDAO.reviewListPaging(map);
	}

	@Override
	public List<Map<String, Object>> qnaListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminCommunityDAO.qnaListPaging(map);		
	}
	
	@Override
	public int noticeListCount() throws Exception {
		Map<String,Object> mapCount = adminCommunityDAO.noticeListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
	
	@Override
	public int qnaListCount() throws Exception {
		Map<String,Object> mapCount = adminCommunityDAO.qnaListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
	
	@Override
	public int reviewListCount() throws Exception {
		Map<String,Object> mapCount = adminCommunityDAO.reviewListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
}