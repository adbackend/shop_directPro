$(function(){
	
	join = new Join();
	
	//가입 버튼을 눌렀을때
	$(".join_button").on("click", function(){
		join.signUp();
	});
	
	//아이디 중복 검사
	$(".id_input").on("propertychange change keyup paste input", function(){
		join.idDuplidate();
	});
	
	//이메일 인증
	$(".mail_check_input").on("click", function(){
		join.mailAuth();
	});
	
}); //function end


function Join(){

	//회원가입
	this.signUp = function(){
		
		$("#join_form").attr("action", "/member/join");
		$("#join_form").submit();
		
	} //function end
	
	//아이디 중복체크
	this.idDuplidate = function(){
		let memberId = document.getElementsByClassName("id_input")[0].value;
		let data = { memberId : memberId }
		
		$.ajax({
			type : "POST",
			url : "/member/memberIdChk",
			data : data,
			success : function(result){
				
				if(result == "success"){
					document.getElementsByClassName("id_input_re_1")[0].style.display = "inline-block";		
					document.getElementsByClassName("id_input_re_2")[0].style.display = "none";		
				}else{
					document.getElementsByClassName("id_input_re_1")[0].style.display = "none";		
					document.getElementsByClassName("id_input_re_2")[0].style.display = "inline-block";	
				}
			}
		}); // ajax end
		
	} //function end
	
	//이메일 인증
	this.mailAuth = function(){
		
		let mail = document.getElementsByClassName("mail_input")[0].value;
		
		$.ajax({
			type : "GET",
			url : "mailCheck?mail=" + mail
		
		
		}); //ajax end
	
	} //function end
	
	
	
}