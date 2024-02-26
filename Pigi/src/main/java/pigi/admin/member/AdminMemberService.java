package pigi.admin.member;

import java.util.List;
import java.util.Map;

import pigi.model.MemberBean;

public interface AdminMemberService {

	public Map<String, Object> selectMemberId(MemberBean member) throws Exception;
	
	public List<Map<String, Object>> memberList() throws Exception;
		
	public void updateMemberAdmin(MemberBean member) throws Exception;
	
	public void deleteMember(MemberBean member) throws Exception;	
	
	public List<Map<String, Object>> memberListPaging(int START, int END) throws Exception;
	
	public int memberCount() throws Exception;
	
	public List<Map<String, Object>> memberListSearchPaging(String CONDITION, String KEYWORD, int START, int END) throws Exception;
	
	public int memberSearchCount(String CONDITION, String KEYWORD) throws Exception;
}