$(function(){
	/* 책 소개 */
	ClassicEditor
		.create(document.querySelector('#bookIntro_textarea'))
		.then(editor=>{
			console.log(editor);
			editor.isReadOnly = true;
		})
		.catch(error=>{
			console.error(error);
		});
		
	/* 책 목차 */	
	ClassicEditor
	.create(document.querySelector('#bookContents_textarea'))
	.then(editor=>{
		console.log(editor);
		editor.isReadOnly = true;
	})	
	.catch(error=>{
		console.error(error);
	});	

	goodsDetail = new GoodsDetail();
	
	//할인율 가공
	let discount = document.getElementById("discount").value;
	document.getElementById("discount_interface").value = discount * 100;
	
	//출판일 가공
	let publeYear = document.getElementById("publeYear").value;
	let yearLength = publeYear.indexOf(" ");

	publeYear = publeYear.substring(0, yearLength);
	
	document.getElementById("publeYear").value = publeYear;
	

});

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

function GoodsDetail(){

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
	

}