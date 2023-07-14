<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>

<link rel="stylesheet" href="/resources/css/admin/goodsEnroll.css">

 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/38.1.0/classic/ckeditor.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script src="/resources/js/admin/goodsEnroll.js"></script>

<script type="text/javascript">
$(document).ready(function(){
// 	let eResult = ${enroll_result};
// 	checkResult(eResult);
	
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
		
	$("#publeYear").datepicker(config);
	
	
});
function checkResult(result){
	
	if(result === ''){
		return ;
	}
	
	alert("상품'"+ eResult +"'을 등록하였습니다.");
	
}
</script>  

<body>
<%@ include file="../includes/header.jsp" %>

    <div class="admin_content_wrap">
    	<div class="admin_content_subject"><span>상품등록</span></div>
    	<div class="admin_content_main">
	         <form action="/admin/goodsEnroll" method="post" id="enrollForm">
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>책 제목</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input name="bookName">
	         		</div>
	         	</div>
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>작가</label>
	         		</div>
	         		<div class="form_section_content">
						<input id="authorName" readonly="readonly"/>
						<input type="hidden" id="authorId" name="authorId"/>
						<button class="authorId_btn">작가선택</button>
	         		</div>
	         	</div>            
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>출판일</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input id="publeYear" name="publeYear" autocomplete="off" readonly="readonly">
	         		</div>
	         	</div>            
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>출판사</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input name="publisher">
	         		</div>
	         	</div>             
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>책 카테고리</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input name="cateCode">
	         		</div>
	         	</div>          
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>상품 가격</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input name="bookPrice" value="0">
	         		</div>
	         	</div>               
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>상품 재고</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input name="bookStock" value="0">
	         		</div>
	         	</div>          
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>상품 할인율</label>
	         		</div>
	         		<div class="form_section_content">
	         			<input name="bookDiscount" value="0">
	         		</div>
	         	</div>          		
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>책 소개</label>
	         		</div>
	         		<div class="form_section_content">
						<textarea name="bookIntro" id="bookIntro_textarea"></textarea>	         			
	         		</div>
	         	</div>        		
	         	<div class="form_section">
	         		<div class="form_section_title">
	         			<label>책 목차</label>
	         		</div>
	         		<div class="form_section_content">
						<textarea name="bookContents" id="bookContents_textarea"></textarea>
	         		</div>
	         	</div>
	         </form>
	        <div class="btn_section">
	        	<button id="cancelBtn" class="btn">취 소</button>
		    	<button id="enrollBtn" class="btn enroll_btn">등 록</button>
		    </div> 
       </div>  
    </div>
              
<%@ include file="../includes/footer.jsp" %>
</body>
</html>