<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
<button type="submit" onclick="javascipt: form.action='./order'">주문</button>
<button type="submit" onclick="javascipt: form.action='./basket'">장바구니</button>
</form>
</body>
</html>