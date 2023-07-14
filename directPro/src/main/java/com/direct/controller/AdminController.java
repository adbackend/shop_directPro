package com.direct.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.direct.model.AuthorVO;
import com.direct.model.BookVO;
import com.direct.model.Criteria;
import com.direct.model.NationVO;
import com.direct.model.PageMakeDTO;
import com.direct.service.AdminService;
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
	
	@Autowired
	private AdminService adminService;
	
	public AdminController() {
	}
	
	@PostConstruct
	public void init() throws Exception{
		
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
	
	//상풍등록 POST
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO bookVO, RedirectAttributes rttr) {
		
		log.info("상품 등록 POST");
		
		adminService.bookEnroll(bookVO);
		
		rttr.addFlashAttribute("enroll_result", bookVO.getBookName());
		
		return "redirect:/admin/goodsManage";
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
	
	//작가관리
	@RequestMapping(value = "/authorManage", method = RequestMethod.GET)
	public void authorManageGET(Criteria cri, Model model) throws Exception{
		
		log.info("작가관리 페이지 접속");
		
		//작가목록
		List<AuthorVO> list = authorService.authorGetList(cri);
		
		//페이지 이동 
		int total = authorService.authorGetTotal(cri);
		
		if(list.isEmpty()) { //작가가 존재 하지않는 경우
			model.addAttribute("listCheck", "empty");
		}else { //작가가 존재
			model.addAttribute("list", list);
		}
		
		PageMakeDTO pageMaker = new PageMakeDTO(cri, total);
		
		model.addAttribute("pageMaker", pageMaker);
		
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
	
	//작가 상세
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception{
		
		log.info("authorDetail...." + authorId);
		
		//작가 관리 페이지 정보
		model.addAttribute("cri", cri);
		
		//작가 상세
		model.addAttribute("nationCodes", nationCodes);
		
		System.out.println(authorService.authorGetDetail(authorId));
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
	}
	
	//작가 정보 수정
	@PostMapping("/authorModify")
	public String authorModify(AuthorVO authorVO, RedirectAttributes rttr) throws Exception{
		
		log.info("authorModifyPOST ..." + authorVO);
		
		int result = authorService.authorModify(authorVO);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
	

}









