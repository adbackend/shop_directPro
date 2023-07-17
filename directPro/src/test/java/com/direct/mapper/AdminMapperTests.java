package com.direct.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.BookVO;
import com.direct.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTests {
	
	@Autowired
	private AdminMapper mapper;
	
	@Test
	public void bookEnrollTest() throws Exception{
		
		BookVO bookVO = new BookVO();
		
		bookVO.setBookName("책이름 test");
		bookVO.setAuthorId(41);
		bookVO.setPubleYear("2023-07-14");
		bookVO.setPublisher("출판사 test");
		bookVO.setCateCode("0231");
		bookVO.setBookPrice(9999);
		bookVO.setBookStock(99);
		bookVO.setBookDiscount(0.23);
		bookVO.setBookIntro("책소개 test");
		bookVO.setBookContents("책목자 test");
		
		mapper.bookEnroll(bookVO);
		
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

}
