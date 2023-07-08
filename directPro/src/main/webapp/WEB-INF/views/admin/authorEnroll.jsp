<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/admin/authorEnroll.css">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="/resources/js/admin/authorEnroll.js"></script>
</head>
</head>
<body>
 
<%@ include file="../includes/header.jsp" %>
    <div class="admin_content_wrap">
        <div class="admin_content_subject"><span>작가 등록</span></div>
        <div class="admin_content_main">
        	<form id="enrollForm" action="/admin/authorEnroll.do" method="post">
        		<div class="form_section">
        			<div class="form_section_title">
        				<label>작가 이름</label>
        			</div>
        			<div class="form_section_content">
        				<input id="authorName" name="authorName"/>
        				<span id="warn_authorName">작가 이름을 입력해주세요.</span>
        			</div>
        		</div>
        		<div class="form_section">
				<div class="form_section_title">
					<label>소속 국가</label>
				</div>
				<div class="form_section_content">
					<select name="nationId" id="nationId">
							<option value="none" selected>===선택===</option>
						<c:forEach var="row" items="${nationCodes}">
							<option value="${row.nationId}">${row.nationName}</option>
						</c:forEach>
					</select>
					<span id="warn_nationId">소속 국가를 입력해주세요.</span>
				</div>
        		</div>
        		<div class="form_section">
        			<div class="form_section_title">
        				<label>작가 소개</label>
        			</div>
        			<div class="form_section_content">
        				<input type="text" id="authorIntro" name="authorIntro"/>
        				<span id="warn_authorIntro">작가 소개를 입력해주세요.</span>
        			</div>
        		</div>
        	</form>
        	
        	<div class="btn_section">
        		<button id="cancelBtn" class="btn">취소</button>
        		<button id="enrollBtn" class="btn enroll_btn">등록</button>
        	</div>
        </div>
     </div>
                
<%@ include file="../includes/header.jsp" %>
               
        
        
 
</body>
</html>
