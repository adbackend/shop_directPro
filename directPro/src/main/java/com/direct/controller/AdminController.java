package com.direct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	//관리자 메인페이지
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		
		log.info("관리자 메인 페이지");
		
	}
	
	//상품등록
	@RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGET() throws Exception{
		
		log.info("상품 등록 페이지 접속");
	}
	
	//상품관리
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public void goodsManageGET() throws Exception{
		
		log.info("상품관리 페이지 접속");
	}
	
	//작가등록
	@RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
	public void authorEnrollGET() throws Exception{
		
		log.info("작가등록 페이지 접속");
	}
	
	//작가관리
	@RequestMapping(value = "/authorManage", method = RequestMethod.GET)
	public void authorManageGET() throws Exception{
		
		log.info("작가관리 페이지 접속");
	}

}