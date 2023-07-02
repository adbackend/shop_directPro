package com.direct.controller;

import java.security.SecureRandom;
import java.security.SecureRandomParameters;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	//회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void loginGET() {
		
		log.info("회원가입 페이지 진입");
		
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO memberVO) throws Exception{
		
		String rawPw = ""; //인코딩 전
		String encodePw = ""; //인코딩 후
		
		rawPw = memberVO.getMemberPw();
		encodePw = pwEncoder.encode(rawPw);
		
		memberVO.setMemberPw(encodePw);
		
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
		
		log.info("로그인 페이지 진입 (GET방식)");
		
	}
	
	//로그인 처리
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginPOST(HttpServletRequest request,
							MemberVO memberVO, 
							RedirectAttributes rttr) throws Exception{
		
		log.info("login 메서드 진입 (POST방식)");
		log.info(memberVO);
		
		HttpSession session = request.getSession();
		
		String rawPw = "";
		String encodePw = "";
		
		MemberVO member = memberService.memberLogin(memberVO);
		
		if(member != null) { //일치하는 아이디 존재시
			
			rawPw = memberVO.getMemberPw(); //사용자가 제출항 비밀번호
			encodePw = member.getMemberPw(); //db에서 가져온 인코딩된 비밀번호
			
			if(true == pwEncoder.matches(rawPw, encodePw)) { //비밀번호 일치여부 판단
				
				member.setMemberPw(""); //인코딩된 비밀번호 삭제(굳이 노출시킬 필요x)
				session.setAttribute("member", member);
				
				return "redirect:/main";
				
			}else {
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}
			
		}else { //일치하는 아이디 미존재(= 로그인실패)
			
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login";
			
		}
		
	} //method end
	
	//로그아웃 GET방식
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logoutMainGET(HttpServletRequest request) throws Exception{
		
		log.info("logoutMainGET 메서드 진입");
		
		HttpSession session = request.getSession();
		
		session.invalidate(); //세션 전체 무효화
		
		return "redirect:/main";
	}
	
	//로그아웃 POST방식
	@ResponseBody
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public void logoutPOST(HttpServletRequest request) throws Exception{
		
		log.info("비동기 로그아웃 메서드 진입");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
	}

}






