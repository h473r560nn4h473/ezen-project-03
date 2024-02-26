package pigi.common.member;

import java.util.Map;

import pigi.model.MemberBean;

public interface LoginService {

	public Map<String, Object> selectMemberId(MemberBean member) throws Exception;
	
	public Map<String,Object> selectMemberJumin(MemberBean member) throws Exception;
}
