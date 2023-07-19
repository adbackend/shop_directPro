<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" href="/resources/css/admin/goodsManage.css">

<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
	
<script src="/resources/js/admin/goodsManage.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	//let result = "'"+${enroll_result}+"'";
	
	//goodsManage = new GoodsManage();
	
	//checkResult(result);
	let modify_result = '${modify_result}';
	
	if(modify_result == 1){
		alert("수정 완료");
	}
	
	/* 삭제 결과 경고창 */
	let delete_result = '${delete_result}';
	
	if(delete_result == 1){
		alert("삭제 완료");
	}
	
});
	
function checkResult(result){
	
	if(result === ""){
		return ;
	}
	
	alert("작가"+ result +"을 등록하였습니다.");
}	
</script>
<body>
	<%@ include file="../includes/header.jsp"%>

	<div class="admin_content_wrap">
		<div class="admin_content_subject">
			<span>상품관리</span>
		</div>
		<div class="goods_table_wrap">
			<!-- 상품 리스트 O -->
			<c:if test="${listcheck != 'empty'}">
				<table class="goods_table">
					<thead>
						<tr>
							<td class="th_column_1">상품 번호</td>
							<td class="th_column_2">상품 이름</td>
							<td class="th_column_3">작가 이름</td>
							<td class="th_column_4">카테고리</td>
							<td class="th_column_5">재고</td>
							<td class="th_column_6">등록날짜</td>
						</tr>
					</thead>
					<c:forEach items="${list}" var="list">
						<tr>
							<td><c:out value="${list.bookId}"></c:out></td>
							<td>
								<a class="move" href="<c:out value='${list.bookId}'/>">
									<c:out value="${list.bookName}"></c:out>
								</a>
							</td>
							<td><c:out value="${list.authorName}"></c:out></td>
							<td><c:out value="${list.cateName}"></c:out></td>
							<td><c:out value="${list.bookStock}"></c:out></td>
							<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<!-- 상품 리스트 X -->
			<c:if test="${listCheck == 'empty'}">
				<div class="table_empty">등록된 작가가 없습니다.</div>
			</c:if>
		</div>

		<!-- 검색 영역 -->
		<div class="search_wrap">
			<form id="searchForm" action="/admin/goodsManage" method="get">
				<div class="search_input">
					<input type="text" id="keyword" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
					<input type="hidden" id="search_pageNum" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
					<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
					<input type="hidden" name="type" value="G">
					<button class='btn search_btn'>검 색</button>
				</div>
			</form>
		</div>

		<!-- 페이지 이름 인터페이스 영역 -->
		<div class="pageMaker_wrap">
			<ul class="pageMaker">

				<!-- 이전 버튼 -->
				<c:if test="${pageMaker.prev }">
					<li class="pageMaker_btn prev">
						<a href="${pageMaker.startPage -1}">이전</a>
					</li>
				</c:if>

				<!-- 페이지 번호 -->
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" var="num">
					<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active':''}">
						<a href="${num}">${num}</a>
					</li>
				</c:forEach>

				<!-- 다음 버튼 -->
				<c:if test="${pageMaker.next}">
					<li class="pageMaker_btn next"><a
						href="${pageMaker.endPage + 1 }">다음</a>
					</li>
				</c:if>
			</ul>
		</div>

		<form id="pageForm" method="get">
			<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum}">
			<input type="hidden" id="amount" name="amount" value="${pageMaker.cri.amount}">
			<input type="hidden" id="keyword" name="keyword" value="${pageMaker.cri.keyword}">
		</form>

	</div>

	<%@ include file="../includes/footer.jsp"%>
</body>
</html>