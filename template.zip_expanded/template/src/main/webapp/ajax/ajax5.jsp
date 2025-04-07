<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - Jquery로 전송 (Post) - JSON.stringify</title>
<script src="./jquery.js?v=1"></script>
<script>
	$(function() {

		var $ajaxdata = new Array();
		$ajaxdata[0] = "홍길동";
		$ajaxdata[1] = "이순신";
		$ajaxdata[2] = "장보고";

		//JSON.stringify 전송 : 대표키로 보낼까요? 대표키 없이 보낼까요?
		$("#btn").click(function() {
			$.ajax({
				url : "./ajax6.do",
				cache : false,
				type : "POST",
				dataType : "html",
				contentType: "application/json;",	//이거 안쓰면 한글 깨짐 중요!
				//data: JSON.stringify($ajaxdata),	//대표키 없는 경우 
				data: JSON.stringify({userdata:$ajaxdata}),	//대표키 있는 경우   
				async : true,
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