<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %></head>
<title>temporary password</title>
</head>
<body>
<header id="header">
    <div onclick="location.href='./'" class="left"></div>
    <h3 class="logo">�ӽ� ��й�ȣ �߱�</h3>
</header>
<form role="form" method="post" autocomplete="off" action="./tmpPwd">
 <p>
  <label for="id" class="sr-only">���̵�</label>
  <input type="text" id="id" name="id" class="form-control" placeholder="ID" required autofocus>
 </p>
 <button type="submit" class="btn btn-lg btn_blue_default btn-block">�ӽ� ��й�ȣ �߱�</button>
</form>
</body>
</html>