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
		join.mailAuth();
	});
	
	//인증번호 일치 여부
	$(".mail_check_input").on("propertychange change keyup paste input", function(){
	
		let inputCode = document.getElementsByClassName("mail_check_input")[0].value; //입력코드
		let checkResult = document.getElementById("mail_check_input_box_warn"); //비교결과
		
		if(inputCode == origiMailCode){
			checkResult.innerText = "인증번호가 일치 합니다.";
			checkResult.style.color = "green";
			mailnumCheck = true;
		}else{
			checkResult.innerText = "인증번호를 다시 확인해 주세요.";
			checkResult.style.color = "red";
			mailnumCheck = false;
		}
	});
	
	/* 비밀번호 확인 일치 유효성 검사 */
	$('.pwck_input').on("propertychange change keyup paste input", function(){
	    
	    let pw = document.getElementById("pw_input").value; //비밀번호
	    let pwck = document.getElementById("pwck_input").value; //비밀번호 확인
	    
	    document.getElementsByClassName("final_pwck_ck")[0].style.display = "none";
	    
	    if(pw == pwck){
	    	document.getElementsByClassName("pwck_input_re_1")[0].style.display = "block";
	    	document.getElementsByClassName("pwck_input_re_2")[0].style.display = "none";
	    	pwckcorCheck = true;
	    }else{
	    	document.getElementsByClassName("pwck_input_re_1")[0].style.display = "none";
	    	document.getElementsByClassName("pwck_input_re_2")[0].style.display = "block";
	    	pwckcorCheck = false;
	    }
	    
	}); 
	
	
}); //function end


let origiMailCode = ""; // 발급된 이메일 인증코드

/* 유효성 검사 통과유무 변수 */
let idCheck = false;       // 아이디
let idckCheck = false;     // 아이디 중복 검사
let pwCheck = false;       // 비번
let pwckCheck = false;     // 비번 확인
let pwckcorCheck = false;  // 비번 확인 일치 확인
let nameCheck = false;     // 이름
let mailCheck = false;     // 이메일
let mailnumCheck = false;  // 이메일 인증번호 확인
let addressCheck = false;   // 주소

function Join(){

	//회원가입
	this.signUp = function(){
		
		let id = document.getElementById("id_input").value; //아이디
		let pw = document.getElementById("pw_input").value; //비밀번호
		let pwck = document.getElementById("pwck_input").value; //비밀번호 확인
		let name = document.getElementById("user_input").value; //이름
		let mail = document.getElementById("mail_input").value; //메일
		let addr = document.getElementById("address_input_3").value; //주소
		
		//아이디
		if(id == ""){
			document.getElementsByClassName("final_id_ck")[0].style.display = "block";
			idCheck = false;
		}else{
			document.getElementsByClassName("final_id_ck")[0].style.display = "none";
			idCheck = true;
		}
		
		//비밀번호
		if(pw == ""){
			document.getElementsByClassName("final_pw_ck")[0].style.display = "block";
			pwCheck = false;
		}else{
			document.getElementsByClassName("final_pw_ck")[0].style.display = "none";
			pwCheck = true;
		}
		
		//비밀번호 확인
		if(pwck == ""){
			document.getElementsByClassName("final_pwck_ck")[0].style.display = "block";
            pwckCheck = false;
        }else{
        	document.getElementsByClassName("final_pwck_ck")[0].style.display = "none";
            pwckCheck = true;
        }
        
        //이름
        if(name == ""){
        	document.getElementsByClassName("final_name_ck")[0].style.display = "block";
            nameCheck = false;
        }else{
        	document.getElementsByClassName("final_name_ck")[0].style.display = "none";
            nameCheck = true;
        }
		
		//이메일
		if(mail == ""){
        	document.getElementsByClassName("final_mail_ck")[0].style.display = "block";
            mailCheck = false;
        }else{
        	document.getElementsByClassName("final_mail_ck")[0].style.display = "none";
            mailCheck = true;
        }
        
        //주소
        if(addr == ""){
        	document.getElementsByClassName("final_addr_ck")[0].style.display = "block";
            addressCheck = false;
        }else{
        	document.getElementsByClassName("final_addr_ck")[0].style.display = "none";
            addressCheck = true;
        }
		
		//최종 유효성 검사
        if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&mailCheck&&mailnumCheck&&addressCheck ){
 		
			$("#join_form").attr("action", "/member/join");
			$("#join_form").submit();
        }    
		
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
					idckCheck = true;
				}else{
					document.getElementsByClassName("id_input_re_1")[0].style.display = "none";		
					document.getElementsByClassName("id_input_re_2")[0].style.display = "inline-block";	
					idckCheck = false;
				}
			}
		}); // ajax end
		
	} //function end
	
	
	
	
	//이메일 인증
	this.mailAuth = function(){
		
		let mail = document.getElementsByClassName("mail_input")[0].value; //입력한 이메일
		let checkBox = document.getElementsByClassName("mail_check_input")[0]; //인증번호 입력란
		let boxWrap = document.getElementsByClassName("mail_check_input_box")[0]; //인증번호 입력란 박스
		
		let mailValidWarnMsg = document.getElementsByClassName("mail_input_box_warn")[0]; //이메일 경고
		
		if(mailFormCheck(mail)){
			mailValidWarnMsg.innerText = "이메일이 전송되었습니다. 확인해주세요.";
			mailValidWarnMsg.style.display = "inline-block";
		}else{
			mailValidWarnMsg.innerText = "올바르지 못한 이메일 형식입니다.";
			mailValidWarnMsg.style.display = "inline-block";
			return false;
		}
		
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
	
} // function end

//이메일 형식 유효성
function mailFormCheck(email){

	let form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
 	return form.test(email);
}


//주소 API
function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    addr += extraAddr;
                
                } else {
                    addr += '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address_input_1").value = data.zonecode;
                document.getElementById("address_input_2").value = addr;
                
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address_input_3").readOnly = false;
                document.getElementById("address_input_3").focus();
            }
        }).open();
}


