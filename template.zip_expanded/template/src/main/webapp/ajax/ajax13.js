export class api_insert {

	api_put() {
		
		fetch("./ajax14/a123456", {
			method : "PUT",
			headers : {"content-type":"application/json"},
			//headers : {"content-type":"application/x-www-form-urlencoded"},		//post로 전송할때 이거로하면 넘어감 
			body : JSON.stringify({
				pd1 : document.querySelector("#pd1").value,	
				pd2 : document.querySelector("#pd2").value,	
				pd3 : document.querySelector("#pd3").value,	
				pd4 : document.querySelector("#pd4").value,	
				pd5 : document.querySelector("#pd5").value	
			})

		}).then(function(aa) {
			return aa.text();
		}).then(function(bb) {
			if (bb == "ok") {
				alert("저장 완료");
				location.reload();
			} else {
				alert("저장 실패");
			}
		}).catch(function(error) {
			console.log(error);
		});
	}

}