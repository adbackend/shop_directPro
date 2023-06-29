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
	$(".mail_check_button").on("click", function(){
		console.log("a");
		join.mailAuth();
	});
	
	//인증번호 일치 여부
	$(".mail_check_input").on("propertychange change keyup paste input", function(){
	
		let inputCode = document.getElementsByClassName("mail_check_input")[0].value; //입력코드
		let checkResult = document.getElementById("mail_check_input_box_warn"); //비교결과
		
		console.log(origiMailCode + "ㄹㅇ 인증코드"); 
		if(inputCode == origiMailCode){
			checkResult.innerText = "인증번호가 일치 합니다.";
			checkResult.style.color = "green";
		}else{
			checkResult.innerText = "인증번호를 다시 확인해 주세요.";
			checkResult.style.color = "red";
		}
	});
	
}); //function end


let origiMailCode = "";

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
		
		let mail = document.getElementsByClassName("mail_input")[0].value; //입력한 이메일
		let checkBox = document.getElementsByClassName("mail_check_input")[0]; //인증번호 입력란
		let boxWrap = document.getElementsByClassName("mail_check_input_box")[0]; //인증번호 입력란 박스
		
		console.log(mail);
		
		$.ajax({
			type : "GET",
			url : "mailCheck?mail=" + mail,
			success : function(data){
			
				checkBox.disabled = false;
				boxWrap.id = "mail_check_input_box_true";
				
				origiMailCode = data;
			}
		
		
		}); //ajax end
	
	} //function end
	
	
	
}