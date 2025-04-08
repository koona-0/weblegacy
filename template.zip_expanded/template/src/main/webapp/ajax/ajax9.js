export class ecma_ajax{
	
	//배열 생성 후 POST
	ajax_arr(){
//		this.arr = ["koo","lee","yoon"];
//		this.arr = {mid:"koo", mname:"구나영"};
		
		fetch("./ajax11.do",{
			method:"POST",
			
			//아래두줄 세트 => @RequestBody로 받기 => JSONArray, JSONObject로 풀기 
//			headers:{"content-type":"application/json"},
//			body:JSON.stringify(this.arr)

			//아래두줄 세트 => @RequestParam 두개로 받거나 @ModelAttribute dto로 받기 
//			headers:{"content-type":"application/x-www-form-urlencoded"},
//			body:"mid=koo&mname=구나영"	
				
			/* 세트 (JSON.stringify)
			headers:{"content-type":"application/json"},
			body:JSON.stringify({
				mid:"koo",
				mname:"구나영"
			})
			*/
			
			//DTO로 받는 법 (new URLSearchParams)
			//URLSearchParams : 배열(키,원시 모두 가능)을 구성하여 Ajax로 데이터를 전송시키는 방식 
			headers:{"content-type":"application/x-www-form-urlencoded"},
			body:new URLSearchParams({
				mid:"koo",
				mname:"구나영"
			})
			
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