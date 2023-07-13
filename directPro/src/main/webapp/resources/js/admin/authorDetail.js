$(function(){

	authorDetail = new AuthorDetail();
	
	//작가목록으로 
	$("#cancelBtn").on("click", function(e){
	
		authorDetail.authorList(e);	
	
	});
	
	$("#modifyBtn").on("click", function(e){
		authorDetail.authorModify(e);
	});
	
}); //function end



function AuthorDetail(){
	
	let moveForm = document.getElementById("moveForm");
	
	//작가 목록
	this.authorList = function(e){
		e.preventDefault();
		
		document.getElementById("form_authorId").remove();
		moveForm.action = "/admin/authorManage";
		moveForm.submit();
		
	}
	
	//작가 수정
	this.authorModify = function(e){
		e.preventDefault();
		
		moveForm.action = "/admin/authorModify";
		moveForm.submit();
		
		
	}

}

