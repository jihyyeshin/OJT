<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>item detail</title>
</head>
<body>
<h3>������ ����</h3>
<p> ${item.name}</p>
<p> ${item.remark}</p>
<!-- �׸� ���;� �� -->
<form method="post" name="form">
<input type="hidden" name="item" id="item" value="${item.item}"/>
<p>����: <input type="text" name="qty" id="qty"/></p>
<button type="submit" onclick="javascipt: form.action='./order'">�ֹ�</button>
<button type="submit" onclick="javascipt: form.action='./basket'">��ٱ���</button>
</form>
</body>
</html>