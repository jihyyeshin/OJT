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
 <p><a href="./location?agent=${member.agentF}">상온</a></p>
 <p><a href="./location?agent=${member.agentA}">식품</a></p>
</c:if>

<p style="color:blue;">${msg}</p>
</body>
</html>