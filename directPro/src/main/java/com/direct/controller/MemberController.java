package com.direct.controller;

import java.security.SecureRandom;
import java.security.SecureRandomParameters;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	public String mailCheckGET(String mail) throws Exception{
		log.info("이메일 데이터 전송 확인");
		log.info("인증번호 : " + mail);
		
		SecureRandom random = new SecureRandom();
		int mailAuthNumber = random.nextInt(888888)+111111; //난수 생성
		
		//이메일 전송
		String from ="test@gmail.com";
		String to = mail;
		String title = "회원가입 인증 이메일 입니다.";
		String content =
				"홈페이지를 방문해 주셔서 감사합니다." +
				"<br><br>" +
				"인증번호는 " + mailAuthNumber + " 입니다. " +
				"<br>" + 
				"해당 인증번호를 확인란에 입력해주세요.";
		
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(title);
			helper.setText(content, true);
			
			mailSender.send(message);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String mailAuth = Integer.toString(mailAuthNumber); 
		
		return mailAuth;
		
	}
	
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		
		log.info("로그인 페이지 진입");
		
	}	

}
