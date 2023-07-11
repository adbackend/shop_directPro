package com.direct.model;

import lombok.Data;

@Data
public class PageMakeDTO {
	
	private int startPage; //시작 페이지
	
	private int endPage; //끝 페이지
	
	private boolean prev, next; //이전페이지, 다음페이지 존재 유무

	private int total; //전체 게시물수
	
	private Criteria cri; //현재페이지, 페이지당 게시물수 정보
	
	public PageMakeDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10; //마지막 페이지
		
		this.startPage = this.endPage - 9; //시작 페이지
		
		int realEnd = (int)(Math.ceil(total*1.0)/cri.getAmount()); //전체 마지막 페이지
		
		//전체마지막 페이지가 화면에 보이는 마지막페이지보다 작을경우
		if(realEnd < this.endPage) { 
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}
	
}
