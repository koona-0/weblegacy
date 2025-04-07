
//ajax => POST 통신 
function ajax_gopage() {
	var ajax, result;

	/*
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);
			}
		}
	}

	ajax.open("", "", false);
	ajax.send();
	*/
	
	//FormData
	var data = new FormData();
	
	/*
	//다른키 사용시 => 문자열로 각 키별로 셋팅 => get 
	data.append("pd1","홍길동");	//append("키명","데이터")
	data.append("pd2","이순신");
	data.append("pd3","유관순");
	console.log(data.get("pd1"));	//출력시 get이용 
	*/
	

	//동일한 키 사용시 => 원시배열형태로 구성 => getAll 
	data.append("pd","홍길동");	
	data.append("pd","이순신");
	data.append("pd","유관순");
	//출력시 getAll사용 
	//console.log(data.getAll("pd"));	//무조건 배열로 설정 (같은 키일때 getAll)
	
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);
			}
		}
	}

	/*
	setRequestHeader 전송방식 (동시에 여러방식 불가능)
	1. "content-type","application/x-www-form-urlencoded" : name값으로 문자 전송
	2. "content-type","application/json" : json 배열의 값을 이용하여 전송 - formData 전용 
	3. "content-type","multipart/form-data" : IO 형태의 값을 전송 
	4. "가상의 키","값" : 한글값은 전송 불가능 Controller에서 @RequestHeader 사용
	*/
	
	ajax.open("POST", "./ajax3.do", true);

	//다른 키 사용시 하나밖에 전송이 안됨 
	//ajax.send("pd",data);	//하나밖에 안감
	
 	ajax.setRequestHeader("content-type","application/json"); 	//1번방식으로 보내면 백에서 못받음
	ajax.send(data); //formdata함수로 생성된 객체를 전송 (키없이 전송) => 백에서 바디로 받기
	
	//네번째 방식 
	//ajax.setRequestHeader("user","lee");	//user라는 key, lee라는 value	 	
	//ajax.send(); 
}


//키가 다 다른 이름일때 
function ajax_gopage2(){
   var ajax, result;
   
	var data = [];
	data.push(
		{ "pd": "홍길동" }
	);
	data.push(
		{ "pd": "강감찬" }
	);
	data.push(
		{ "pd": "유관순" }
	);
   
   ajax = new XMLHttpRequest();
   ajax.onreadystatechange = function(){
      if(ajax.readyState == 4 && ajax.status == 200){
            console.log(this.response); // Back-end값 출력
      }
   }

   ajax.open("POST","./ajax4.do",true);
   ajax.send(JSON.stringify(data)); 
}
