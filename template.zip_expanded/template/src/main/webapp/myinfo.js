//disabled된 애는 핸들링이 안됨 
//Backend로도 안가고 Javascript에서도 핸들링이 불가능함!!!!

document.querySelector("#hpmodify").addEventListener("click",function(){
	//해당 연락처 수정 버튼을 클릭시 연락처 입력부분을 수정할 수 있도록 disable 정지시킴
	if(confirm("연락처를 수정하시겠습니까?")){
		document.querySelector("#MHP").disabled = false;
	}
});

//이메일 수정시 적용되는 이벤트 핸들링 함수 
document.querySelector("#mailmodify").addEventListener("click",function(){
	if(confirm("이메일을 수정하시겠습니까?")){
		document.querySelector("#MEMAIL").disabled = false;
	}
});

//배열키 : MPASS, MEMAIL, MHP 이거로 보내주세영 - Back-end
//개인정보수정 버튼 클릭시 AJAX 발동 
document.querySelector("#modify_myinfo").addEventListener("click",function(){
	/*
	//Map 배열로 키를 생성하여 전달하는 방식
	//이거는 Spring에서 전송 안됨! Spring-boot에서는 가능 
	let udata = new Map();
	
	udata.set("MID",document.querySelector("#MID").value);
	//값이 null이면 에러나기때문에 핸들링 필요 
	if(document.querySelector("#MPASS").value != ""){
		udata.set("MPASS",document.querySelector("#MPASS").value);
	}
	udata.set("MHP",document.querySelector("#MHP").value);
	udata.set("MEMAIL",document.querySelector("#MEMAIL").value);
	console.log(udata);
	*/ 
	fetch("./myinfo_modify.do/mykey",{
		method : "PATCH",
		//headers : {"content-type":"application/json"},
		body : JSON.stringify({
			"MID" : document.querySelector("#MID").value,
			"MPASS" : document.querySelector("#MPASS").value,
			"MHP" : document.querySelector("#MHP").value,
			"MEMAIL" : document.querySelector("#MEMAIL").value
		})
		
	}).then(function(result){
		return result.text();
	}).then(function(data){
		 if(data=="ok"){
			alert("정상적으로 변경 완료");
			location.reload();
		}else{
			alert("정보 수정 실패");
			
		}
		
		
		console.log(data);
	}).catch(function(error){
		console.log(error);
	});
});
