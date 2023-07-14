package com.direct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direct.mapper.AdminMapper;
import com.direct.model.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;
	
	//상품 등록
	@Override
	public void bookEnroll(BookVO bookVO) {
		log.info("bookEnroll...");
		adminMapper.bookEnroll(bookVO);
	}

}
