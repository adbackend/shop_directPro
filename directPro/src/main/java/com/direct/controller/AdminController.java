package com.direct.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.direct.model.AttachImageVO;
import com.direct.model.AuthorVO;
import com.direct.model.BookVO;
import com.direct.model.CateVO;
import com.direct.model.Criteria;
import com.direct.model.NationVO;
import com.direct.model.PageMakeDTO;
import com.direct.service.AdminService;
import com.direct.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

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
	public void goodsEnrollGET(Model model) throws Exception{
		
		log.info("상품 등록 페이지 접속");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<CateVO> list = adminService.cateList();
		
		String cateList = objectMapper.writeValueAsString(list);
		
		log.info("json 형식 변경전.. " + list);
		log.info("json 형식 변경후.. " + cateList);
		
		model.addAttribute("cateList", cateList);
		
	}
	
	//상풍등록 POST
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO bookVO, RedirectAttributes rttr) {
		
		log.info("상품 등록 POST 진입, bookVO = {}", bookVO);
		
		adminService.bookEnroll(bookVO);
		
		rttr.addFlashAttribute("enroll_result", bookVO.getBookName());
		
		return "redirect:/admin/goodsManage";
	}
	
	//첨부파일 업로드
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		log.info("파일업로드POST...");
		
		log.info("파일 이름 : " + uploadFile[0].getOriginalFilename());
		log.info("파일 타입 : " + uploadFile[0].getContentType());
		
		//이미지 파일 체크
		for(MultipartFile multipartFile : uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				
				type = Files.probeContentType(checkfile.toPath());
				log.info("mime type: {} ", type);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) { // 이미지 파일이 아닐경우
				
				List<AttachImageVO> list = null;
				
				return new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.BAD_REQUEST);
			}
		}
		
		String uploadFolder = "C:\\CYJ_PRO\\upload_file";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		
		//폴더 생성
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		//이미지 정보 객체
		List<AttachImageVO> list = new ArrayList<AttachImageVO>();
		
		for(MultipartFile multipartFile : uploadFile) {
			
			//이미지 정보 객체
			AttachImageVO vo = new AttachImageVO();
			
			
			String uploadFileName = multipartFile.getOriginalFilename(); //파일이름
			String uuid = UUID.randomUUID().toString();
			
			vo.setFileName(uploadFileName); //uuid 적용전
			vo.setUploadPath(datePath);
			
			uploadFileName = uuid + "_" + uploadFileName; //uuid 적용 파일 이름
			
			File saveFile = new File(uploadPath, uploadFileName); // new File("파일위치", "파일이름")
			
			vo.setUuid(uuid);
			
			
			//파일저장
			try {
				
				multipartFile.transferTo(saveFile);

				//썸네일 방법1
				/* 
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName); //썸네일 이미지
				
				BufferedImage bo_image = ImageIO.read(saveFile); // BufferedImage을 변경
				
				//비율
				double ration = 3;
				
				//넓이 높이
				int width = (int)(bo_image.getWidth() / ration);
				int height = (int)(bo_image.getHeight() / ration);
						
				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
				
				Graphics2D graphic = bt_image.createGraphics();
				graphic.drawImage(bo_image, 0, 0, width, height, null);
				
				ImageIO.write(bt_image, "jpg", thumbnailFile);
				 */
				
				//썸네일 방법2
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName); //썸네일 이미지
				
				BufferedImage bo_image = ImageIO.read(saveFile);
				
				double ration = 3;
				
				//넓이 높이
				int width = (int)(bo_image.getWidth() / ration);
				int height = (int)(bo_image.getHeight() / ration);
				
				
				Thumbnails.of(saveFile)
				.size(width, height)
				.toFile(thumbnailFile);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			list.add(vo);
			
		} //for end
		
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
		
	} // method end
	
	//첨부파일 이미지 삭제
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		log.info("첨부파일 삭제POST fileName = {}", fileName);
		
		File file = null;
		
		try {
			log.info("Decoder fileName = {}", URLDecoder.decode(fileName, "UTF-8"));
			
			file = new File("C:\\CYJ_PRO\\upload_file\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete(); //썸네일 삭제
			
			String originFileName = file.getAbsolutePath().replace("s_", ""); 
			
			log.info("originFileName = {}, " + originFileName);
			
			file = new File(originFileName);
			file.delete();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}

	
	//상품관리
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public void goodsManageGET(Criteria cri, Model model) throws Exception{
		
		log.info("상품관리 페이지 진입");
		
		List<BookVO> list = adminService.goodsGetList(cri); //상품목록
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		}else {
			model.addAttribute("listCheck", "empty");
			return ;
		}
		
		model.addAttribute("pageMaker", new PageMakeDTO(cri, adminService.goodsGetTotal(cri)));
		
	}
	
	//상품상세
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws Exception{
		
		log.info("상품상세 페이지 진입");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		model.addAttribute("cateList", objectMapper.writeValueAsString(adminService.cateList()));
		
		model.addAttribute("cri", cri);
		
		BookVO bookVO = adminService.goodsGetDetail(bookId);
		
		model.addAttribute("goodsInfo", bookVO);
	}
	
	//상품 수정
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO bookVO, RedirectAttributes rttr) throws Exception{
		
		log.info("goodModifyPOST.." + bookVO);
		
		int result = adminService.goodsModify(bookVO);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	//상품 삭제
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		log.info("goodsDelete POST..");
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		log.info("상품 삭제 완료 bookId = {}", bookId);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	
	//작가 팝업창
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		log.info("작가 팝업창");
			
		cri.setAmount(5); // 5개 목록 출력
		List<AuthorVO> list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		}else {
			model.addAttribute("listCheck", "empty");
		}
		
		model.addAttribute("pageMaker", new PageMakeDTO(cri, authorService.authorGetTotal(cri)));
		
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
	
	//작가 삭제
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		
		log.info("authorDeletePOST... 작가 = {}" ,  authorId);
		
		int result = 0;
		
		try {
			
			result = adminService.goodsDelete(result);
			
		}catch (Exception e) {
			e.printStackTrace();
			result = 2;
			
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
		}
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	

}









