<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>

<c:if test="${member == null }">
<h3>Index Page</h3>
 <p><a href="./login">로그인</a></p>
</c:if>

<c:if test="${member != null }">
<h3>Hello</h3>
 <p> ${member.name}님 환영합니다.</p>
 <p><a href="./logout">로그아웃</a></p>
 <form action="./items" method="post">
 	<input type="text" name="agent" value="${member.agentF}"/>
 	<input type="text" name="memberid" value="${member.id}"/>
 	<button type="submit">상온</button>
 </form>
 <form action="./items" method="post">
 	<input type="text" name="agent" value="${member.agentA}"/>
 	<input type="text" name="memberid" value="${member.id}"/>
 	<button type="submit">신선</button>
 </form>
</c:if>

<p style="color:blue;">${msg}</p>
</body>
</html>