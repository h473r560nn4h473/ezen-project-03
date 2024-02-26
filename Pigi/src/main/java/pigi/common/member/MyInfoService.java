package pigi.common.member;

import java.util.List;
import java.util.Map;

import pigi.model.MemberBean;
import pigi.model.CommunityBean;
import pigi.model.OrderBean;

public interface MyInfoService {

	public Map<String, Object> selectMemberId(MemberBean member) throws Exception;
	
	public void updateMember(MemberBean member) throws Exception;
	
	public void deleteMember(MemberBean member) throws Exception;
	
	public List<Map<String,Object>> selectQnaMemberId(MemberBean member) throws Exception;
	
	public List<Map<String,Object>> selectReviewMemberId(MemberBean member) throws Exception;
	
	public List<Map<String,Object>> selectOrderMemberId(MemberBean member) throws Exception;
}
