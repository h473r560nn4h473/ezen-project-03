package pigi.common.member;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pigi.model.MemberBean;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name = "memberDAO")
	private MemberDAO memberDAO;

	@Override
	public Map<String, Object> selectMemberId(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("EMAIL", member.getEMAIL());
		
		return memberDAO.selectMemberId(map);
	}

	@Override
	public Map<String, Object> selectMemberJumin(MemberBean member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("JUMIN1", member.getJUMIN1());
		map.put("JUMIN2", member.getJUMIN2());
		
		return memberDAO.selectMemberJumin(map);
	}
	
	
}
