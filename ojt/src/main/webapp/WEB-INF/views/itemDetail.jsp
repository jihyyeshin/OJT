<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %>
 <link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
<title>item detail</title>
</head>
<body>
	<header id="header">
		<div onclick="location.href='../items'" class="left"></div>
		<h3 class="logo">아이템 정보</h3>
	</header>
	
	<div class="view";>
	<h4> ${item.name}</h4>
	<p> ${item.remark}</p>
	<!-- 그림 들어와야 함 -->
	<div id="img"></div>
	<form method="post" name="form">
		<input type="hidden" name="item" id="item" value="${item.item}"/>
		<p>수량: <input type="text" name="qty" id="qty"/>개</p>
		
		<input type="hidden" name="memberid" value="${memberid}"/>
		<input type="hidden" name="agent" value="${agent}"/>
		<input type="hidden" name="name" value="${item.name}"/>
		<input type="hidden" name="amount" value="${item.amount}"/>
		
		<button id="footer" type="submit" onclick="javascipt: form.action='./insertBasket'">장바구니넣기</button>
	</form>
	</div>
</body>
</html>