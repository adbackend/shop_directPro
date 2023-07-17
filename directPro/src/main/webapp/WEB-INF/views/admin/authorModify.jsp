<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/authorModify.css">

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="/resources/js/admin/authorModify.js"></script>
</head>
<body>
<%@include file="../includes/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject">
    	<span>작가 상세</span>
    </div>
    <div class="admin_content_main">
    	<form id="modifyForm" action="/admin/authorModify" method="post">
       		<div class="form_section">
       			<div class="form_section_title">
       				<label>작가 번호</label>
       			</div>
       			<div class="form_section_content">
       				<input class="input_block" name="authorId" readonly="readonly" value="<c:out value='${authorInfo.authorId }'></c:out>">
       			</div>
       		</div>                    
       		<div class="form_section">
       			<div class="form_section_title">
       				<label>작가 이름</label>
       			</div>
       			<div class="form_section_content">
       				<input id="authorName" name="authorName" value="<c:out value='${authorInfo.authorName }'></c:out>" >
       				<span id="warn_authorName">작가 이름을 입력 해주세요.</span>
       			</div>
       		</div>
       		<div class="form_section">
       			<div class="form_section_title">
       				<label>소속 국가</label>
       			</div>
       			<div class="form_section_content">
       				<select name="nationId" >
       					<option value="none" disabled="disabled">=== 선택 ===</option>
       					<c:forEach var="row" items="${nationCodes}">
       						<option value="${row.nationId}"
       							<c:if test="${row.nationId eq authorInfo.nationId}">selected</c:if>>${row.nationName}
       						</option>
       					</c:forEach>
       				</select>
       			</div>
       		</div>
       		<div class="form_section">
       			<div class="form_section_title">
       				<label>작가소개</label>
       			</div>
       			<div class="form_section_content">
       				<textarea name="authorIntro" id="authorIntro"><c:out value='${authorInfo.authorIntro}'/></textarea>
       				<span id="warn_authorIntro">작가 소개를 입력 해주세요.</span>
       			</div>
       		</div>
       		<div class="form_section">
       			<div class="form_section_title">
       				<label>등록 날짜</label>
       			</div>
       			<div class="form_section_content">
       				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.regDate}" pattern="yyyy-MM-dd"/>">
       			</div>
       		</div>
       		<div class="form_section">
       			<div class="form_section_title">
       				<label>수정 날짜</label>
       			</div>
       			<div class="form_section_content">
       				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.updateDate}" pattern="yyyy-MM-dd"/>">
       			</div>
       		</div>
     		<div class="btn_section">
       			<button id="cancelBtn" class="btn">취소</button>
            	<button id="modifyBtn" class="btn modify_btn">수정</button>
            </div> 
        </form>
    </div>                    
</div>

<form id="moveForm" method="get">
	<input type="hidden" name="authorId" value='<c:out value="${authorInfo.authorId }"/>'>
	<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
	<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
	<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
</form>

<%@include file="../includes/footer.jsp" %>


</body>
</html>