package pigi.common.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 회원리스트
	public List<Map<String, Object>> memberList() throws Exception {
		return sqlSessionTemplate.selectList("member.memberList");
	}
	
	// 이메일 중복확인, 로그인 기능, 회원정보수정폼
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("member.selectMemberId", map);
	}
	
	// 회원가입 성공
	public void insertMember(Map<String, Object> map) throws Exception{
		sqlSessionTemplate.insert("member.insertMember",map);
	}
	
	// 회원정보찾기
	public Map<String,Object> selectMemberJumin(Map<String,Object>map) throws Exception{
		return sqlSessionTemplate.selectOne("member.selectMemberJumin",map);
	}
	
	// 회원정보 수정기능
	public void updateMember(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.update("member.updateMember", map);
	}
	
	// 회원탈퇴기능
	public void deleteMember(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.delete("member.deleteMember", map);
	}
	
	// 고객센터
	public List<Map<String,Object>> selectQnaMemberId(Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectList("community.selectQnaMemberId",map);
	}
	
	// 후기
	public List<Map<String,Object>> selectReviewMemberId(Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectList("community.selectReviewMemberId",map);
	}
	
	// 주문 조회
	public List<Map<String,Object>> selectOrderMemberId(Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectList("order.selectOrderMemberId",map);
	}
	
}
