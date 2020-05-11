<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form id="frm" name="frm" action="../items/basket" method="POST">
	<input type="hidden" name="memberid" value="${memberid}"/>
	<input type="hidden" name="agentF" value="${agentF}"/>
	<input type="hidden" name="agentA" value="${agentA}"/>
	
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#frm").submit();
});
</script>
</body>
</html>