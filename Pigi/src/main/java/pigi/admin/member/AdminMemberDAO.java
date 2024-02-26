package pigi.admin.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("adminMemberDAO")
public class AdminMemberDAO {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 회원 전체목록 불러오기
	public List<Map<String, Object>> memberList() throws Exception{
		return sqlSessionTemplate.selectList("member.memberList");
	}
	
	// 회원 검색, 상세보기
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("member.selectMemberId", map);
	}
	
	// 회원정보 수정
	public void updateMemberAdmin(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.update("member.updateMemberAdmin", map);
	}
	
	// 회원정보 삭제
	public void deleteMember(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.delete("member.deleteMember", map);
	}
	
	// 회원 전체목록 페이징
	public List<Map<String, Object>> memberListPaging(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectList("member.memberListPaging", map);
	}
	
	// 전체 회원의 수
	public Map<String, Object> memberCount() throws Exception {
		return sqlSessionTemplate.selectOne("member.memberCount");
	}
	
	// 회원 검색 페이징
	public List<Map<String, Object>> memberListSearchPaging(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectList("member.memberListSearchPaging", map);
	}
	
	// 검색된 회원의 수
	public Map<String, Object> memberSearchCount(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("member.memberSearchCount", map);
	}
}
