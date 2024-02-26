package pigi.admin.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.MemberBean;

@Service("adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService {

	@Resource(name = "adminMemberDAO")
	private AdminMemberDAO adminMemberDAO;

	@Override
	public Map<String, Object> selectMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return adminMemberDAO.selectMemberId(map);
	}

	@Override
	public List<Map<String, Object>> memberList() throws Exception {
		return adminMemberDAO.memberList();
	}

	@Override
	public void updateMemberAdmin(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RANK", member.getRANK());
		map.put("BLOCK", member.getBLOCK());
		map.put("ADDRESS1", member.getADDRESS1());
		map.put("ADDRESS2", member.getADDRESS2());
		map.put("POSTCODE", member.getPOSTCODE());
		map.put("PHONE", member.getPHONE());
		map.put("MOBILE", member.getMOBILE());
		
		map.put("EMAIL", member.getEMAIL());
		
		adminMemberDAO.updateMemberAdmin(map);
	}

	@Override
	public void deleteMember(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		adminMemberDAO.deleteMember(map);
	}

	@Override
	public List<Map<String, Object>> memberListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminMemberDAO.memberListPaging(map);
	}

	@Override
	public int memberCount() throws Exception {
		Map<String, Object> countMap = adminMemberDAO.memberCount();
		
		return Integer.parseInt(String.valueOf(countMap.get("COUNT")));
	}

	@Override
	public List<Map<String, Object>> memberListSearchPaging(String CONDITION, String KEYWORD, int START, int END)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", CONDITION);
		map.put("KEYWORD", KEYWORD);
		map.put("START", START);
		map.put("END", END);
		
		return adminMemberDAO.memberListSearchPaging(map);
	}

	@Override
	public int memberSearchCount(String CONDITION, String KEYWORD) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", CONDITION);
		map.put("KEYWORD", KEYWORD);		
		
		Map<String, Object> countMap = adminMemberDAO.memberSearchCount(map);
		
		return Integer.parseInt(String.valueOf(countMap.get("COUNT")));
	}
}