<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" href="/resources/css/admin/goodsModify.css">
<script
	src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>

<script src="/resources/js/admin/goodsModify.js"></script>
<script type="text/javascript">
const config = {
		dateFormat : "yy-mm-dd",
		showOn : "button",
		buttonText : "날짜선택",
		prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    yearSuffix: '년',
        changeMonth: true,
        changeYear: true
};
$(function(){
	$("#publeYear").datepicker(config);
});

			
	/* 캘린더 */
// 	$(function() {
// 		let publeYear = '${goodsInfo.publeYear}';
// 		let puble_length = publeYear.indexOf(" ");
// 		publeYear = publeYear.substring(0, puble_length);
// 		$( "input[name='publeYear']" ).datepicker(config);
// 		$( "input[name='publeYear']" ).datepicker('setDate', publeYear);
// 	});	
	
	
</script>
<body>
	<%@ include file="../includes/header.jsp"%>

	<div class="admin_content_wrap">
		<input type="hidden" id="cateList" value='${cateList}'/>
		<div class="admin_content_subject">
			<span>상품 수정</span>
		</div>
		<div class="admin_content_main">
			<form action="/admin/goodsModify" method="post" id="modifyForm">
				<div class="form_section">
					<div class="form_section_title">
						<label>책 제목</label>
					</div>
					<div class="form_section_content">
						<input name="bookName" value="${goodsInfo.bookName}"> 
						<span class="ck_warn bookName_warn">책 이름을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>작가</label>
					</div>
					<div class="form_section_content">
						<input id="authorName_input" readonly="readonly" value="${goodsInfo.authorName}"> 
						<input id="authorId_input" name="authorId" type="hidden" value="${goodsInfo.authorId}">
						<button class="authorId_btn">작가 선택</button>
						<span class="ck_warn authorId_warn">작가를 선택해주세요</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>출판일</label>
					</div>
					<div class="form_section_content">
						<input id="publeYear" name="publeYear" autocomplete="off" readonly="readonly" value="${goodsInfo.publeYear}">
						<span class="ck_warn publeYear_warn">출판일을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>출판사</label>
					</div>
					<div class="form_section_content">
						<input name="publisher" value="${goodsInfo.publisher}"> 
						<span class="ck_warn publisher_warn">출판사를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>책 카테고리</label>
					</div>
					<div class="form_section_content">
						<div class="cate_wrap">
							<span>대분류</span> 
							<select id="cate1" class="cate1">
								<option selected value="none">선택</option>
							</select>
						</div>
						<div class="cate_wrap">
							<span>중분류</span> 
							<select id="cate2" class="cate2">
								<option selected value="none">선택</option>
							</select>
						</div>
						<div class="cate_wrap">
							<span>소분류</span> 
							<input type="hidden" id="cateCode" value="${goodsInfo.cateCode}"/>
							<select id="cate3" class="cate3" name="cateCode">
								<option selected value="none">선택</option>
							</select>
						</div>
						<span class="ck_warn cateCode_warn">카테고리를 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 가격</label>
					</div>
					<div class="form_section_content">
						<input name="bookPrice" value="${goodsInfo.bookPrice}"> 
						<span class="ck_warn bookPrice_warn">상품 가격을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 재고</label>
					</div>
					<div class="form_section_content">
						<input name="bookStock" value="${goodsInfo.bookStock}"> 
						<span class="ck_warn bookStock_warn">상품 재고를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 할인율</label>
					</div>
					<div class="form_section_content">
						<input id="discount_interface" maxlength="2" value="0"> 
						<input id="discount" name="bookDiscount" type="hidden" value="${goodsInfo.bookDiscount}"> 
						<span class="step_val">할인가격 : <span id="span_discount" class="span_discount"></span></span> 
						<span class="ck_warn bookDiscount_warn">1~99 숫자를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>책 소개</label>
					</div>
					<div class="form_section_content bit">
						<textarea name="bookIntro" id="bookIntro_textarea">${goodsInfo.bookIntro}</textarea>
						<span class="ck_warn bookIntro_warn">책 소개를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>책 목차</label>
					</div>
					<div class="form_section_content bct">
						<textarea name="bookContents" id="bookContents_textarea">${goodsInfo.bookContents}</textarea>
						<span class="ck_warn bookContents_warn">책 목차를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품이미지</label>
					</div>
					<div class="form_section_content">
						<input type="file" id="fileItem" name="uploadFile" style="height : 30px;"/>
						<div id="uploadResult">
							
						</div>
					</div>
				</div>
				<input type="hidden" id="bookId" name='bookId' value="${goodsInfo.bookId}">
			</form>
			<div class="btn_section">
				<button id="cancelBtn" class="btn">취 소</button>
				<button id="modifyBtn" class="btn modify_btn">수 정</button>
				<button id="deleteBtn" class="btn delete_btn">삭 제</button>
			</div>
		</div>
		<form id="moveForm">
			<input type="hidden" name="pageNum" value="${cri.pageNum}"> 
			<input type="hidden" name="amount" value="${cri.amount}"> 
			<input type="hidden" name="keyword" value="${cri.keyword}"> 
			<input type="hidden" name='bookId' value="${goodsInfo.bookId}">
		</form>
	</div>
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>