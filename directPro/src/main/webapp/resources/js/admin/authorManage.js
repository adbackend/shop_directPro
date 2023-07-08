$(function(){

	let result = "${enroll_result}";

	authorManage = new AuthorManage();
	
	
	checkResult(result);
	
	
}); //function end

function checkResult(result){
	
	if(result === ""){
		return ;
	}
	
	alert("작가"+ result +"을 등록하였습니다.");
}

function AuthorManage(){

}