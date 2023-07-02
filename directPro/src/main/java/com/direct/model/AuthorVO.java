package com.direct.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorVO {

	private int authorId; //작가 ID
	
	private String authorName; //작가이름
	
	private String nationId; //국가 ID
	
	private String nationName; //작가 국가
	
	private String authorIntro; //작가소개
	
	private Date regDate; //등록날짜
	
	private Date updateDate; //수정날짜
	
}
