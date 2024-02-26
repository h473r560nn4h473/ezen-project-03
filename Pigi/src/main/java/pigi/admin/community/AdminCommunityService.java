package pigi.admin.community;

import java.util.List;
import java.util.Map;


import pigi.model.CommentBean;
import pigi.model.CommunityBean;

public interface AdminCommunityService {

	public List<Map<String, Object>> noticeList()throws Exception;
	
	public Map<String, Object> selectNoticeId(CommunityBean community)throws Exception;
	
	public void insertNotice(CommunityBean community)throws Exception;
		
	public void updateNoticeId(CommunityBean community)throws Exception;
	
	public List<Map<String, Object>> reviewList()throws Exception;
	
	public List<Map<String, Object>> qnaList()throws Exception;
	
	public Map<String, Object> selectQnaId(CommunityBean community)throws Exception;
	
	public List<Map<String, Object>> commentListId(CommentBean comment)throws Exception;
	
	public void insertComment(CommentBean comment)throws Exception;
	
	public void updateComment(CommentBean comment)throws Exception;
	
	public void deleteComment(CommentBean comment)throws Exception;
	
	public void deleteCommunityId(CommunityBean community)throws Exception;
	
	/* 페이징 */
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception;
	
	public List<Map<String, Object>> reviewListPaging(int START, int END)throws Exception;
	
	public List<Map<String, Object>> qnaListPaging(int START, int END)throws Exception;
	
	public int noticeListCount() throws Exception;
	
	public int qnaListCount() throws Exception;
	
	public int reviewListCount() throws Exception;
}
