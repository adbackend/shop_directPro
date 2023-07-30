<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<link rel="stylesheet" href="/resources/css/admin/goodsDetail.css">
<script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>

<script src="/resources/js/admin/goodsDetail.js"></script>
<script type="text/javascript">
	
</script>
<body>
	<%@ include file="../includes/header.jsp"%>
	
	<div class="admin_content_wrap">
		<div class="admin_content_subject">
			<span>상품 상세</span>
		</div>

		<div class="admin_content_main">
			<input type="hidden" id="h_bookId" value="${goodsInfo.bookId}"/>
			<input type="hidden" id="cateList" value='${cateList}'/>
			<div class="form_section">
				<div class="form_section_title">
					<label>책 제목</label>
				</div>
				<div class="form_section_content">
					<input name="bookName" value="<c:out value="${goodsInfo.bookName}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>등록 날짜</label>
				</div>
				<div class="form_section_content">
					<input value="<fmt:formatDate value='${goodsInfo.regDate}' pattern='yyyy-MM-dd'/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>최근 수정 날짜</label>
				</div>
				<div class="form_section_content">
					<input value="<fmt:formatDate value='${goodsInfo.updateDate}' pattern='yyyy-MM-dd'/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>작가</label>
				</div>
				<div class="form_section_content">
					<input id="authorName_input" readonly="readonly" value="${goodsInfo.authorName }" disabled>

				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>출판일</label>
				</div>
				<div class="form_section_content">
					<input id="publeYear" name="publeYear" autocomplete="off" readonly="readonly" value="<c:out value="${goodsInfo.publeYear}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>출판사</label>
				</div>
				<div class="form_section_content">
					<input name="publisher" value="<c:out value="${goodsInfo.publisher}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>책 카테고리</label>
				</div>
				<div class="form_section_content">
					<div class="cate_wrap">
						<span>대분류</span> 
						<select id="cate1" class="cate1" disabled>
							<option value="none">선택</option>
						</select>
					</div>
					<div class="cate_wrap">
						<span>중분류</span> 
						<select id="cate2" class="cate2" disabled>
							<option value="none">선택</option>
						</select>
					</div>
					<div class="cate_wrap">
						<input type="hidden" id="cateCode" value="${goodsInfo.cateCode}"/>
						<span>소분류</span> 
						<select id="cate3" class="cate3" name="cateCode" disabled>
							<option value="none">선택</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 가격</label>
				</div>
				<div class="form_section_content">
					<input name="bookPrice" value="<c:out value="${goodsInfo.bookPrice}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 재고</label>
				</div>
				<div class="form_section_content">
					<input name="bookStock" value="<c:out value="${goodsInfo.bookStock}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 할인율</label>
				</div>
				<div class="form_section_content">
					<input type="hidden" id="discount" value="${goodsInfo.bookDiscount}"/>
					<input id="discount_interface"  maxlength="2" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>책 소개</label>
				</div>
				<div class="form_section_content bit">
					<textarea name="bookIntro" id="bookIntro_textarea" disabled>${goodsInfo.bookIntro}</textarea>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>책 목차</label>
				</div>
				<div class="form_section_content bct">
					<textarea name="bookContents" id="bookContents_textarea" disabled>${goodsInfo.bookContents}</textarea>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품이미지</label>
				</div>
				<div class="form_section_content">
					<div id="uploadResult">
					</div>
				</div>
			</div>
			
			<div class="btn_section">
				<button id="cancelBtn" class="btn">상품 목록</button>
				<button id="modifyBtn" class="btn enroll_btn">수정</button>
			</div>
		</div>


		<form id="moveForm" method="get">
			<input type="hidden" name="pageNum" value="${cri.pageNum}">
			<input type="hidden" name="amount" value="${cri.amount}">
			<input type="hidden" name="keyword" value="${cri.keyword}">
		</form>

	</div>
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>