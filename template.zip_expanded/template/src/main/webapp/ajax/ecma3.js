export class logins{	//외부로 호출할 수 있는 class 
	zzz(){	//메소드 ()여기 function쓰면 안됨 함수와 메소드의 개념은 다름!)
		let data = new Array();
		data[0] = "a1";
		data[1] = "a2";
		console.log(data);
	}
	
	search_ck(word){
		if(word=""){
			alert("검색어를 입력하세요");
		}else{
			location.href="http://nate.com";	//GET
		}
	}
	
	constructor(){		//즉시실행메소드 : 클래스 호출시 작동 
		console.log("테스트라능");
	}
	
	agree_data(a,b){	//setter (set을 붙여도 되는데 안붙여도 됨)
		this.data = a;
		this.data2 = b;
	}
	
	get agree_data(){	//getter (get을 앞에 붙여 사용하는 메소드)
		return this.data;
	}
}

//addEventListener : 이벤트 핸들링 함수 => 아무데나 써도 작동함 export안써도 로드안해도 작동함 무조건 우선순위
//페이지 로드시 무조건 작동하는 함수  
//해당 js파일을 다른곳에서도 쓸때는 이벤트 핸들링 함수를 사용하는 jsp파일에 스크립트로 추가해야함 아니면 에러남 
//무조건 버튼을 가져와야해서 버튼이 없는 경우 에러나기때문임 
document.querySelector("#btn").addEventListener("click",function(){
	let search = document.querySelector("#mid").value;
	//querySelector : id(#), class(.)를 위주로 오브젝트를 로드할 수 있음 
	
	//name은 document.ElementByName이거 써야 함 
	//name이 form 안에 붙어있을 경우 폼네임.네임 쓰던거로 써도됨   
	console.log(search);
	new logins().search_ck(search);
});