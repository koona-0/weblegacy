<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax (GET) - 문자열, Array를 이용한 데이터 전송</title>
</head>
<body>
<!-- 
1. ajax => form 태그로 전송하지 않음!!!
2. FormData 함수 사용하여 전송
3. ajax는 브라우저의 URL이 변경되지 않음 //페이지 자체에서 값을 변경 
4. Back-end가 무조건 결과값을 Front-end에게 전송해줘야함 
 -->
<input type="button" value="전송" onclick="ajax_gopage()">

</body>
<!-- 
ajax GET 통신 (선택된 상품만 Back-end 전송)
1. 같은 이름으로 문자열로 보내면 될까요? ㅇㅁㅇ? => ./ajax1.do?product=1,2,3,4,5
2. 키를 이용하여 배열로 보내면 될까요? ㅇㅅㅇ? => product=['1','2','3','4','5']
 -->
<script src="./ajax1.js?v=1"></script>
</html>