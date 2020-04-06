<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

</head>
<body>
<h3>회원가입</h3>
<form role="form" id="frm" method="post" autocomplete="off">
 <p>이름: <input type="text" id="name" name="name" /></p>
 <p>아이디: <input type="text" id="id" name="id" /></p>
 <p>비밀번호: <input type="password" id="password" name="password" onchange="check_psw();" placeholder="영문, 숫자, 특수문자 조합 8자 이상"/></p>
 <p id = "psw_check_result"></p>
 <p>비밀번호 찾기 질문: <input type="text" id="user_quest" name="user_quest" /></p>
 <p>비밀번호 찾기 답변: <input type="text" id="user_answer" name="user_answer" /></p>
 <p>대리점 번호: <input type="text" id="cd_agent" name="cd_agent" /></p>
 <p><button type="button" onclick="check_submit();">회원가입</button>
 <button type="reset">취소</button></p>
</form>

<script type="text/javascript">
	var psw_validation = 'false';
	
	function check_psw(){
		var psw=$("#password").val();
		var check = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		// 영문, 숫자, 특수문자 조합
		if(check.test(psw)) {
			$("#psw_check_result").html("PASS");
			$("#psw_check_result").css({
				'color' : 'green',
			});
			psw_validation = 'true';
		}
		else{
			if(!check.test(psw)) console.log("숫자");
			$("#psw_check_result").html("영문, 숫자, 특수문자 조합 8자 이상으로 입력하세요!");
			$("#psw_check_result").css({
				'color': 'red',
			});
			psw_validation = 'false';
		}
	}
	function check_submit(){
		if(psw_validation == 'true'){
			console.log("check");
			$("#frm").submit();
		}
		else{
			alert("비밀번호를 확인해주세요");
		}
	}
</script>
</body>
</html>