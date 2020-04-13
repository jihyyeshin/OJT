<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %>
<title>login</title>
</head>
<body>
<h3>배송서비스</h3>
<c:if test="${member == null }">
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
 <p><a href="./tmpPwd">비밀번호를 잊어버리셨나요?</a></p>
</form>
</c:if>

<c:if test="${member != null }">
 <p> ${member.name}님 환영합니다.</p>
 <form action="./items" method="post">
 	<input type="hidden" name="agent" value="${member.agentF}"/>
 	<input type="hidden" name="memberid" value="${member.id}"/>
 	<button type="submit">상온</button>
 </form>
 <form action="./items" method="post">
 	<input type="hidden" name="agent" value="${member.agentA}"/>
 	<input type="hidden" name="memberid" value="${member.id}"/>
 	<button type="submit">신선</button>
 </form>
 <p><a href="./logout">로그아웃</a></p>
</c:if>
<p style="color:blue;">${msg}</p>
</body>
</html>