<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<!-- a 태그에 javascript 함수를 호출 시 javascript: 단어를 입력 후 호출 -->
<body>


${sessionScope.mid}님 환영합니다. <a href="javascript:kakao_logout()">[로그아웃]</a>
<br><br><br>


<!-- SPA?형태로 제작 : 어디로 이동 안하고 ajax를 이용 -->

<input type="hidden" id="MID" value="${mydata.get(0).MID}"><br>
이름 : ${mydata.get(0).MID}<br>

<!-- 패스워드에 절대 값 때리면 안됨 
굳이 때리고 싶으면 값 때려도  ******로 그냥 때리기 
암호화해도 시간만 이쓰면 뚫음 개발자도구에서 password 대신 text로 때리면 다보임 -->
패스워드 : <input type="password" id="MPASS"><br>

<!-- 
주의 : disabled된 애는 핸들링이 안됨 말그대로 사용하지 않겠다는 뜻 
Backend로도 안가고 Javascript에서도 핸들링이 불가능함!!!!
풀고싶다면 버튼을 이용해서 인풋의 속성 disabled를 false로 해주기 
 -->
연락처 : <input type="text" id="MHP" value="${mydata.get(0).MHP}" maxlength="11" disabled>
<input type="button" value="연락처수정" id="hpmodify"><br>

<!-- 실무에선 연락처에 나이스 인증 붙어있음 -->
이메일 : <input type="text" id="MEMAIL" value="${mydata.get(0).MEMAIL}" disabled>
<input type="button" value="이메일수정" id="mailmodify"><br>

<input type="button" value="개인정보수정" id="modify_myinfo">


</body>
<script src="../myinfo.js?v=<%=date%>"></script>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"></script>
<script>
Kakao.init('3171ff31816c485cec4d780369e2603b');	//키발급된 번호
function kakao_logout(){
	if(!Kakao.Auth.getAccessToken()){
		location.href = './logout.do';
	}
	else{
		Kakao.Auth.setAccessToken(undefined);
		sessionStorage.clear();
		localStorage.clear();
		location.href = './logout.do';
	}
}
</script>
</html>