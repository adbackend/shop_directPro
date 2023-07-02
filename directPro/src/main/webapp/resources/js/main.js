$(function(){
	
	main = new Main();
	
	//로그아웃 버튼 클릭시
	$("#gnb_logout_button").on("click", function(){
		main.logout();
	});
	
}); //function end

function Main(){

	//로그아웃
	this.logout = function(){
	
		$.ajax({
			type : "POST",
			url : "/member/logout.do",
			success : function(data){
			
				alert("로그아웃 성공");
				document.location.reload(); //새로고침 해야 세션 변경사항이 화면에 반영
				
			}
			
		}); // ajax end
	
	} // function end

}