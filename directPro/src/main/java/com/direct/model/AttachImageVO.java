package com.direct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachImageVO {
	

	private String uploadPath; //경로
	private String uuid; //uuid
	private String fileName; //파일이름
	
	private int bookId; //상품아이디

}
