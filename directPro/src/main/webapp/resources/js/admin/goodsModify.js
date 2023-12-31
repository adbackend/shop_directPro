$(function(){

	imageList(); // 등록된 이미지 불러오기
	
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

	goodsModify = new GoodsModify();

	
	//할인율 가공
	let discount = document.getElementById("discount").value;
	document.getElementById("discount_interface").value = discount * 100;
	
	//출판일 가공
	let publeYear = document.getElementById("publeYear").value;
	let yearLength = publeYear.indexOf(" ");

	publeYear = publeYear.substring(0, yearLength);
	
	document.getElementById("publeYear").value = publeYear;
	
	//상품목록 이동
	$("#cancelBtn").on("click", function(e){
		goodsModify.goodsManageList(e);
	});
	
	//수정 버튼
	$("#modifyBtn").on("click", function(e){
		goodsModify.goodsModify(e);
	});
	
	//중분류 select, 대분류 변경시
	$("#cate1").on("change", function(e){
		goodsModify.cateSelect2(e);
	});
	
	//소분류 select, 중분류 변경시
	$("#cate2").on("change", function(e){
		goodsModify.cateSelect3(e);
	});
	
	//상품 할인율 input 설정
	$("#discount_interface").on("propertychange change keyup paste input", function(){
		calcRate();
	});
	
	$("#bookPrice").on("change", function(){
		calcRate();
	});
	
	//상품 삭제
	$("#deleteBtn").on("click", function(e){
		goodsModify.goodsDelete(e);
	});
	
	//파일업로드
	$("#fileItem").on("change", function(e){
		goodsModify.fileUpload(e);
	});
	
	//이미지 삭제버튼 클릭시 
	$("#uploadResult").on("click", "#imgDeleteBtn", function(e){
		deleteFile();
	});
	

});

function imageList(){

	let bookId = document.getElementById("bookId").value;
	let uploadResult = document.getElementById("uploadResult");
	
	$.getJSON("/getAttachList", {"bookId" : bookId}, function(arr){
	
		if(arr.length == 0){ //이미지가 없는 상품일때
			return ;
		}
		
		let str = "";
		let obj = arr[0];
		
		let div = document.createElement("div");
		let div2 = document.createElement("div");
		let img = document.createElement("img");
		let input1 = document.createElement("input");
		let input2 = document.createElement("input");
		let input3 = document.createElement("input");
		
		console.log(obj);
		let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
		
		div.id = "result_card";
		div.dataset.path = obj.uploadPath;
		div.dataset.uuid = obj.uuid;
		div.dataset.filename= obj.fileName;
		
		div2.className = "imgDelete";
		div2.id = "imgDeleteBtn";
		div2.dataset.file = fileCallPath
		div2.innerHTML = "X";	
		
		input1.type = "hidden";
		input1.name = "imageList[0].fileName";
		input1.value = obj.fileName;
		
		input2.type = "hidden";
		input2.name = "imageList[0].uuid";
		input2.value = obj.uuid;
			
		input3.type = "hidden";
		input3.name = "imageList[0].uploadPath";
		input3.value = obj.uploadPath;		
		
		img.src = "/display?fileName=" + fileCallPath;
		
		uploadResult.append(div);
		div.append(img, div2, input1, input2, input3);
		
		
	});
	
}


//할인율 계산
function calcRate(){

	let userInput = document.getElementById("discount_interface");
	let discountInput = document.getElementById("discount");
	
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

function GoodsModify(){

	let moveForm = document.getElementById("moveForm");
	let modifyForm = document.getElementById("modifyForm"); //수정form
	
	//목록으로 이동
	this.goodsManageList = function(e){
	
		e.preventDefault();
		moveForm.action = "/admin/goodsManage";
		moveForm.method = "get";
		moveForm.submit();
		
	}
	
	//수정버튼 클릭시
	this.goodsModify = function(e){
	
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
		
		/* 공란 체크 */
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
		
		if(!isNaN(bookDiscount)){
			$(".bookDiscount_warn").css('display','none');
			discountCk = true;
		} else {
			$(".bookDiscount_warn").css('display','block');
			discountCk = false;
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
			//alert('통과');
			modifyForm.submit();
		} else {
			return false;
		}
		
				
	}

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
	
	let objOption1 = document.createElement("option");
	let objOption2 = document.createElement("option");
	let objOption3 = document.createElement("option");
	
	makeCateArray(cate1Obj, cate1Array, cateList, 1); //대분류, 중분류, 소분류 파싱
	makeCateArray(cate2Obj, cate2Array, cateList, 2);
	makeCateArray(cate3Obj, cate3Array, cateList, 3);
	
	
	let targetCateCode3 = document.getElementById("cateCode").value; //소분류 코드
	
	for(let i=0; i<cate3Array.length; i++){
		if(targetCateCode3 === cate3Array[i].cateCode){
			targetCateCode3 = cate3Array[i];
		}
	}
	
	for(let i=0; i<cate3Array.length; i++){
		
		if(targetCateCode3.cateParent === cate3Array[i].cateParent){
		
			objOption3 = new Option();
			
			objOption3.value = cate3Array[i].cateCode;
			objOption3.text = cate3Array[i].cateName;
			
			cateSelect3.options.add(objOption3);
		}
	}
	
	$(".cate3 option").each(function(i, obj){
		if(targetCateCode3.cateCode == obj.value){
			obj.selected = true;
		}
	});
	
	//2.중분류 카테고리
	
	let targetCateCode2 = "";
	
	//선택된 중분류
	for(let i=0; i<cate2Array.length; i++){
		if(targetCateCode3.cateParent === cate2Array[i].cateCode){
			targetCateCode2 = cate2Array[i];
		}
	}
	
	for(let i=0; i<cate2Array.length; i++){
		if(targetCateCode2.cateParent === cate2Array[i].cateParent){
		
			objOption2 = new Option();
			
			objOption2.value = cate2Array[i].cateCode;
			objOption2.text = cate2Array[i].cateName;
			
			cateSelect2.options.add(objOption2);
		}
	}
	
	$(".cate2 option").each(function(i, obj){
		if(targetCateCode2.cateCode == obj.value){
			obj.selected = true;
		}
	});
	
	//3.대분류
	
	let targetCateCode1 = "";
	
	//선택된 대분류
	for(let i=0; i<cate1Array.length; i++){
		if(targetCateCode2.cateParent === cate1Array[i].cateCode){
			targetCateCode1 = cate1Array[i];
		}
	}
	
	for(let i=0; i<cate1Array.length; i++){
		if(targetCateCode1.cateParent === cate1Array[i].cateParent){
		
			objOption1 = new Option();
			
			objOption1.value = cate1Array[i].cateCode;
			objOption1.text = cate1Array[i].cateName;
			
			cateSelect1.options.add(objOption1);
		}
	}
	
	$(".cate1 option").each(function(i, obj){
		if(targetCateCode1.cateCode == obj.value){
			obj.selected = true;
		}
	});
	
	//분류선택시 초기화
	//대분류 선택시
	$(cateSelect1).on("change", function(e){
		
		let selectVal1 = e.target.value; //대분류 선택값
		console.log(selectVal1);
		
		cateSelect2.replaceChildren(); //중분류 자식노드 삭제
		cateSelect3.replaceChildren(); //소분류 자식노드 삭제
		
		objOption2.value = "none";
		objOption2.text = "선택";
		cateSelect2.options.add(objOption2);

		objOption3.value = "none";
		objOption3.text = "선택";
		cateSelect3.options.add(objOption3);
	});
	
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
	
	//상품 삭제
	this.goodsDelete = function(e){
		
		e.preventDefault();
		
		moveForm.replaceChildren(); //자식노드 삭제
		
		let input = document.createElement("input");
		let bookId= document.getElementById("bookId").value;
		
		input.type = "hidden";
		input.id = "bookId";
		input.name = "bookId";
		input.value = bookId;
		
		moveForm.appendChild(input);
		
		moveForm.action = "/admin/goodsDelete";
		moveForm.method = "post";
		moveForm.submit();
		
	} // function end
	

	
	//파일 업로드
	this.fileUpload = function(e){
	
		let ele = document.getElementById('uploadResult');
		let eleCount = ele.childElementCount;
		
		console.log("이미지 length : " + eleCount);
		
		if(eleCount > 0){ //이미지가 0보다 클경우
			console.log("으아아1");
			deleteFile();
			console.log("으아아2");
		}
		
		let formData = new FormData();

		let fileInput = document.getElementById("fileItem");
		let fileList = fileInput.files[0];
		
		formData.append("uploadFile", fileList);
		
		console.log(fileList);
		console.log(fileList.type);
		
		let fileCkResult = true; // 이미지 파일인지 체크
		fileCkResult = fileCheck(fileList.type, fileList.size);
		
		
		// processData란? 
		// 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
		// 일반적으로 서버에 전달되는 데이터는 query string 형태로 전달되기 때문에,
		// 파일전송의 경우 이를 하지 않아야 되므로 processData : false로 설정
		
		// contentType란?
		// default값이 "application/x-www-form-urlencoded; charset=UTF-8" 이다
		// 이를 "multipart/form-data"로 전송되게 false로 바꿔주는 역할
		
		if(fileCkResult){
			$.ajax({
				url : "/admin/uploadAjaxAction",
				type : "POST",
				processData : false,
				contentType : false, //서버로 전송되는 데이터
				data : formData,
				dataType : "json", // 서버로부터 반환받을 데이터 타입
				success : function(result){
					console.log(result);
					showUploadImage(result);
				},
				error : function(result){
					alert("이미지 파일이 아닙니다.");
					console.log(result);
				}
			}); // ajax end
		}else{
			document.getElementById("fileItem").value = "";
			return false;
		}
		
	}// function end
	

}

let regex = new RegExp("(.*?)\.(jpg|png|jpeg)$");
let maxSize = 1048576; //1MB

//파일 유효성검사
function fileCheck(fileType, fileSize){

	let ck = true;
	
	if(!regex.test(fileType)){

		alert("해당 종류의 파일은 업로드할 수 없습니다.");
		ck = false;
		
		return ck;
	}

	if(fileSize > maxSize){

		alert("파일사이즈 초과");
		ck = false;
		
		return ck;
	}
	
	console.log(ck+" 11");
	if(!ck){
	console.log(ck +" 22");
		document.getElementById("fileItem").value = "";
	}	
	
	return ck;
	
} // function end

//이미지 미리보기
function showUploadImage(uploadResultArr){
	
	//데이터를 전달받지 못하면 return
	if(!uploadResultArr || uploadResultArr.length == 0){
		return;	
	}
	
	let uploadResult = document.getElementById("uploadResult");
	let obj = uploadResultArr[0];
	
	// encodeURIComponent() 메서는 '/' 와 '\' 문자 또한 인코딩한다
	//encodeURIComponent()적용전	
//	let fileCallPath = obj.uploadPath.replaceAll("\\","/") + "/s_" + obj.uuid + "_" + obj.fileName; 
	
	let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
	
	
	let div1 = document.createElement("div");
	let div2 = document.createElement("div");
	let input1 = document.createElement("input");
	let input2 = document.createElement("input");
	let input3 = document.createElement("input");
	
	let img = document.createElement("img");
	
	div1.id = "result_cart";
	
	img.src = "/display?fileName="+fileCallPath;
	
	div2.id = "imgDeleteBtn";
	div2.className = "imgDelete";
	div2.dataset.file = fileCallPath;
	div2.innerHTML = "X";
	
	input1.type = "hidden";
	input1.name = "imageList[0].fileName";
	input1.value = obj.fileName;
	
	input2.type = "hidden";
	input2.name = "imageList[0].uuid";
	input2.value = obj.uuid;
		
	input3.type = "hidden";
	input3.name = "imageList[0].uploadPath";
	input3.value = obj.uploadPath;	
	
	uploadResult.append(div1);
	div1.append(img, div2, input1, input2, input3);
	
} //function end


//이미지 삭제 버튼 
function deleteFile(){
//	let targetDiv = document.getElementById("result_card"); //이미지 div영역
//	targetDiv.remove();

	console.log("왱11...");
	let targetDiv = document.getElementById("uploadResult");
	targetDiv.firstChild.remove();
	
	console.log("왱22...");

}








