<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="./header.jsp" %>
	<link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">
	<title>회원가입</title>
</head>
<body>
<header id="header">
	<div onclick="location.href='./'" class="left"></div>
	<h3 class="logo">회원가입</h3>
</header>
<form role="form" id="frm" method="post" autocomplete="off" class="form-signin">
	<h4>개인 정보</h4>
	<input type="text" id="name" name="name" class="form-control" placeholder="이름" required autofocus >
	<input type="text" id="id" name="id" class="form-control" placeholder="아이디" required autofocus>
	<input type="password" id="password" name="password" onchange="check_psw();" class="form-control" placeholder="비밀번호(영문,숫자,특수문자 8자이상)" required autofocus>
	<p style="font-family: CJLIGHT;" id = "psw_check_result"></p>
	<input type="text" id="addr" name="addr" class="form-control" placeholder="거래처 주소" required autofocus>
	<input type="button" onclick="calcResult();" class="btn jihye_btn_blue_default" value="할당"/>
	<h4>대리점 정보</h4>
	<input type="hidden" id="agentA" name="agentA"/>
	<p id="resultAgentA">신선: </p>
	<!-- <p id="resultLocationA" style="font-size:10px;"></p> -->
	<input type="hidden" id="agentF" name="agentF"/>
	<p id="resultAgentF">상온: </p>
	<!-- <p id="resultLocationF" style="font-size:10px;"></p> -->
	<footer id="footer">
		<h3 onclick="check_submit();">회원가입</h3>
	</footer>
</form>

<script type="text/javascript">
	var agentId;
	var resultArray;
	function calcResult(){
		locCalc('A');
		locCalc('F');
	}
	function locCalc(gbn) {
			var geocoder = new kakao.maps.services.Geocoder();
			var loc=$("#addr").val();
			console.log('loc');
			
			if(loc=="" || loc==null || loc==undefined)
				$("#result").html("위치를 입력하세요.");
			else {
				var locArray=loc.split(' ');
				if(locArray[1]==undefined || locArray[1]=="" || locArray[1]==null)
					loc=locArray[0];
				else loc=locArray[0]+" "+locArray[1]; 
				var lat, lng;
				// 위경도 계산
				geocoder.addressSearch(loc, function(result, status) {
				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
				    	lat=result[0].y;
				    	lng=result[0].x;
				    	// 통신
				    	$.ajax({
							url:"./locationTest",
							type:"POST",
							dataType:"text",
							contentType: "application/x-www-form-urlencoded; charset=UTF-8",
							async:false,
							data: "location="+loc+"&lat="+lat+"&lng="+lng+"&gbn="+gbn,
							success: function(data){
								// 받아온 결과물 저장
								resultArray=data.split('|');
								$("#resultAgent"+gbn).append(resultArray[1]);
								//$("#resultLocation"+gbn).html(resultArray[2]);
								$("#agent"+gbn).val(resultArray[0]);
							},
							error:function(request,status, error){
								console.log("status:\n"+request.status+"\nerror:\n"+request.error);
							},
							complete:function(data){
								console.log("complete");
							}
						});
				    } 
				});
			}
		}
	
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
			$("#psw_check_result").html("영문, 숫자, 특수문자 조합 8자 이상");
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