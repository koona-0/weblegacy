<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - Jquery로 전송 (Post) - JSON.stringify (DTO연결)</title>

<script src="./jquery.js?v=1"></script>
<script>
	$(function() {
		//dto로 보내는 방법의 변수  
		//Back-end가 DTO형태 기준으로 정보를 요청하였을 경우 
		var $userdata = "pd1=구나영&pd2=이주은&pd4=윤하빈";

		$("#btn").click(function() {
			$.ajax({
				url : "./ajax7.do",
				cache : false,
				type : "POST",
				dataType : "html",
				/*
				//각각의 다른 키로 데이터를 받아서 처리 JSON.stringify
				contentType: "application/json;",
				data : JSON.stringify({
					pd1 : "구나영",
					pd3 : "이주은",
					pd4 : "윤하빈"
				}),
				*/
				
				data : $userdata,	//JSON.stringify 미사용 
				success : function($result) {
					console.log($result);
				},
				error : function($error) {
					console.log($error);
				}

			});

		});

	});
</script>
</head>
<body>
<input type="button" value="전송" id="btn">
</body>
</html>