package com.direct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direct.mapper.AdminMapper;
import com.direct.model.BookVO;
import com.direct.model.CateVO;
import com.direct.model.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;
	
	//상품 등록
	@Transactional
	@Override
	public void bookEnroll(BookVO bookVO) {
		
		log.info("bookEnroll...");
		adminMapper.bookEnroll(bookVO);
		
		if(bookVO.getImageList() == null || bookVO.getImageList().size() <= 0) {
			return ;
		}
		
		bookVO.getImageList().forEach(attach -> {
			attach.setBookId(bookVO.getBookId());
			adminMapper.imageEnroll(attach);
		});
		
	}
	
	//카테고리 목록
	@Override
	public List<CateVO> cateList() {
		return adminMapper.cateList();
	}
	
	//상품 목록
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		return adminMapper.goodsGetList(cri);
	}
	
	//상품 총 갯수
	@Override
	public int goodsGetTotal(Criteria cri) {
		return adminMapper.goodsGetTotal(cri);
	}
	
	//상품상세
	@Override
	public BookVO goodsGetDetail(int bookId) {
		return adminMapper.goodsGetDetail(bookId);
	}
	
	//상품수정
	@Transactional
	@Override
	public int goodsModify(BookVO bookVO) {
		
		log.info("goodsModify... 상품수정");
		
		int result = adminMapper.goodsModify(bookVO); // 상품 수정
		
		if(result == 1 && bookVO.getImageList() != null && bookVO.getImageList().size() > 0) {
			
			adminMapper.deleteImageAll(bookVO.getBookId()); //해당 상품의 기존 이미지 삭제
			
			bookVO.getImageList().forEach(attach -> {
				
				attach.setBookId(bookVO.getBookId());
				
				adminMapper.imageEnroll(attach);
				
			});
		}
		
		return result;
	}
	
	//상품 삭제
	@Override
	public int goodsDelete(int bookId) {
		
		return adminMapper.goodsDelete(bookId);
		
	}

}
