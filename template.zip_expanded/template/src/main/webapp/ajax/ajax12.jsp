<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete, PUT - AJAX</title>
<script src="./jquery.js"></script>
</head>
<body>
   <input type="button" value="JS삭제" onclick="ajax_del()">
   <input type="button" value="ES삭제" id="ajax_del">
</body>
<script>
//JS 삭제 
function ajax_del(){
   var formdata = new FormData();
   formdata.append("midx",5);
   formdata.append("midx",9);
   formdata.append("midx",12);
   //console.log(formdata.getAll("midx"));
   var http,result;
   http = new XMLHttpRequest();
   http.open("DELETE","./ajax13/a123456",false);
   http.setRequestHeader("content-type","application/x-www-form-urlencoded");
   http.onload = function() {
      console.log(this.response);
   };
   http.onerror = function() {
      console.log("통신오류");
   };
   http.send(formdata);
}

//ES 삭제
document.querySelector("#ajax_del").addEventListener("click", function() {
	/*방법 1 : FormData() => @RequestBody 
	var formdata = new FormData();
	   formdata.append("midx",5);
	   formdata.append("midx",9);
	   formdata.append("midx",12);
	   */
	   //방법2 : Array() =>  @RequestBody 
	   this.arr = ["5","9","12"];
	   
	   fetch("./ajax13/a123456",{
		   method:"DELETE",
//			body:formdata	//방법1

headers : {"content-type":"application/json"},
body : JSON.stringify(this.arr)	//방법2

	   }).then(function(aa){	
			return aa.text();	
		}) .then(function(bb){	 
			console.log(bb);
		}) .catch(function(error){	
			console.log(error);
		});
});


</script>
</html>



























