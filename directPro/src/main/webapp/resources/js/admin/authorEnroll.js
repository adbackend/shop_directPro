$(function(){

	authorEnroll = new AuthorEnroll();
	
	//작가등록버튼
	$("#enrollBtn").on("click", function(){
	
		authorEnroll.enrollBtn();
	});
	
	//취소버튼
	$("#cancelBtn").on("click", function(){
		location.href = "/admin/authorManage";
	});
	
	
	
}); //function end


function AuthorEnroll(){
	
	//작가등록
	this.enrollBtn = function() {
	
		//통과 유무 변수
		let nameCheck = false; //작가이름
		let nationCheck = false; //소속국가
		let introCheck = false; //작가소개
		
		let wAuthorName = document.getElementById("warn_authorName");
		let wNationId = document.getElementById("warn_nationId");
		let wAuthorIntro = document.getElementById("warn_authorIntro");
		
		let authorName = document.getElementById("authorName").value; //작가이름
		let nationId = document.getElementById("nationId").value; //소속국가
		let authorIntro = document.getElementById("authorIntro").value; //작가소개
		
		//작가이름 빈값체크
		if(authorName === ""){
			wAuthorName.style.display = "block";
			nameCheck = false;
		}else {
			wAuthorName.style.display = "none";
			nameCheck = true;
		}
		
		//국가공란 빈값체크
		if(nationId === "none"){
			wNationId.style.display = "block";
			nationCheck = false;
		}else{
			wNationId.style.display = "none";
			nationCheck = true;
		}
		
		//작가소개 빈값체크
		if(authorIntro === ""){
			wAuthorIntro.style.display = "block";
			introCheck = false;
		}else{
			wAuthorIntro.style.display = "none";
			introCheck = true;
		}
		
		if(nameCheck && nationCheck && introCheck){
			
			document.getElementById("enrollForm").submit();
			
		}else{
			return ;
		}
		
	
	}

}