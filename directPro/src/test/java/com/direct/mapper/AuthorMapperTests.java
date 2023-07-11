package com.direct.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.model.AuthorVO;
import com.direct.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTests {
 
    @Autowired
    private AuthorMapper mapper;
    
    //작가 등록 테스트
    @Test
    public void authorEnroll() throws Exception{
        
        AuthorVO author = new AuthorVO();
        
        author.setNationId("01");
        author.setAuthorName("테스트");
        author.setAuthorIntro("테스트 소개");
        
        mapper.authorEnroll(author);
        
    }
    
    //작가 목록 테스트
    @Test
    public void authorGetListTest() throws Exception{
    	
    	Criteria cri = new Criteria(3,10);
    	
    	List<AuthorVO> list = mapper.authorGetList(cri);
    	System.out.println(list.size());
    	
    	for(int i=0; i<list.size(); i++) {
    		
    		
    		System.out.println(list.get(i));
    		
    	}
    	
    } 
    
    
    //작가 목록 keyword추가 테스트
    @Test
    public void authorGetListTest2() throws Exception{
    	
    	Criteria cri = new Criteria(3,10);
    	
    	cri.setKeyword("테스트");
    	
    	List<AuthorVO> list = mapper.authorGetList(cri);
    	System.out.println(list.size());
    	
    	for(int i=0; i<list.size(); i++) {
    		
    		
    		System.out.println(list.get(i));
    		
    	}
    	
    } 
    
    //작가 총 갯수
    @Test
    public void authorGetTotalTest() throws Exception{
    	
    	Criteria cri = new Criteria();
    	cri.setKeyword("테스트");
    	
    	int total = mapper.authorGetTotal(cri);
    	
    	System.out.println(total);
    	
    }
    
}