<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form id="frm" name="frm" action="./items" method="POST">
	<input type="hidden" name="memberid" value="${memberid}"/>
	<input type="hidden" name="agent" value="${agent}"/>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//alert("agent!!"+${memberid});
	$("#frm").submit();
});
</script>
</body>
</html>