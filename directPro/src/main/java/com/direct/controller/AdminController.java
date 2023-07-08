package com.direct.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.direct.model.AuthorVO;
import com.direct.model.NationVO;
import com.direct.service.AuthorService;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	public static List<HashMap<String, NationVO>> nationCodes;
	
	@Autowired
	private AuthorService authorService;
	
	public AdminController() {
	}
	
	@PostConstruct
	public void init() {
		
		log.info("공통코드 관리");
		nationCodes = authorService.nationCodes();
		
		log.info("nationCodes = {}", nationCodes);
		
	}
	
	
	
	
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
	public void authorEnrollGET(Model model) throws Exception{
		
		log.info("작가등록GET 페이지 접속");

		model.addAttribute("nationCodes", nationCodes);
	}
	
	//작가등록
	@RequestMapping(value = "/authorEnroll.do", method = RequestMethod.POST)
	public String authorEnrollPOST(AuthorVO authorVO, Model model, RedirectAttributes rttr) throws Exception{
		
		log.info("작가등록POST 페이지 접속");
		log.info("authorVO= {} ",  authorVO);
		
		authorService.authorEnroll(authorVO);
		
		rttr.addFlashAttribute("enroll_result", authorVO.getAuthorName());
		
		return "redirect:/admin/authorManage";
	}
	
	//작가관리
	@RequestMapping(value = "/authorManage", method = RequestMethod.GET)
	public void authorManageGET() throws Exception{
		
		log.info("작가관리 페이지 접속");
	}

}
