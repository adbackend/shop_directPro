package com.direct.model;

import lombok.Data;

@Data
public class CateVO {
	
	private int tier; //카테고리 등급
	
	private String cateName; //카테고리 이름
	
	private String cateCode; //카테고리 넘버
	
	private String cateParent; //상위 카테고리
}
