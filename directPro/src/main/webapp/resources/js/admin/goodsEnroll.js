$(function(){

	/* 책 소개 */
	ClassicEditor
		.create(document.querySelector('#bookIntro_textarea'))
		.catch(error=>{
			console.error(error);
		});
		
	/* 책 목차 */	
	ClassicEditor
	.create(document.querySelector('#bookContents_textarea'))
	.catch(error=>{
		console.error(error);
	});
	

	
	goodsEnroll = new GoodsEnroll();
	
	//상품등록
	$("#enrollBtn").on("click", function(e){
		goodsEnroll.goodsInsert(e);
	});
	
	//작가선택 팝업창
	$(".authorId_btn").on("click", function(e){
		goodsEnroll.authorPopUp(e);
	});
	
		
}); //function end

function GoodsEnroll(){

	let enrollForm = document.getElementById("enrollForm");
	
	//상품등록
	this.goodsInsert = function(e){
		
		e.preventDefault();
		enrollForm.submit();	
		
	} //function end
	
	//작가선택 팝업창
	this.authorPopUp = function(e){
	
		e.preventDefault();
		
		let popUrl = "/admin/authorPop";
		let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		
		window.open(popUrl, "작가 찾기", popOption);
	
	} //function end

}