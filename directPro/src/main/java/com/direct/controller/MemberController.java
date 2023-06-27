package com.direct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	//회원가입 페이지 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void loginGET() {
		
		log.info("회원가입 페이지 진입");
		
	}
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		
		log.info("로그인 페이지 진입");
		
	}	

}
