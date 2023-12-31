package com.direct.service;

import java.util.HashMap;
import java.util.List;

import com.direct.model.AuthorVO;
import com.direct.model.Criteria;
import com.direct.model.NationVO;

public interface AuthorService {
	
	public void authorEnroll(AuthorVO author) throws Exception; //작가 등록
	
	public List<HashMap<String, NationVO>> nationCodes() throws Exception; //코드
	
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception; //작가 목록
	
	public int authorGetTotal(Criteria cri) throws Exception; //작가 총 갯수
	
	public AuthorVO authorGetDetail(int authorId)  throws Exception; //작가 상세
	
	public int authorModify(AuthorVO authorVO) throws Exception; //작가 수정
	
	public int authorDelete(int authorId) throws Exception; //작가 삭제

}
