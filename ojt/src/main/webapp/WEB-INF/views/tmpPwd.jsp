<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>temporary password</title>
</head>
<body>
<h3>임시 비밀번호 발급</h3>
<form role="form" method="post" autocomplete="off" action="./tmpPwd">
 <p>
  <label for="id">아이디</label>
  <input type="text" id="id" name="id" />
 </p>
 <p>
 <label for="id">비밀번호 힌트</label>
 <input type="text" id="pwdhint" name="pwdhint" />
 </p>
 <p><button type="submit">임시 비밀번호 발급</button>
 <button type="reset">취소</button></p>
</form>
</body>
</html>