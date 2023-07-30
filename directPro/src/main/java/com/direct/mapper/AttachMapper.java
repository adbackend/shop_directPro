package com.direct.mapper;

import java.util.List;

import com.direct.model.AttachImageVO;

public interface AttachMapper {
	
	public List<AttachImageVO> getAttachList(int bookId); //이미지 데이터

}
