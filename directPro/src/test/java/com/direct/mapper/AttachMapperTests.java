package com.direct.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.AttachImageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AttachMapperTests {
	
	@Autowired
	private AttachMapper attachMapper;
	
	//이미지
	@Test
	public void getAttachListTest() {
		
		int bookId = 481;
		
		System.out.println("이미지 정보 : " + attachMapper.getAttachList(bookId));
		
	}

}
