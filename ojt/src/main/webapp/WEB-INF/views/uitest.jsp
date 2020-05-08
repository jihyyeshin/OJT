<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp"%></head>
<link href="<c:url value="/resources/css/test.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/itemrecommend.css" />"
	rel="stylesheet">
</head>
<body>
	<header id="header" style="height: 85px;">
		<div onclick="location.href='./'" class="left"></div>
		<h3 class="logo">상품 주문</h3>

		<form method="post">
			<button onclick="javascipt: form.action='./items/basket'"
				class="right"></button>
			<input type="hidden" name="memberid" value="${param.memberid}" /> <input
				type="hidden" name="agent" value="${param.agent}" />
		</form>
		<input class="search" type="text" id="search" placeholder="상품 검색">
	</header>
	<form name="paging" onsubmit="_submit();" method="post">
		<input type="hidden" name="memberid" value="${param.memberid}" /> <input
			type="hidden" name="agent" value="${param.agent}" /> <input
			type="hidden" name="item" value="" />
		<div class="item-list">
			<div class="recommend-list">
				<h4 style="text-align: center;">${param.memberid}님을위한추천 상품</h4>
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prev"> <img
							src="<c:url value="/resources/img/left.png" />" width="50px"
							height="50px">
						</a>
					</div>
					<div class="rolling_panel">
						<ul id="recList">
						</ul>
					</div>
					<div class="rightarrow">
						<a href="javascript:void(0)" id="next"> <img
							src="<c:url value="/resources/img/right.png" />" width="50px"
							height="50px">
						</a>
					</div>
				</div>
			</div>
			<!------------------------------------여기----------------------------------------------->
			<!------------------------------------여기----------------------------------------------->
			<!------------------------------------여기----------------------------------------------->

			<div id="itemList" style="position:relative;top:20px;bottom:10px;">
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="float:left;width:50%;">
					<tbody>
						<tr>
							<td>
								<div style="width: 20%;">
								&nbsp;&nbsp;
								<input type="checkbox" name="itemchk" value="174150">
								</div>
								<div style="width: 100%;">
								<img
								src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
								class="center">
								</div>
							</td>
						</tr>
						<tr>
							<td><a id="title" href="javascript:goDetail(174150);">비비고 백300G(GGNH)</a></td>
						</tr>
						<tr>
							<td>3723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
					
			
		</div>

		<button id="footerL" type="submit"
			onclick="javascipt: form.action='./items/sale'">주문하기</button>
		<button id="footerR" type="submit"
			onclick="javascipt: form.action='./items/insertBasket'">장바구니
			넣기</button>
	</form>
</body>
</html>