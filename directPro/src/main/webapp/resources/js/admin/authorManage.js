$(function(){

	let result = "${enroll_result}";

	authorManage = new AuthorManage();
	
	checkResult(result);
	
	//페이지 번호 클릭시, 페이지 이동
	$(".pageMaker_btn a").on("click", function(e){
		authorManage.pageNavigation(e);	
	});
	
	//작가 검색 버튼
	$(".search_btn").on("click", function(e){
		authorManage.authorSearch(e);
	});
	
	
}); //function end

function AuthorManage(){

	let pageForm = document.getElementById("pageForm"); //페이징

	//페이지 번호 클릭시, 페이지 이동
	this.pageNavigation = function(e){
	
		e.preventDefault();
		
		let hrefValue = e.target.href;
		
		document.getElementById("pageNum").value = hrefValue.split("/").reverse()[0];
		
		pageForm.action = "/admin/authorManage";
		pageForm.submit();
		
	} //function end
	
	
	let searchForm = document.getElementById("searchForm"); //작가 검색
	
	//작가 검색
	this.authorSearch = function(e){
		
		let keyword = document.getElementById("keyword").value;
	
		e.preventDefault();
		
		if(keyword == ""){
			alert("검색값을 입력하세요.");
			return false;
		}
		
		document.getElementById("search_pageNum").value = 1;
		searchForm.submit();
	}
	
} //function end


function checkResult(result){
	
	if(result === ""){
		return ;
	}
	
	alert("작가"+ result +"을 등록하였습니다.");
}
