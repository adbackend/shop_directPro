package com.direct.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {
	
	@Autowired
	private MemberMapper memberMapper;
	
	//회원가입 테스트
	@Test
	public void memberJoin() throws Exception{
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("spring_test");
		memberVO.setMemberPw("spring_test");
		memberVO.setMemberName("spring_test");
		memberVO.setMemberMail("spring_test");
		memberVO.setMemberAddr1("spring_test");
		memberVO.setMemberAddr2("spring_test");
		memberVO.setMemberAddr3("spring_test");
		
		memberMapper.memberJoin(memberVO);
		
	}
	
	//아이디 중복검사
	@Test
	public void memberDuplicationTest() throws Exception{
		
		String id = "admin1"; //존재하는 아이디
		String id2 = "admin1111"; //존재하지 않는 아이디
		
		memberMapper.idCheck(id);
		memberMapper.idCheck(id2);
	}

}
