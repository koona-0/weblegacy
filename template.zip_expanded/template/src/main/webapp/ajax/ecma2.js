/*
export : 해당 함수를 외부에서 사용할 수 있도록 내보내는 역할 
 */

export function zzz(){
	console.log("es7!");
}

export function aaa(){
	var user = "구나영";
	console.log(user + "님 포인트 1500 입니다.");
}

export function bbb(){		//외부에서 import로 호출이 가능한 함수 
	alert("환영합니다");
}

function ccc(){				//내부 함수 : 내부에서만 사용 가능 
	console.log("ccc함수");
}