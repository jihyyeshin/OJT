<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>temporary password</title>
</head>
<body>
<h3>�ӽ� ��й�ȣ �߱�</h3>
<form role="form" method="post" autocomplete="off" action="./tmpPwd">
 <p>
  <label for="id">���̵�</label>
  <input type="text" id="id" name="id" />
 </p>
 <p>
 <label for="id">��й�ȣ ��Ʈ</label>
 <input type="text" id="pwdhint" name="pwdhint" />
 </p>
 <p><button type="submit">�ӽ� ��й�ȣ �߱�</button>
 <button type="reset">���</button></p>
</form>
</body>
</html>