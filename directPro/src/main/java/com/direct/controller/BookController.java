package com.direct.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.direct.mapper.AttachMapper;
import com.direct.model.AttachImageVO;
import com.direct.service.AttachService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	
	@Autowired
	private AttachService attachService;

	// 메인페이지로 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPageGET() {

	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		
		log.info("fileName = {}", fileName);
		
		File file = new File("C:\\CYJ_PRO\\upload_file\\" + fileName);
		
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			log.info("MIME Type : {}", Files.probeContentType(file.toPath()));
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//이미지 정보 반환
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		
		log.info("getAttachList 진입, bookId = {}", bookId);
		
		return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(bookId), HttpStatus.OK);
	}
	
}
