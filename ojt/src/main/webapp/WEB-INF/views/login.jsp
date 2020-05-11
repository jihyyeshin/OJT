<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %>
<title>login</title>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
</head>
<body class="text-center">

<div class="form-login">
	<img class="mb-4" src="<c:url value="/resources/img/CJ_logo.png" />" alt="" width="100" height="88">
	<c:if test="${member == null }">

	<form role="form" method="post" autocomplete="off" action="./login">
		<label for="id" class="sr-only">아이디</label>
		<input type="text" id="id" name="id" class="form-control" placeholder="ID" required autofocus>
		 
		<label for="pwd" class="sr-only">비밀번호</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		
		<p><button type="submit" class="btn btn-lg btn_blue_default btn-block">로그인</button></p>
		<p><a href="./signup" class="btn btn-lg btn_white_default btn-block">회원가입</a></p>
		<p><a href="./tmpPwd" class="link">비밀번호를 잊어버리셨나요?</a></p>
	</form>
	</c:if>
	<c:if test="${member != null }">
	<h1> ${member.name}님 환영합니다.</h1>
	<p>하단 버튼을 눌러 상품을 주문해보세요!</p>
	<form action="./items" method="post">
		<input type="hidden" name="agentF" value="${member.agentF}"/>
		<input type="hidden" name="agentA" value="${member.agentA}"/>
		<input type="hidden" name="memberid" value="${member.id}"/>
		<p><button type="submit" class="btn btn-lg btn_blue_default btn-block">상품주문</button></p>
	</form>
	<form action="#" method="post">
		<input type="hidden" name="memberid" value="${member.id}"/>
		<p><button class="btn btn-lg btn_white_default btn-block">주문내역조회</button></p>
	</form>
	
	<p><a href="./logout" class="link">로그아웃</a></p>
	</c:if>
	<p style="color:blue;font-family: CJLIGHT;">${msg}</p>
</div>
</body>
</html>