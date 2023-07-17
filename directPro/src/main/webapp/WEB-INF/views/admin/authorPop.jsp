<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가 관리</title>
<link rel="stylesheet" href="/resources/css/admin/authorPop.css">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="/resources/js/admin/authorPop.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	let mresult = ${modify_result};
	
	checkmResult(mresult);
});

function checkmResult(mresult){
	if(mresult == '1'){
		alert("작가 정보 수정 완료");
	}else if(mresult == '0'){
		alert("작가 수정 실패");
	}
}
</script>

<body>
	<div class="subject_name_warp">
		<span>작가 선택</span>
	</div>
	<div class="content_wrap">
           	<!-- 게시물 표 영역 -->
			<div class="author_table_wrap">
           		<!-- 게시물 O -->
           		<c:if test="${listCheck != 'empty'}">
           			<div class="table_exist">
                    	<table class="author_table">
                    		<thead>
                    			<tr>
                    				<td class="th_column_1">작가 번호</td>
                    				<td class="th_column_2">작가 이름</td>
                    				<td class="th_column_3">작가 국가</td>
                    			</tr>
                    		</thead>
                    		<c:forEach items="${list}" var="list">
                    		<tr>
                    			<td><c:out value="${list.authorId}"></c:out> </td>
                    			<td>
                    				<a class="move" data-id="<c:out value='${list.authorId}'/>" data-name="<c:out value='${list.authorName}'/>">
	                    				<c:out value="${list.authorName}"></c:out>
                    				</a>
                    			</td>
                    			<td><c:out value="${list.nationName}"></c:out> </td>
                    		</tr>
                    		</c:forEach>
                    	</table>
                	</div>                			
           		</c:if>
           		<!-- 게시물 x -->
           		<c:if test="${listCheck == 'empty'}">
           			<div class="table_empty">
           				등록된 작가가 없습니다.
           			</div>
           		</c:if>
           		
                <!-- 검색 영역 -->
                <div class="search_wrap">
                	<form id="searchForm" action="/admin/authorPop" method="get">
                		<div class="search_input">
                			<input type="text" id="search_keyword" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                			<input type="hidden" id="search_pageNum" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                			<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                			<button class='btn search_btn'>검 색</button>
                		</div>
                	</form>
                </div>
                
                <!-- 페이지 이동 인터페이스 영역 -->
                <div class="pageMaker_wrap" >
                
                    <ul class="pageMaker">
                    
                    	<!-- 이전 버튼 -->
                    	<c:if test="${pageMaker.prev}">
                    		<li class="pageMaker_btn prev">
                    			<a href="${pageMaker.startPage - 1}">이전</a>
                    		</li>
                    	</c:if>
                    	
                    	<!-- 페이지 번호 -->
                    	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
                    		<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
                    			<a href="${num}">${num}</a>
                    		</li>
                    	</c:forEach>
                    	
                    	<!-- 다음 버튼 -->
                    	<c:if test="${pageMaker.next}">
                    		<li class="pageMaker_btn next">
                    			<a href="${pageMaker.endPage + 1 }">다음</a>
                    		</li>
                    	</c:if>
                    	
                    </ul>
                    
                </div>               		
           		
				<form id="moveForm" action="/admin/authorPop" method="get">
					<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
					<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>               		
			</div>
		</div>
              
</body>
</html>
