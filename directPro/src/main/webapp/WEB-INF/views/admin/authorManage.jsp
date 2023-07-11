<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가 관리</title>
<link rel="stylesheet" href="/resources/css/admin/authorManage.css">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="/resources/js/admin/authorManage.js"></script>
</head>
</head>
<body>

<%@ include file="../includes/header.jsp" %>

    <div class="admin_content_wrap">
    	<div class="admin_content_subject"><span>작가 관리</span></div>
    	<div class="author_table_wrap">
    		<table class="author_table">
    			<thead>
    				<tr>
    					<td class="th_column_1">작가 번호</td>
    					<td class="th_column_2">작가 이름</td>
    					<td class="th_column_3">작가 국가</td>
    					<td class="th_column_4">등록 날짜</td>
    					<td class="th_column_5">수정 날짜</td>
    				</tr>
    				<c:choose>
	    				<c:when test="${listCheck != 'empty'}">
		    				<c:forEach var="list" items="${list}">
		    					<tr>
		    						<td><c:out value="${list.authorId}"/></td>
		    						<td><c:out value="${list.authorName}"/></td>
		    						<td><c:out value="${list.nationName}"/></td>
		    						<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
		    						<td><fmt:formatDate value="${list.updateDate}" pattern="yyyy-MM-dd"/></td>
		    					</tr>
		    				</c:forEach>
	    				</c:when>
	    				<c:otherwise>
	    					<tr>
	    						<td colspan="5">검색된 결과가 없습니다.</td>
	    						
	    					</tr>
	    				</c:otherwise>
					</c:choose>
    			</thead>
    		</table>
    	</div>
    	
    	<!-- 검색 영역 -->
		<div class="search_wrap">
			<form id="searchForm" action="/admin/authorManage" method="get">
				<div class="search_input">
					<input type="text" id="keyword" name="keyword" value="${pageMaker.cri.keyword}"/>
					<input type="hidden" id="search_pageNum" name="pageNum" value="${pageMaker.cri.pageNum}"/>
					<input type="hidden" id="search_amount" name="amount" value="${pageMaker.cri.amount}"/>
					<button class="btn search_btn">검색</button>
				</div>
			</form>
		</div>    	 
    	
	    <!-- 페이지 이동 -->
	    <div class="pageMaker_wrap">
			<ul class="pageMaker">	 			
		 		<!-- 이전버튼 -->
		 		<c:if test="${pageMaker.prev}">
		 			<li class="pageMaker_btn prev">
		 				<a href="${pageMaker.startPage-1}">이전</a>
		 			</li>
		 		</c:if>
		 		
		 		<!-- 페이지 번호 -->
		 		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
		 			<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active' : ''}">
		 				<a href="${num}">${num}</a>
		 			</li>
		 		</c:forEach>
		 		
		 		<!-- 다음 페이지 -->
		 		<c:if test="${pageMaker.next}">
		 			<li class="pageMaker_btn next">
		 				<a href="${pageMaker.endPage+1}">다음</a>
		 			</li>
		 		</c:if>
	 		</ul>
	    </div>
	    <!-- 페이지 이동 끝 -->
	    
	    <form id="pageForm" method="get">
	    	<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum}"/>
	    	<input type="hidden" id="amount" name="amount" value="${pageMaker.cri.amount}"/>
	    	<input type="hidden" id="keyword" name="keyword" value="${pageMaker.cri.keyword}"/>
	    </form>
    	
    	
    </div>
              
<%@ include file="../includes/footer.jsp" %>
</body>
</html>
