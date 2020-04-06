<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<h3>로그인</h3>
<!-- 조회에 해당 -->
<form role="form" method="post" autocomplete="off" action="./login">
 <p>
  <label for="id">아이디</label>
  <input type="text" id="id" name="id" />
 </p>
 <p>
  <label for="pwd">비밀번호</label>
  <input type="password" id="password" name="password" />
 </p>
 <p><button type="submit">로그인</button>
 <button type="reset">취소</button></p>
 <p><a href="./signup">회원가입</a></p>
</form>
</body>
</html>