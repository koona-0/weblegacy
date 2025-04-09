<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Oracle Data를 API로 전달 받아 WEB에 출력</title>
</head>
<body>

<table border="1">

<thead>
<tr>
<th>번호</th>
<th>내용1</th>
<th>내용2</th>
<th>내용3</th>
<th>내용4</th>
<th>내용5</th>
</tr>
</thead>

<tbody id="table_view">

</tbody>
</table>
</body>

<script type="module">
import {api_select} from "./api_list.js?v=2";
api_select();
</script>

</html>