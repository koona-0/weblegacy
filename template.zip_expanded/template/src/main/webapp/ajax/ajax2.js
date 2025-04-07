
//ajax => POST 통신 
function ajax_gopage() {
	var ajax, result;

	/*
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);
			}
		}
	}

	ajax.open("", "", false);
	ajax.send();
	*/
	
	var data = new Array();
	data[0] = "홍길동";
	data[1] = "강감찬";
	data[2] = "이순신";
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.status == 200) {
				console.log(this.response);
			}
		}
	}
	ajax.open("POST", "./ajax2.do", true);
	//POST 전송시 해당 content-type을 설정하여 전송 
	ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");	//뒷 파라미터는 form의 enc-type 컨스페하면 나옴 
	ajax.send("product="+data);	//해당 key name + 배열 데이터 (여러개를 태울땐 &로 이어줌)

}