package pigi.common.community;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.CommentBean;
import pigi.model.CommunityBean;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {

	@Resource(name = "communityDAO")
	private CommunityDAO communityDAO;
	
	@Override
	public List<Map<String, Object>> noticeList() throws Exception{
		return communityDAO.noticeList();
	}

	@Override
	public Map<String, Object> selectNoticeId(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CIDX", community.getCIDX());
		
		return communityDAO.selectNoticeId(map);
	}

	@Override
	public List<Map<String, Object>> qnaList() throws Exception {
		return communityDAO.qnaList();
	}

	@Override
	public Map<String, Object> selectQnaId(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CIDX", community.getCIDX());
		
		return communityDAO.selectQnaId(map);
	}

	@Override
	public List<Map<String, Object>> commentListId(CommentBean comment) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ARTICLEIDX", comment.getARTICLEIDX());
		
		return communityDAO.commentListId(map);
	}

	@Override
	public void insertQna(CommunityBean community) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CTITLE", community.getCTITLE());
		map.put("CWRITER", community.getCWRITER());
		map.put("CCONTENT", community.getCCONTENT());
		
		communityDAO.insertQna(map);
	}
	
	/* 페이징 */
	@Override
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return communityDAO.noticeListPaging(map);
	}
	
	@Override
	public List<Map<String, Object>> qnaListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return communityDAO.qnaListPaging(map);		
	}
	
	@Override
	public int noticeListCount() throws Exception {
		Map<String,Object> mapCount = communityDAO.noticeListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
	
	@Override
	public int qnaListCount() throws Exception {
		Map<String,Object> mapCount = communityDAO.qnaListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
}