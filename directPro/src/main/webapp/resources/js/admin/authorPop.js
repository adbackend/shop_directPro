$(function(){

	authorPop = new AuthorPop();
	
	//페이지 번호 클릭시, 페이지 이동
	$(".pageMaker_btn a").on("click", function(e){
		authorPop.pageNavigation(e);	
	});
	
	//작가 검색 버튼
	$(".search_btn").on("click", function(e){
		authorPop.authorSearch(e);
	});
	
	//작가 클릭시
	$(".move").on("click", function(e){
		authorPop.authorSelect(e);
	});
	
	
		
}); //function end

function AuthorPop(){
	
	let moveForm = document.getElementById("moveForm");
	
	//페이지 번호 클릭시, 페이지 이동
	this.pageNavigation = function(e){
	
		
		e.preventDefault();
		
		
		let hrefValue = e.target.href;
		document.getElementById("pageNum").value = hrefValue.split("/").reverse()[0];

		console.log(hrefValue);
		
		moveForm.submit();
		
	}
	
	//작가 검색
	this.authorSearch = function(e){
		
		e.preventDefault();
		
		let keyword = document.getElementById("search_keyword").value;
	
		if(keyword == ""){
			alert("검색값을 입력하세요.");
			return false;
		}
		
		document.getElementById("search_pageNum").value = 1;
		searchForm.submit();
		
	}
	
	//작가 선택시 
	this.authorSelect = function(e){
	
		e.preventDefault();
		
		let authorId = e.target.dataset.id;
		let authorName = e.target.dataset.name
		
		opener.document.getElementById("authorId").value = authorId; //부모창에 전달
		opener.document.getElementById("authorName").value = authorName; //부모창에 전달
		
		window.close();
	}
	





}