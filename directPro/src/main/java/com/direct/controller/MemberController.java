package com.direct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.direct.model.MemberVO;
import com.direct.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void loginGET() {
		
		log.info("회원가입 페이지 진입");
		
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO memberVO) throws Exception{
		
		log.info("join 진입");
		
		memberService.memberJoin(memberVO);
		
		log.info("join service 성공");
		
		return "redirect:/main";
	}
	
	//아이디 중복 검사
	@ResponseBody
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	public String memberIdChkPOST(String memberId) throws Exception{
		log.info("memberIdChk() 진입");
		
		int result = memberService.idCheck(memberId);
		
		log.info("결과값 = " + result);
		
		if(result != 0) {
			return "fail"; //중복 아이디가 존재
		}else {
			return "success";
		}
		
	}
	
	//이메일 인증
	@ResponseBody
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	public void mailCheckGET(String mail) throws Exception{
		log.info("이메일 데이터 전송 확인");
		log.info("인증번호 : " + mail);
		
	}
	
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		
		log.info("로그인 페이지 진입");
		
	}	

}
