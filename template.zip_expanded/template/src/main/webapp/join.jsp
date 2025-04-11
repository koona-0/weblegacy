<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>간편회원가입</title>
</head>
<body>

<form id="frm" method="post" action="./joinok.do">
<input type="hidden" name="MCODE" value="1">	<!-- 1 : 자회사회원가입 / 2 : 카카오 / 3 : 네이버 -->
<input type="hidden" name="MJOIN" value="WEB">	<!-- WEB, KAKAO, NAVER -->
<input type="hidden" id="ck" value="">	<!-- 아이디 중복체크 확인 -->

아이디 : <input type="text" name="MID"><input type="button" value="중복췤" id="idck"><br>
이름 : <input type="text" name="MNAME"><br>
닉네임 : <input type="text" name="MNICK"><br>
패스워드 : <input type="password" name="MPASS"><br>
<!-- 패스워드확인생략 -->
이메일 : <input type="text" name="MEMAIL"><br>
연락처 : <input type="text" name="MHP"><br>

<input type="button" value="회원가입" id="join">
</form>

</body>
<script>
let kid = sessionStorage.getItem("mid");
let knick = sessionStorage.getItem("knick");
if(kid != null){	//!=""하면 안먹음 
	document.querySelector("#ck").value = "ok";
	document.querySelector("#idck").style.display = "none";
	frm.MCODE.value = "2";
	frm.MJOIN.value = "KAKAO";
	frm.MID.value = kid;
	frm.MID.readonly = true;
	frm.MNICK.value = knick;
	frm.MNICK.readonly = true;
	frm.MPASS.value = kid;
	frm.MPASS.readonly = true;
}
</script>

<script type="module">
import {member_ck} from "./join.js";

document.querySelector("#idck").addEventListener("click",function(){
new member_ck().ajax_idcheck();
});

document.querySelector("#join").addEventListener("click",function(){
   new member_ck().join_okpage();
});
</script>
</html>