<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 
API Key에 관련된 사항을 체크 
PATCH : update (수정)
PUT : insert (입력)
DELETE : delete (삭제)

GET(보안X), POST(보안) : select (검색)
 -->
<meta charset="UTF-8">
<title>Ajax(PATCH) - REST API Server</title>
<script src="./jquery.js"></script>
<script>
$(function() {
    $("#btn").click(function(){
       var $mid = $("#mid").val();
       //코드 변경 배열(X)
       $.ajax({
          url : "./ajax12.do/patch_myinfo",
          cache : false,
          type : "PATCH",
          dataType : "html",
          contentType : "application/json",
          data : JSON.stringify({
             mid : $mid,
             mname : "홍길동",
             mage : "35",
             memail : "hong@nate.com"
          }),
          success: function($data) {
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
아이디 : <input type="text" id="mid"><br>
<input type="button" value="JS 클릭" onclick="ajaxs()"><br>
<input type="button" value="JQ 클릭" id="btn">
</body>
<script>
function ajaxs(){
	var mid = document.getElementById("mid");	//하나만 보내는 경우 
	
	var arr = new Array();	//배열을 보내는 경우 
	arr[0] = mid.value;
	arr[1] = "koonayoung";
	arr[2] = "서울시 마포구";
		
	var http, result;
	
	http = new XMLHttpRequest();
	
//	http.open("PATCH","./ajax12.do/"+mid.value, false);	//하나만 보내는 경우 
//	http.open("PATCH","./ajax12.do/"+arr, false);	//배열을 보내는 경우 
//	http.setRequestHeader("content-type","application/x-www-form-urlencoded");	//하나만 보내는 경우,배열을 보내는 경우


	//배열값 및 보안 코드를 적용하여 API로 연결하는 방식 
	//JSON.stringify=> 받을수있는 방법이없음 위방법으로 써야함 
	http.open("PATCH","./ajax12.do/patch_myinfo", false);	//보안코드 (patch_myinfo라는 단어로 )
	//patch_myinfo 보안 뭐어쩌고 ... 암호화시킴 보안에 좋음 
	http.setRequestHeader("content-type","application/json");	//배열을 JSON으로 보내는 경우 

	http.onload = function(){
		console.log(this.response);
	};
	http.onerror = function(){
		console.log("통신오류발생");
	};
//	http.send();		//하나만 보내는 경우,배열을 보내는 경우
	http.send(JSON.stringify(arr));	// JSON.stringify(arr) => send라는 함수를 이용하여 전달  
	
	
}
</script>
</html>