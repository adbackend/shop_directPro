package com.direct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direct.mapper.AttachMapper;
import com.direct.model.AttachImageVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttachServiceImpl implements AttachService{

	@Autowired
	private AttachMapper attachMapper;
		
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {

		return attachMapper.getAttachList(bookId);
	}

}
