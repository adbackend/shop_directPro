$(function(){

	goodsManage = new GoodsManage();

	
	//페이지 번호 클릭시, 페이지 이동
	$(".pageMaker_btn a").on("click", function(e){
		goodsManage.pageNavigation(e);	
	});
	
	//상품 검색 버튼
	$(".search_btn").on("click", function(e){
		goodsManage.goodsSearch(e);
	});
	
	//상품 상세 버튼
	$(".move").on("click", function(e){
		goodsManage.goodsDetail(e);
	});
	
	
}); //function end

function GoodsManage(){

	let moveForm = document.getElementById("moveForm"); //페이징

	//페이지 번호 클릭시, 페이지 이동
	this.pageNavigation = function(e){
	
		e.preventDefault();
		
		let hrefValue = e.target.href;
		
		document.getElementById("pageNum").value = hrefValue.split("/").reverse()[0];
		
		moveForm.submit();
		
	} //function end
	
	
	let searchForm = document.getElementById("searchForm"); //작가 검색
	
	//상품 검색
	this.goodsSearch = function(e){
		
		let keyword = document.getElementById("keyword").value;
	
		e.preventDefault();
		
		if(keyword == ""){
			alert("검색값을 입력하세요.");
			return false;
		}
		
		document.getElementById("search_pageNum").value = 1;
		searchForm.submit();
		
	} //function end
	
	
	//상품 상세
	this.goodsDetail = function(e){
		e.preventDefault();
		
		let pageFormAu = document.getElementById("pageForm");
		let input = document.createElement("input");
		
		let urlValue = e.target.href.split("/").reverse()[0];
		
		input.type="hidden";
		input.id="bookId"		
		input.name="bookId";
		input.value=urlValue
				
		pageFormAu.appendChild(input);
		
		pageFormAu.action = "/admin/goodsDetail";
		pageFormAu.submit();
		
	} //function end
	
} //function end

