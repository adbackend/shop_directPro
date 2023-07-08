package com.direct.service;

import java.util.HashMap;
import java.util.List;

import com.direct.model.AuthorVO;
import com.direct.model.NationVO;

public interface AuthorService {
	
	public void authorEnroll(AuthorVO author) throws Exception; //작가 등록
	
	public List<HashMap<String, NationVO>> nationCodes(); //코드

}
