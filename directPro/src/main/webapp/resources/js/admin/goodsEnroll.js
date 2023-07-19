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
	
	//중분류 select, 대분류 변경시
	$("#cate1").on("change", function(e){
		goodsEnroll.cateSelect2(e);
	});
	
	//소분류 select, 중분류 변경시
	$("#cate2").on("change", function(e){
		goodsEnroll.cateSelect3(e);
	});
	
	//상품 할인율 input 설정
	$("#discount_interface").on("propertychange change keyup paste input", function(){
		calcRate();
	});
	
	$("#bookPrice").on("change", function(){
		calcRate();
	});
	
	
		
}); //function end

//할인율 계산
function calcRate(){

	let userInput = document.getElementById("discount_interface");
	let discountInput = document.getElementById("bookDiscount");
	
	let discountRate = userInput.value;
	let sendDiscountRate = discountRate / 100;
	
	let bookPrice = document.getElementsByName("bookPrice")[0].value; //원가
	let discountPrice = bookPrice * (1 - sendDiscountRate); //할인 가격
	
	
	if(!isNaN(discountRate)){ //문자가 아닐때
		document.getElementById("span_discount").innerText = discountPrice;
		discountInput.value = sendDiscountRate;
	}else{
		document.getElementById("span_discount").innerText = "숫자를 입력해주세요";
	}
}

//대분류, 중분류, 소분류 파싱
function makeCateArray(obj, array, cateList, tier){
	
	for(let i=0; i<cateList.length; i++){
		if(cateList[i].tier === tier){
			
			obj = new Object();
			
			obj.cateName = cateList[i].cateName;
			obj.cateCode = cateList[i].cateCode;
			obj.cateParent = cateList[i].cateParent;
			
			array.push(obj);
		}
	}
}

function GoodsEnroll(){

	let cateList = document.getElementById("cateList").value; //카테고리
	cateList = JSON.parse(cateList); //json 파싱
	
	let cate1Array = new Array();
	let cate2Array = new Array();
	let cate3Array = new Array();
	
	let cate1Obj = new Object();
	let cate2Obj = new Object();
	let cate3Obj = new Object();
	
	let cateSelect1 = document.getElementById("cate1");
	let cateSelect2 = document.getElementById("cate2");
	let cateSelect3 = document.getElementById("cate3");
	
	makeCateArray(cate1Obj, cate1Array, cateList, 1); //대분류, 중분류, 소분류 파싱
	makeCateArray(cate2Obj, cate2Array, cateList, 2);
	makeCateArray(cate3Obj, cate3Array, cateList, 3);
	
	let objOption = document.createElement("option");
	let objOption2 = document.createElement("option");
	let objOption3 = document.createElement("option");
	
	//대분류
	for(let i=0; i<cate1Array.length; i++){
		
		objOption = new Option();
	
		objOption.value = cate1Array[i].cateCode;
		objOption.text = cate1Array[i].cateName;
		
		cateSelect1.options.add(objOption);
	}
	
	
	//중분류 선택
	this.cateSelect2 = function(e){
	
		let selectVal1 = e.target.value; //대분류 선택값
		
		cateSelect2.replaceChildren(); //중분류 자식노드 삭제
		cateSelect3.replaceChildren(); //소분류 자식노드 삭제
		
		objOption2.value = "none";
		objOption2.text = "선택";
		cateSelect2.options.add(objOption2);

		objOption3.value = "none";
		objOption3.text = "선택";
		cateSelect3.options.add(objOption3);
		
		
		for(let i=0; i<cate2Array.length; i++){
			if(selectVal1 === cate2Array[i].cateParent){
			
				objOption2 = new Option();
			
				objOption2.value = cate2Array[i].cateCode;
				objOption2.text = cate2Array[i].cateName;
				
				cateSelect2.options.add(objOption2);
			}
		} // for end
	} //function end
	
	//소분류 선택
	this.cateSelect3 = function(e){
	
	
		let selectVal2 = e.target.value; //중분류 선택값
		cateSelect3.replaceChildren(); //자식노드 삭제
		
		objOption3.value = "none";
		objOption3.text = "선택";
		cateSelect3.options.add(objOption3);		
		
		for(let i=0; i<cate3Array.length; i++){
			if(selectVal2 === cate3Array[i].cateParent){
				
				objOption3 = new Option();
				
				objOption3.value = cate3Array[i].cateCode;
				objOption3.text = cate3Array[i].cateName;
				
				cateSelect3.options.add(objOption3);
			}
		} // for end
	
	} //function end
	

	let enrollForm = document.getElementById("enrollForm");
	
	//상품등록
	this.goodsInsert = function(e){
		
		e.preventDefault();
		
		/* 체크 변수 */
		let bookNameCk = false;
		let authorIdCk = false;
		let publeYearCk = false;
		let publisherCk = false;
		let cateCodeCk = false;
		let priceCk = false;
		let stockCk = false;
		let discountCk = false;
		let introCk = false;
		let contentsCk = false;
		
		/* 체크 대상 변수 */
		let bookName = $("input[name='bookName']").val();
		let authorId = $("input[name='authorId']").val();
		let publeYear = $("input[name='publeYear']").val();
		let publisher = $("input[name='publisher']").val();
		let cateCode = $("select[name='cateCode']").val();
		let bookPrice = $("input[name='bookPrice']").val();
		let bookStock = $("input[name='bookStock']").val();
		let bookDiscount = $("#discount_interface").val();
		let bookIntro = $(".bit p").html();
		let bookContents = $(".bct p").html();

		if(bookName){
			$(".bookName_warn").css('display','none');
			bookNameCk = true;
		} else {
			$(".bookName_warn").css('display','block');
			bookNameCk = false;
		}
		
		if(authorId){
			$(".authorId_warn").css('display','none');
			authorIdCk = true;
		} else {
			$(".authorId_warn").css('display','block');
			authorIdCk = false;
		}
		
		if(publeYear){
			$(".publeYear_warn").css('display','none');
			publeYearCk = true;
		} else {
			$(".publeYear_warn").css('display','block');
			publeYearCk = false;
		}	
		
		if(publisher){
			$(".publisher_warn").css('display','none');
			publisherCk = true;
		} else {
			$(".publisher_warn").css('display','block');
			publisherCk = false;
		}
		
		if(cateCode != 'none'){
			$(".cateCode_warn").css('display','none');
			cateCodeCk = true;
		} else {
			$(".cateCode_warn").css('display','block');
			cateCodeCk = false;
		}	
		
		if(bookPrice != 0){
			$(".bookPrice_warn").css('display','none');
			priceCk = true;
		} else {
			$(".bookPrice_warn").css('display','block');
			priceCk = false;
		}	
		
		if(bookStock != 0){
			$(".bookStock_warn").css('display','none');
			stockCk = true;
		} else {
			$(".bookStock_warn").css('display','block');
			stockCk = false;
		}		
		
		if(isNaN(bookDiscount)){ // isNaN = 숫자면 false
			$(".bookDiscount_warn").css('display','block');
			discountCk = false;
		}else{
			$(".bookDiscount_warn").css('display','none');
			discountCk = true;
		}
		
		if(bookIntro != '<br data-cke-filler="true">'){
			$(".bookIntro_warn").css('display','none');
			introCk = true;
		} else {
			$(".bookIntro_warn").css('display','block');
			introCk = false;
		}	
		
		if(bookContents != '<br data-cke-filler="true">'){
			$(".bookContents_warn").css('display','none');
			contentsCk = true;
		} else {
			$(".bookContents_warn").css('display','block');
			contentsCk = false;
		}				
		
		if(bookNameCk && authorIdCk && publeYearCk && publisherCk && cateCodeCk && priceCk && stockCk && discountCk && introCk && contentsCk ){
			enrollForm.submit();	
		} else {
			return false;
		}		
		
	} //function end
	
	//작가선택 팝업창
	this.authorPopUp = function(e){
	
		e.preventDefault();
		
		let popUrl = "/admin/authorPop";
		let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		
		window.open(popUrl, "작가 찾기", popOption);
	
	} //function end

}