package pigi.common.community;

import java.util.List;
import java.util.Map;

import pigi.model.CommunityBean;
import pigi.model.CommentBean;

public interface CommunityService {

	public List<Map<String, Object>> noticeList() throws Exception;
	
	public Map<String, Object> selectNoticeId(CommunityBean community) throws Exception;
	
	public List<Map<String, Object>> qnaList() throws Exception;
	
	public Map<String, Object> selectQnaId(CommunityBean community) throws Exception;
	
	public List<Map<String, Object>> commentListId(CommentBean comment) throws Exception;
	
	public void insertQna(CommunityBean community) throws Exception;
	
	/* 페이징 */
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception;

	public List<Map<String, Object>> qnaListPaging(int START, int END) throws Exception;
	
	public int noticeListCount() throws Exception;
	
	public int qnaListCount() throws Exception;
}