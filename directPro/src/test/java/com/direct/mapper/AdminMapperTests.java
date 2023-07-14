package com.direct.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.BookVO;

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

}
