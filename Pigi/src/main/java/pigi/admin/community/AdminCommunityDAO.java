package pigi.admin.community;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("adminCommunityDAO")
public class AdminCommunityDAO  {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	 	
	//관리자 공지 게시판 리스트
	public List<Map<String, Object>> noticeList() throws Exception {
		return sqlSessionTemplate.selectList("community.noticeList");
	}
		
	//관리자 공지 게시판 상세보기
	public Map<String, Object> selectNoticeId(Map<String, Object> map) throws Exception {
		return  sqlSessionTemplate.selectOne("community.selectNoticeId", map);
	}
		
	//관리자 공지사항 글 등록
	public void insertNotice(Map<String,Object> map) throws Exception{
		sqlSessionTemplate.insert("community.insertNotice",map);
	}
		
	//관리자 공지사항 수정 
	public void updateNoticeId(Map<String,Object> map) throws Exception{
		sqlSessionTemplate.update("community.updateNoticeId",map);
	}
	
	//관리자 고객후기 게시판 리스트
	public List<Map<String, Object>>reviewList() throws Exception {
		return sqlSessionTemplate.selectList("community.reviewList");
	}
		
	//관리자 고객센터 게시판 리스트
	public List<Map<String, Object>>qnaList() throws Exception {
		return sqlSessionTemplate.selectList("community.qnaList");
	}
		
	//관리자 고객센터 게시판 상세보기
	public Map<String, Object> selectQnaId(Map<String, Object> map) throws Exception {
		return  sqlSessionTemplate.selectOne("community.selectQnaId", map);
	}
		
	//관리자 고객센터 댓글 보기
	public List<Map<String, Object>>commentListId(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("community.commentListId", map);
	}
	
	//관리자 고객센터 댓글 입력
	public void insertComment(Map<String,Object> map) throws Exception{
		sqlSessionTemplate.insert("community.insertComment",map);
	}
	
	//관리자 고객센터 댓글 수정
	public void updateComment(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.update("community.updateComment", map);
	}

	//관리자 고객센터 댓글 삭제
	public void deleteComment(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.delete("community.deleteComment", map);
	}
	
	//관리자 공지,후기 삭제 기능
	public void deleteCommunityId(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.delete("community.deleteCommunityId", map);
	}
	
	/* 페이징 */
	public List<Map<String, Object>> noticeListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("community.noticeListPaging", map);
	}
	
	public List<Map<String, Object>>reviewListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("community.reviewListPaging", map);
	}

	public List<Map<String, Object>>qnaListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("community.qnaListPaging", map);
	}	
	
	public Map<String, Object> noticeListCount()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"community.noticeListCount");
	}
	
	public Map<String, Object> reviewListCount()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"community.reviewListCount");
	}
	
	public Map<String, Object> qnaListCount()
			throws Exception {
		return sqlSessionTemplate.selectOne(
			"community.qnaListCount");
	}
}