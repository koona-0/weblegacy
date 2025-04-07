document.querySelector("#btn").addEventListener("click", function() {
	//new box().abc("나옹");	//클래스 호출 및 메소드 호출 
	new box2().abc("나옹이");
	new box2().bbb();
	 
});

var msg = "테스트 !";

class box {	//클래스 

	abc(data) {	//메소드
		//this => 가상 변수 
		this.msg = data + " 데이터 확인";
		console.log(this.msg);	//그냥 msg 넣어야 테스트 ! 나옴 
	}

}

class box2 extends box{
	bbb(){
		console.log("상속받은 클래스 bbb 메소드");
	}
	
}
