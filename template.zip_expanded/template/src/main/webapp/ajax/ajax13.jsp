<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - @PutMapping => DTO로 처리 </title>
<script src="./jquery.js"></script>
<script>

	$(function() {
		$("#jqbtn").click(function() {
			$.ajax({
				url : "./ajax14/a123456",
				cache : false,
				type : "PUT",
				dataType : "html",
				contentType : "application/json",
				data : JSON.stringify({
					pd1 : document.querySelector("#pd1").value,
					pd2 : document.querySelector("#pd2").value,
					pd3 : document.querySelector("#pd3").value,
					pd4 : document.querySelector("#pd4").value,
					pd5 : document.querySelector("#pd5").value
				}),
				success : function($data) {
					console.log($data);
				},
				error : function() {
					console.log("통신 오류 발생!");
				}
			});
		});
	});
	
</script>
</head>
<body>
입력값 : <input type="text" id="pd1"><br>
입력값 : <input type="text" id="pd2"><br>
입력값 : <input type="text" id="pd3"><br>
입력값 : <input type="text" id="pd4"><br>
입력값 : <input type="text" id="pd5"><br>
<input type="button" value="JS Ajax전송" onclick="ajax_put()">
<input type="button" value="ES Ajax전송" id="btn">
<input type="button" value="jquery Ajax전송" id="jqbtn">
</body>
<!-- JS -->
<script>
function ajax_put(){
	var a= 1;
	while(a < 6){
		//eval() : 문자형태를 Script화 시켜주는 역할 함수 
		//eval을 이용해 동적으로 변수 생성 
		eval("pd" + a + " = document.getElementById('pd" + a + "').value;");
		//eval("var pd"+a +"="+ document.getElementById("pd"+a).value);	//한글 안됨 
		a++;
	}
	  
    // 키배열 pd1 ~ pd5
    var keyarray = {
          pd1: pd1,
          pd2: pd2,
          pd3: pd3,
          pd4: pd4,
          pd5: pd5
    };
    var json = JSON.stringify(keyarray);
    console.log(json);
    
    var http,result;
    http = new XMLHttpRequest();
    http.open("PUT","./ajax14/a123456",false);
    //여기서 헤더는 상관없음 
//    http.setRequestHeader("content-type","application/json");
//    http.setRequestHeader("content-type","application/x-www-form-urlencoded");
    http.onload = function() {
       result = this.response;
       if(result == "ok"){
    	   alert("저장 완료");
    	   //location.href.reload();	// reload 오류 발생가능
    	   //window.location.reload();	// 그냥 아래처럼 사용함 
    	   location.reload();
       }else{
    	   alert("저장 실패");
       }
       
       
       console.log(result);
    }
    http.onerror = function() {
       console.log("통신오류!!");
    }
//	http.send(keyarray);
	http.send(json);
	
}
</script>
<!-- ES -->
<script type="module">
import {api_insert} from "./ajax13.js";
document.querySelector("#btn").addEventListener("click",function(){
	new api_insert().api_put();
});
</script>
</html>