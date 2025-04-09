
export function api_select(){
	fetch("./api_select.do?key=koo",{		
		method : "GET",
		//data : "key=koo"		//Post 일때 key보내는법 
		
	}).then(function(aa){
		return aa.json();	//json() : JSON.parse 사용과 동일 (ecma) 
		//text() : 문자열 
	}).then(function(bb) {
		//forEach, for in (ECMA), Jquery (forEach, Each, for in) =>Jquery가 선택의 폭이 넓어서 사람들이 좋아함 
		//react : forEach, Map / Vue : Map, forEach, filter
		
		bb["data_all"].forEach(function(a, b, c) { //a : 배열 데이터, b : 배열 그룹번호, c : 전체배열
			//console.log(a["pd1"]);
			//innerHTML로 배열값을 웹페이지에 출력하는 코드 
			document.querySelector("#table_view").innerHTML += `
			<tr data-index='`+a["midx"]+`'>
			<td>`+b+`</td>
			<td>`+a["pd1"]+`</td>			
			<td>`+a["pd2"]+`</td>
			<td>`+a["pd3"]+`</td>
			<td>`+a["pd4"]+`</td>
			<td>`+a["pd5"]+`</td>
			</tr>
			`
		});
      
	}).catch(function(error){
		console.log(error);
	});
	
	
}

