package com.direct.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.AttachImageVO;
import com.direct.model.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTests {
	
	@Autowired
	AdminService adminService;
	
	//상품등록, 이미지 등록 테스트
	@Test
	public void bookEntollTest() {
		
		BookVO book = new BookVO();
		
		//상품 정보
		book.setBookName("service 테스트");
		book.setAuthorId(16521);
		book.setPubleYear("2021-03-18");
		book.setPublisher("출판사");
		book.setCateCode("203003");
		book.setBookPrice(20000);
		book.setBookStock(300);
		book.setBookDiscount(0.23);
		book.setBookIntro("책 소개 ");
		book.setBookContents("책 목차 ");
		
		//이미지 정보
		List<AttachImageVO> imageList = new ArrayList<AttachImageVO>();
		
		AttachImageVO image1 = new AttachImageVO();
		AttachImageVO image2 = new AttachImageVO();
		
		image1.setFileName("테스트1 이름");
		image1.setUploadPath("테스트1 경로");
		image1.setUuid("테스트1 uuid");
		
		image2.setFileName("테스트2 이름aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		image2.setUploadPath("테스트2 경로");
		image2.setUuid("테스트2 uuid");
		
		imageList.add(image1);
		imageList.add(image2);
		
		book.setImageList(imageList);
		
		adminService.bookEnroll(book);
		
		System.out.println("등록된 vo : " + book);
		
		
		
	}

}
