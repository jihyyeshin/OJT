<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="./header.jsp" %>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ad9f9f4c70ec392a1d41fe14ab44d29&libraries=services"></script>
	<link href="<c:url value="/resources/css/signup.css" />" rel="stylesheet">
	<title>회원가입</title>
</head>
<body>
	<header id="header">
		<div onclick="location.href='./'" class="left"></div>
		<h3 class="logo">회원가입</h3>
	</header>

	<div class="container">
	<form role="form" id="frm" method="post" autocomplete="off">
	<h4>개인 정보</h4>
		<table class="table" style="text-align:center;">
			<tr>
				<td>
				<input type="text" id="name" name="name" class="form-control" placeholder="이름" required autofocus >
				</td>
			</tr>
			<tr>
				<td>
				<input type="text" id="id" name="id" class="form-control" placeholder="아이디" required autofocus>
				</td>
			</tr>
			<tr>
				<td>
				<input type="password" id="password" name="password" onchange="check_psw();" class="form-control" placeholder="비밀번호(영문,숫자,특수문자 8자이상)" required autofocus>				
				</td>
				<td>
				<p id = "psw_check_result"></p>
				</td>
			</tr>
			<tr>
				<td>
				<input type="text" id="addr" name="addr" class="form-control" placeholder="거래처 주소" required autofocus>
				</td>
				<td style="width: 15%;">
				<input type="button" onclick="calcResult();" class="btn btn_blue_default" value="할당"/>
				</td>
			</tr>
		</table>

	<h4>대리점 정보</h4>
	<input type="radio" name="chk_info" value="A" checked="checked">신선
	<input type="radio" name="chk_info" value="F">상온
	<table class="table">
		<tr>
			<td>
			<input type="text" id="agentId" name="agentId" class="form-control" placeholder="대리점ID" required autofocus>
			</td>
			<td style="width: 15%;">
			<input type="button" onclick="agentCalc();" class="btn btn_blue_default" value="검색"/>
			</td>
		</tr>
		<tr>
			<td>
			<input type="hidden" id="agentA" name="agentA"/>
			<p id="resultAgentA">신선: </p>
			</td>
		</tr>
		<tr>
			<td>
			<input type="hidden" id="agentF" name="agentF"/>
			<p id="resultAgentF">상온: </p>
			</td>
		</tr>
	</table>

	<div id="footer" onclick="check_submit();">회원가입 </div>
	</form>
	</div>
	

<script type="text/javascript">
	var resultArray;
	var loc;

	function agentCalc(){
		var agentId=$("#agentId").val();
		var radios = document.getElementsByName('chk_info');
		var gbn, gbnVal;
		if(radios[0].checked) {
			gbn='A';
			gbnVal="신선";
		}
		else if(radios[1].checked) {
			gbn='F';
			gbnVal="상온";
		}
		
		$.ajax({
			url:"./postAgent",
			type:"POST",
			dataType:"text",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async:false,
			data: "agentId="+agentId,
			success: function(data){
				// 주소 잘못되었을 때
				if(data == "false"){
					alert("잘못된 대리점 코드입니다.");
					$('#agentId').val('');
				}else{
					// 받아온 결과물 저장
					resultArray=data.split('|');
					$("#resultAgent"+gbn).text(gbnVal+" : "+resultArray[1]);
					$("#agent"+gbn).val(resultArray[0]);
				}
			},
			error:function(request,status, error){
				console.log("status:\n"+request.status+"\nerror:\n"+request.error);
			},
			complete:function(data){
				console.log("complete");
			}
		});
	}
	function calcResult(){
		loc=$("#addr").val();
		if(loc=="" || loc==null || loc==undefined)
			alert("주소를 입력하세요.");
		else {
			locCalc('A');
			locCalc('F');
		}
	}
	function locCalc(gbn) {
		var geocoder = new kakao.maps.services.Geocoder();
		var gbnVal;
		if(gbn=='A') gbnVal="신선";
		else if(gbn=='F') gbnVal="상온";
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
							// 주소 잘못되었을 때
							if(data == "false"){
								alert("잘못된 거래처 주소입니다.");
								$('#addr').val('');
							}
							else{
								// 받아온 결과물 저장
								resultArray=data.split('|');
								$("#resultAgent"+gbn).text(gbnVal+" : "+resultArray[1]);
								$("#agent"+gbn).val(resultArray[0]);
							}
						},
						error:function(request,status, error){
							console.log("status:\n"+request.status+"\nerror:\n"+request.error);
						},
						complete:function(data){
							console.log("complete");
							if (gbn=='F') alert('대리점이 할당되었습니다.');
						}
				});
			} 
		});
			
	}
	
	var psw_validation = 'false';
	
	function check_psw(){
		var psw=$("#password").val();
		var check = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		// 영문, 숫자, 특수문자 조합
		if(check.test(psw)) {
			$("#psw_check_result").html("O");
			$("#psw_check_result").css({
				'color' : 'green',
			});
			psw_validation = 'true';
		}
		else{
			if(!check.test(psw)) console.log("숫자");
			$("#psw_check_result").html("X");
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