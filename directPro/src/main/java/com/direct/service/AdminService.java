package com.direct.service;

import java.util.List;

import com.direct.model.BookVO;
import com.direct.model.CateVO;
import com.direct.model.Criteria;

public interface AdminService {
	
	public void bookEnroll(BookVO bookVO); //상품등록
	
	public List<CateVO> cateList(); //카테고리 목록
	
	public List<BookVO> goodsGetList(Criteria cri); //상품 목록
	
	public int goodsGetTotal(Criteria cri); //상품 총 갯수
	
	public BookVO goodsGetDetail(int bookId); //상품 상세

}
