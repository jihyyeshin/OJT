<%@ page language="java"
	contentType="text/html; charset=UTF-8; application/json;"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- 상품 주문 -->
<%@ include file="./header.jsp"%></head>
<link href="<c:url value="/resources/css/sale.css" />" rel="stylesheet">


<title>주문</title>
</head>
<body>
	<header id="header">
		<div onclick="location.href='./'" class="left"></div>
		<h3 class="logo">주문 상품 상세</h3>
	</header>
	<div class="sale-list" id="saleList">
		<div class="sales center" id="sales">
			<h4 style="color: #5988ED;">신선</h4>
			<table>
				<tbody>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>1,400원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>1,400원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>4,615원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>19,795원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>102,453원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>2,197,245원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>923원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>130,647원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>130,647원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>4,790,390원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>12,922원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>18,960원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>10,153원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>396,000원</td>
					</tr>
					<tr>
						<td>비비고썰은배추김치더깔끔500G(용기)</td>
						<td>9,060원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>30,459원</td>
					</tr>
				</tbody>
			</table>
			<h4 style="color: #5988ED;">상온</h4>
			<table>
				<tbody>
					<tr>
						<td>사계절쌈장14k캔</td>
						<td>2,280원</td>
					</tr>
					<tr>
						<td>사계절쌈장14k캔</td>
						<td>2,280원</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="blankDel"></div>
		<div style="font-family:CJBOLD;margin-top:10px;margin-bottom:10px;margin-left:20px;">
		희망 배송 날짜: <input type="date" id="currnetDate">
		</div>
		<div class="blankDel"></div>
		<div style="font-family:CJBOLD;margin-top:10px;margin-bottom:10px;margin-left:20px;">
		<table style="width:100%;">
			<tr>
				<td style="border-bottom: 0px;text-align: left;">최종 결제금액</td>
				<td style="color:#FF7272;border-bottom: 0px;text-align: right;">1000원</td>
			</tr>
		</table>
		</div>
		<div style="width: 100%; height: 10px; background-color: #F5F5F5;"></div>
		
	</div>
	<button id="footer" type="submit" onclick="javascipt: form.action='./items/sale'">주문하기</button>
	<script type="text/javascript">
	  document.getElementById('currnetDate').value= new Date().toJSON().slice(0, 10);
	  
	</script>
</body>
</html>