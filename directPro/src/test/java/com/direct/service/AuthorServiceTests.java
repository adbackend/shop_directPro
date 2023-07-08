package com.direct.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceTests {
	
	//의존성 주입
	@Autowired
	private AuthorService authorService;
	
	@Test
	public void authorEnrollTest() throws Exception{
		
		AuthorVO authorVO = new AuthorVO();
		
		authorVO.setNationId("01");
		authorVO.setAuthorName("테스트1");
		authorVO.setAuthorIntro("테스트 소개1");
		
		authorService.authorEnroll(authorVO);
		
	}

}
