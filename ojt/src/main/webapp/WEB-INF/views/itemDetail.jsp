<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %>
<title>item detail</title>
</head>
<body>
<h3>아이템 정보</h3>
<p> ${item.name}</p>
<p> ${item.remark}</p>
<!-- 그림 들어와야 함 -->
<form method="post" name="form">
	<input type="hidden" name="item" id="item" value="${item.item}"/>
	<p>수량: <input type="text" name="qty" id="qty"/></p>
	<button type="submit" onclick="javascipt: form.action='./insertBasket'">장바구니넣기</button>
	<input type="hidden" name="memberid" value="${memberid}"/>
	<input type="hidden" name="agent" value="${agent}"/>
	<input type="hidden" name="name" value="${item.name}"/>
	<input type="hidden" name="amount" value="${item.amount}"/>
</form>
<a href="../items">뒤로가기</a>
</body>
</html>