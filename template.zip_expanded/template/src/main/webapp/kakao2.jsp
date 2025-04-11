<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인및 외부 API 로그인 방식(Kakao)</title>
</head>
<body>

	<form id="frm" method="post" action="./ajax/web_loginok.do">
	<!-- code = 1일반로그인 2카카오로그인 -->
		<input type="hidden" name="code" value="1">
		<input type="hidden" name="kakao_id" value="">
		<input type="hidden" name="kakao_nicknm" value="">
		<!-- autocomplete 자동완성 해제 : 보안굿 -->
		 아이디 : <input type="text" name="mid" autocomplete="off"><br> 
		 패스워드 : <input type="password" name="mpass"><br> 
		 <input type="submit" value="로그인"><br>
		 <input type="checkbox" id="save_id">아이디 저장<br>
		 <!-- 
		 자동로그인 기능은 실제로그인이 한번 돼야 활성화됨
		 자동로그인은 Back-end가 세션스토리지가 아니라 로컬스토리지로 셋아이템으로 브라우저에 저장해야함 
		 아이디 저장은 Front-end가 로컬스토리지에 저장함		 
		 -->
	</form>
	
	<br>
	<br>
	<img src="./ajax/kakao_login.png" onclick="kakao_login()">
	<p id="token-result"></p>
</body>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"></script>
<script>
	Kakao.init('3171ff31816c485cec4d780369e2603b');
	function kakao_login() {
		// Kakao.Auth.login : 카카오 회원가입 및 로그인 페이지를 출력하는 함수 
		Kakao.Auth.login({
			//성공시 출력되는 형태 
			success : function(response) {	//response : 결과화면 (사실 여기서 안넣어도됨)
				Kakao.API.request({			//사용자 가입정보를 요청 
					url : '/v2/user/me',	// 사용자 정보 가져오기
					success : function(response) {	//API서버에서 가입정보를 가져옴 
						console.log(response);
						let id = response["id"];		//고유값 
						let nickname = response["kakao_account"]["profile"]["nickname"];		//카카오 닉네임
						frm.code.value = "2";
						frm.kakao_id.value = id;
						frm.kakao_nicknm.value = nickname;
						frm.submit();
					},
					fail : function(error) {
						console.log(error);
						console.log("카카오 API 접속 오류");
					}
				});
			},
			//API 키가 맞지 않을 경우 출력되는 함수 
			fail : function(error) {
				console.log(error);
				console.log("카카오 key 접속 오류 및 환경설정 오류");
			}
		});
	}
</script>
<script>
	
</script>
<script>
	//해당 페이지로 접속시 작동되는 함수 
	window.onload = function(){
		let userid = localStorage.getItem("userid");
		if(userid != null){	//아이디 저장기능 활성화 
			frm.mid.value = userid;
			document.querySelector("#save_id").checked = true;	//아이디 저장 체크박스 체크 
		}
	}

	//아이디 저장 
	document.querySelector("#save_id").addEventListener("click", function(e) {
		if(frm.mid.value == ""){	
			alert("아이디를 입력하셔야 해당 기능을 사용할 수 있습니다.");
			this.checked = false;	//체크안되게 만들기 
		}else{
			if(this.checked == true){
				localStorage.setItem("userid",frm.mid.value);
			}else{
				localStorage.clear();
			}
			
		}
	});

	// ECMA => submit 사용 시 return, return false가 없습니다.
	document.querySelector("#frm").addEventListener("submit", function(e) {
		e.preventDefault(); // 강제정지
		if (frm.mid.value == "") {
			alert("아이디를 입력하세요");
		} else if (frm.mpass.value == "") {
			alert("패스워드를 입력하세요");
		} else {
			frm.submit();
		}
	});
</script>
</html>