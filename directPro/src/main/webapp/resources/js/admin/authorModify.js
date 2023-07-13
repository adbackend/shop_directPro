$(function(){

	authroModify = new AuthroModify();
	
	//작가 상세로 이동
	$("#cancelBtn").on("click", function(e){
	
		authroModify.authorList(e);	
	
	});
	
	//작가 수정
	$("#modifyBtn").on("click", function(e){
		authroModify.authorModify(e);
	});
	
}); //function end



function AuthroModify(){
	
	let moveForm = document.getElementById("moveForm");
	
	//작가 상세
	this.authorList = function(e){
		e.preventDefault();
		
		moveForm.action = "/admin/authorDetail";
		moveForm.submit();
		
	}
	
	let modifyForm = document.getElementById("modifyForm");
	
	//작가 수정
	this.authorModify = function(e){
		e.preventDefault();
		
		let authorName = document.getElementById("authorName").value;
		let authorIntro = document.getElementById("authorIntro").value;
		
		let nameCk = false;
		let introCk = false;
		
		if(!authorName){ //입력값이 없으면
			document.getElementById("warn_authorName").style.display = "block";	
		}else{
			document.getElementById("warn_authorName").style.display = "none";
			nameCk = true; 	
		}
		
		if(!authorIntro){ //입력값이 없으면
			document.getElementById("warn_authorIntro").style.display = "block";	
		}else{
			document.getElementById("warn_authorIntro").style.display = "none";
			introCk = true;	
		}
		
		if(nameCk && introCk){
			modifyForm.submit();
		}else{
			return false;
		}
		
	}

}

