package pigi.common.member;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.CommunityBean;
import pigi.model.MemberBean;
import pigi.model.OrderBean;

@Service("myInfoService")
public class MyInfoServiceImpl implements MyInfoService {

	@Resource(name = "memberDAO")
	private MemberDAO memberDAO;

	@Override
	public Map<String, Object> selectMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return memberDAO.selectMemberId(map);
	}

	@Override
	public void updateMember(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("PASSWORD", member.getPASSWORD());
		map.put("NAME", member.getNAME());
		map.put("ADDRESS1", member.getADDRESS1());
		map.put("ADDRESS2", member.getADDRESS2());
		map.put("POSTCODE", member.getPOSTCODE());
		map.put("PHONE", member.getPHONE());
		map.put("MOBILE", member.getMOBILE());
		
		map.put("EMAIL", member.getEMAIL());
		
		memberDAO.updateMember(map);		
	}

	@Override
	public void deleteMember(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		memberDAO.deleteMember(map);
	}

	@Override
	public List<Map<String, Object>> selectQnaMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return memberDAO.selectQnaMemberId(map);
	}

	@Override
	public List<Map<String, Object>> selectReviewMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return memberDAO.selectReviewMemberId(map);
	}

	@Override
	public List<Map<String, Object>> selectOrderMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return memberDAO.selectOrderMemberId(map);
	}
	
	
}
