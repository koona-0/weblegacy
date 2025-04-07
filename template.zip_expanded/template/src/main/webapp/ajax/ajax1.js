
//ajax => GET 통신 
function ajax_gopage() {
	var ajax, result;
	
	//ajax 동기화 : Back-end에 값을 받아야만 처리 후 다음 결과를 작동시키는 원리 
	//ajax 비동기화 : Back-end 값을 받지 않아도 다음 처리할 항목을 전송시킬수 있음  

	/*
	//js를 이용한 ajax 기본베이스 
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);	//Back-end값 출력
			}
		}
	}

	//첫 파라미터에 GET인지 POST인지 넣어서 사용 
	ajax.open("", "", false);	//false : 동기화, true : 비동기화 => 이것도 백,프론트 소통하기 (아무것도안쓰면 기본 true)
	ajax.send();
	*/
	
	
	/*
	//GET으로 같은 이름의 문자열로 보내기 => ./ajax1.do?product=1,2,3,4,5	
	var data = "1,2,3,4,5";
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);	
			}
		}
	}                    
	ajax.open("GET", "./ajax1.do?product="+data, false);
	ajax.send();
	 */

	
	//GET으로 배열로 보내기 => product=['1','2','3','4','5']
	var data = new Array();
	data[0] = "홍길동";
	data[1] = "강감찬";
	data[2] = "유관순";
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);
			}
		}
	} 
	ajax.open("GET", "./ajax1.do?product="+data, false);
	ajax.send();
	
	
}