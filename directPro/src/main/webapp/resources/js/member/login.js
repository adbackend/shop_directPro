$(function(){
	
	login = new Login();
	
	$(".login_button").on("click", function(){
		login.loginProcess();
	});
	
}); //function end

function Login(){
	
	//로그인
	this.loginProcess = function(){
		let form = document.getElementById("login_form");
		
		form.action = "/member/login.do";
		form.submit();
	}
	
	
} //function end