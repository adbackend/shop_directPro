package com.direct.service;

import java.util.List;

import com.direct.model.AttachImageVO;

public interface AttachService {

	public List<AttachImageVO> getAttachList(int bookId); //이미지 데이터
	
}
