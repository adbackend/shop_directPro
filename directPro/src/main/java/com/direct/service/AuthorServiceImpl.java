package com.direct.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.direct.mapper.AuthorMapper;
import com.direct.model.AuthorVO;

public class AuthorServiceImpl implements AuthorService{

	@Autowired
    AuthorMapper authorMapper;
	
	//작가등록
	@Override
	public void authorEnroll(AuthorVO authorVO) throws Exception {
		authorMapper.authorEnroll(authorVO);
	}
}
