package com.direct.mapper;

import java.util.HashMap;
import java.util.List;

import com.direct.model.AuthorVO;
import com.direct.model.Criteria;
import com.direct.model.NationVO;

public interface AuthorMapper {
	
	public void authorEnroll(AuthorVO authorVO); //작가 등록
	
	public List<HashMap<String, NationVO>> nationCodes(); //국가코드
	
	public List<AuthorVO> authorGetList(Criteria cri); //작가 목록
	
	public int authorGetTotal(Criteria cri); //작가 총 갯수
	
	public AuthorVO authorGetDetail(int authorId); //작가상세
	
	public int authorModify(AuthorVO authroVO); //작가수정
	
	public int authorDelete(int authorId); //작가 삭제

}
