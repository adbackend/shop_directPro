package com.direct.mapper;

import java.util.HashMap;
import java.util.List;

import com.direct.model.AuthorVO;
import com.direct.model.NationVO;

public interface AuthorMapper {
	
	public void authorEnroll(AuthorVO authorVO); //작가 등록
	
	public List<HashMap<String, NationVO>> nationCodes(); //코드

}
