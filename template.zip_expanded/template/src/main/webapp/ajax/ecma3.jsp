<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA - ES7 ~ 기초코드2 (Class 및 외부 js 로드)</title>
</head>
<body>

검색 : <input type="text" id="mid" class="mid"><br>
<input type="button" value="검색" id="btn">
</body>
<!-- 무조건 하단에 쓰기 -->
<script type="module"> 
import {zzz} from "./ecma2.js";		//zzz 함수를 로드	(일일히 다 불러와야함)
import {logins} from "./ecma3.js";	//logins라는 class를 로드 (class사용하는것이 좋음 한번에 모든 메소드 불러와짐) (단점 : 오타있으면 사망)
zzz();
new logins().zzz();

</script>
</html>