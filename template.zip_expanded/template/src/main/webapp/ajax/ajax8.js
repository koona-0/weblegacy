//버튼 클릭시 클래스 및 메소드를 호출 
document.querySelector("#btn").addEventListener("click",function(){
	new ajax_network().ajax_get();
	
});

document.querySelector("#btn2").addEventListener("click",function(){
	new ajax_network2().ajax_idck();
	
});

//Ajax GET
export class ajax_network{
	//axio(react,vue) 안쓰고 fetch씀 (신상)
	//fetch : ECMA에서부터 사용을 했으며 XMLHttpRequest => fetch로 변경
	ajax_get(){
		this.mid = "구나영";
		fetch("./ajax9.do?mid="+this.mid)	//ECMA GET ajax
		.then(function(aa){	//Back-end에서 결과값을 받는 함수 
			return aa.text();	//text(), json() => JSON.parse()
		})
		.then(function(bb){	//Front-end에서 return된 결과 함수를 출력하는 함수 
			console.log(bb);
		})
		.catch(function(error){	//예외처리로 오류 발생시 출력되는 역할 
			console.log(error);
		});
	}
	
	
}

//Ajax POST
export class ajax_network2{
	
	ajax_idck(){
		this.userid="koo";
		this.mid = "mid="+this.userid;	//name형태로 Back-end로 POST 전송 
		fetch("./ajax10.do",{
			method : "POST",	//POST 통신방법 
			headers:{"content-type":"application/x-www-form-urlencoded"},	//이거 안쓰면 에러남	//headers를 이용하여 UTF-8 언어셋
			body : this.mid		//body는 POST전송시 무조건 사용  
		}).then(function(aa){	
			return aa.text();	
		})
		.then(function(bb){	 
			console.log(bb);
		})
		.catch(function(error){	
			console.log(error);
		});
		 
		
	}


}










