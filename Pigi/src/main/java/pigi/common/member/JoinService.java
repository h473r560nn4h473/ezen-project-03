package pigi.common.member;

import java.util.Map;

import pigi.model.MemberBean;

public interface JoinService {

	public Map<String, Object> selectMemberId(MemberBean member) throws Exception;
	
	public void insertMember(MemberBean member) throws Exception;
}