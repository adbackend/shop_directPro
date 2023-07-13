package com.direct.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direct.mapper.AuthorMapper;
import com.direct.model.AuthorVO;
import com.direct.model.Criteria;
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
	
	//국가 코드
	@Override
	public List<HashMap<String, NationVO>> nationCodes() throws Exception {
		return authorMapper.nationCodes();
	}
	
	//작가 목록
	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		return authorMapper.authorGetList(cri);
	}
	
	//작가 총 갯수
	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		return authorMapper.authorGetTotal(cri);
	}
	
	//작가 상세
	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		return authorMapper.authorGetDetail(authorId);
	}
	
	
	//작가 수정
	@Override
	public int authorModify(AuthorVO authorVO) throws Exception {
		return authorMapper.authorModify(authorVO);
	}
	
}
