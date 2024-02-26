package pigi.common.member;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.MemberBean;

@Service("joinService")
public class JoinServiceImpl implements JoinService {

	@Resource(name = "memberDAO")
	private MemberDAO memberDAO;

	@Override
	public Map<String, Object> selectMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return memberDAO.selectMemberId(map);
	}

	@Override
	public void insertMember(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		map.put("PASSWORD", member.getPASSWORD());
		map.put("NAME", member.getNAME());
		map.put("JUMIN1", member.getJUMIN1());
		map.put("JUMIN2", member.getJUMIN2());
		map.put("ADDRESS1", member.getADDRESS1());
		map.put("ADDRESS2", member.getADDRESS2());
		map.put("POSTCODE", member.getPOSTCODE());
		map.put("PHONE", member.getPHONE());
		map.put("MOBILE", member.getMOBILE());
		map.put("RANK", member.getRANK());
		map.put("BLOCK", member.getBLOCK());
		
		memberDAO.insertMember(map);		
	}
	
	
}
