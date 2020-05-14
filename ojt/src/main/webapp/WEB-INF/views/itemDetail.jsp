<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %>
 <link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
<title>item detail</title>
</head>
<body>
	<header id="header">
		<div onclick="location.href='../items'" class="left"></div>
		<h3 class="logo">아이템 정보</h3>
		
	</header>
	
	<div class="view">
		<img src="${src} " width="100%"></img>
		<p></p>
		<h4 style="text-align:center;font-size:1.2rem;"> ${item.name}</h4>
		<p style="text-align:center;"> ${item.remark}</p>
		
		
		<form method="post" name="form">
			<input type="hidden" name="itemDiv" id="itemDiv" value="${item.item}"/>
			<p style="margin-left:20px;">수량: <input type="text" name="qty" id="qty"/>개</p>
			<h4 style="margin-left:20px;font-family: CJBOLD;color:#FF7272;font-size:1.2rem;" id="amounts"> </h4>
			<input type="hidden" name="memberid" value="${memberid}"/>
			<input type="hidden" name="agentF" value="${agentF}"/>
			<input type="hidden" name="agentA" value="${agentA}"/>
			<input type="hidden" name="agent" value="${item.agent}"/>
			
			<input type="hidden" name="name" value="${item.name}"/>
			<input type="hidden" name="amount" value="${item.amount}"/>
			
			<button id="footer" type="submit" onclick="javascipt: form.action='./insertBasket'">장바구니넣기</button>
		</form>
		<a href="${LowestUrl} " style="margin-left:20px;text-decoration: underline;color:#474747;font-size:1.2rem;">인터넷 최저가 ${LowestCost}원</a>
	</div>
	<script type="text/javascript">
	
	$(document).ready(function(){
		var amounts=${item.amount};
		$('#amounts').html(numberWithCommas(amounts)+"원");
	});
	
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	</script>
</body>
</html>