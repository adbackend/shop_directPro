package com.direct.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direct.mapper.AuthorMapper;
import com.direct.model.AuthorVO;
import com.direct.model.NationVO;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
    AuthorMapper authorMapper;
	
	//작가 등록
	@Override
	public void authorEnroll(AuthorVO authorVO) throws Exception {
		authorMapper.authorEnroll(authorVO);
	}
	
	
	@Override
	public List<HashMap<String, NationVO>> nationCodes() {
		return authorMapper.nationCodes();
	}
	
	
}
