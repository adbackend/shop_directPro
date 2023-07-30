package com.direct.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.AttachImageVO;
import com.direct.model.BookVO;
import com.direct.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTests {
	
	@Autowired
	private AdminMapper mapper;
	
	//상품등록
	@Test
	public void bookEnrollTest() throws Exception{
		
		BookVO bookVO = new BookVO();
		
		bookVO.setBookName("책이름 test2");
		bookVO.setAuthorId(16521);
		bookVO.setPubleYear("2023-07-14");
		bookVO.setPublisher("출판사 test");
		bookVO.setCateCode("204003");
		bookVO.setBookPrice(9999);
		bookVO.setBookStock(99);
		bookVO.setBookDiscount(0.23);
		bookVO.setBookIntro("책소개 test");
		bookVO.setBookContents("책목자 test");
		
		System.out.println("before bookVO : " + bookVO);
		
		mapper.bookEnroll(bookVO);
		
		System.out.println("after bookVO : " + bookVO);
		
	}
	
	//카테고리 목록
	@Test
	public void cateListTest() throws Exception{
		System.out.println(mapper.cateList());
	}
	
	//상품목록, 상품 총갯수
	@Test
	public void goodsGetListTest() throws Exception{
		
		Criteria cri = new Criteria();
		cri.setKeyword("");
		
		//상품목록
		List<BookVO> list = mapper.goodsGetList(cri);
		for(int i=0; i<list.size(); i++) {
			System.out.println("상품목록 결과.. " + i + " :" + list.get(i));
		}
		
		//상품 총 갯수
		
		int result = mapper.goodsGetTotal(cri);
		System.out.println("상품총갯수 : " + result);
	}
	
	//상품상세
	@Test
	public void goodsGetDetailTest() {
		
		int bookId = 300;
		
		System.out.println(mapper.goodsGetDetail(bookId));
		
	}
	
	//상품 수정
	@Test
	public void goodsModifyTest() {
		
		BookVO bookVO = new BookVO();
		
		bookVO.setBookId(155);
		bookVO.setBookName("mapper 테스트");
		bookVO.setAuthorId(61);
		bookVO.setPubleYear("2023-07-01");
		bookVO.setPublisher("수정 출판사");
		bookVO.setCateCode("204000");
		bookVO.setBookPrice(20000);
		bookVO.setBookStock(300);
		bookVO.setBookDiscount(0.11);
		bookVO.setBookIntro("책소개 수정");
		bookVO.setBookContents("책목차 수정");

		mapper.goodsModify(bookVO);
		
	}
	
	//상품 삭제
	@Test
	public void goodsDeleteTest() {
		
		int bookId = 156;
		
		int result = mapper.goodsDelete(bookId);
		
		if(result == 1) {
			System.out.println(bookId + "글 내용 삭제 성공");
		}
		
	}

	//이미지 업로드
	@Test
	public void imageEnrollTest() {
		
		AttachImageVO vo = new AttachImageVO();
		
		vo.setBookId(128);
		vo.setFileName("test");
		vo.setUploadPath("test");
		vo.setUuid("test");
		
		mapper.imageEnroll(vo);
		
	}
}









