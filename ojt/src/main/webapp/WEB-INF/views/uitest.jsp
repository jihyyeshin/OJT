<%@ page language="java"
	contentType="text/html; charset=UTF-8; application/json;"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- 상품 주문 -->
<%@ include file="./header.jsp"%></head>
<link href="<c:url value="/resources/css/test.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/itemrecommend.css" />"
	rel="stylesheet">

<title>주문</title>
</head>
<body>
	<header id="header" style="height: 85px;">
		<div onclick="location.href='./'" class="left"></div>
		<h3 class="logo">상품 주문</h3>

		<!-- 장바구니 상품 조회 -->
		<form method="post">
			<button onclick="javascipt: form.action='./items/basket'"
				class="right"></button>
			<input type="hidden" name="memberid" value="123"> <input
				type="hidden" name="agentF" value="386445"> <input
				type="hidden" name="agentA" value="153441">
		</form>
		<input class="search" type="text" id="search" placeholder="상품 검색">
	</header>
	<form name="paging" onsubmit="_submit();" method="post">
		<input type="hidden" name="memberid" value="123"> <input
			type="hidden" name="agentF" value="386445"> <input
			type="hidden" name="agentA" value="153441"> <input
			type="hidden" name="item" value="">
		<div class="item-list">
			<div class="recommend-list">
				<h4 style="text-align: center;">123님을 위한 추천 상품</h4>
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prev"> <img
							src="/app/resources/img/left.png" width="50px" height="50px">
						</a>
					</div>
					<div class="rolling_panel">
						<ul id="recList">
							<li><table style="width: 100%;">
									<tbody>
										<tr>
											<td style="width: 60%;"><img
												src="https://shopping-phinf.pstatic.net/main_2254456/22544563162.1.jpg?type=f133"
												style="text-align: center;" width="120" height="100"></td>
											<td style="width: 40%; text-align: left;"><br>
											<h4>
													<a id="title" href="javascript:goDetail(172387, 386445);">햄스빌베이컨
														차돌박이스타일 250G/냉장</a>
												</h4>
												<p>4248원</p></td>
										</tr>
									</tbody>
								</table></li>
							<li><table style="width: 100%;">
									<tbody>
										<tr>
											<td style="width: 60%;"><img
												src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
												style="text-align: center;" width="120" height="100"></td>
											<td style="width: 40%; text-align: left;"><br>
											<h4>
													<a id="title" href="javascript:goDetail(174150, 386445);">비비고
														백김치 500G(GGNH)</a>
												</h4>
												<p>3723원</p></td>
										</tr>
									</tbody>
								</table></li>
						</ul>
					</div>
					<div class="rightarrow">
						<a href="javascript:void(0)" id="next"> <img
							src="/app/resources/img/right.png" width="50px" height="50px">
						</a>
					</div>
				</div>
			</div>
			<div class="lvl-list">
				<h4 style="text-align: center;">이런 상품은 어떤가요?</h4>
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prev"> <img
							src="/app/resources/img/left.png" width="50px" height="50px">
						</a>
					</div>
					<div class="rolling_panel">
						<ul id="recList">
							<li><table style="width: 100%;">
									<tbody>
										<tr>
											<td style="width: 60%;"><img
												src="https://shopping-phinf.pstatic.net/main_2254456/22544563162.1.jpg?type=f133"
												style="text-align: center;" width="100" height="88"></td>
											<td style="width: 40%; text-align: left;"><br>
											<h4>
													<a id="title" href="javascript:goDetail(172387, 386445);">햄스빌베이컨
														차돌박이스타일 250G/냉장</a>
												</h4>
												<p>4248원</p></td>
										</tr>
									</tbody>
								</table></li>
							<li><table style="width: 100%;">
									<tbody>
										<tr>
											<td style="width: 60%;"><img
												src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
												style="text-align: center;" width="100" height="88"></td>
											<td style="width: 40%; text-align: left;"><br>
											<h4>
													<a id="title" href="javascript:goDetail(174150, 386445);">비비고
														백김치 500G(GGNH)</a>
												</h4>
												<p>3723원</p></td>
										</tr>
									</tbody>
								</table></li>
						</ul>
					</div>
					<div class="rightarrow">
						<a href="javascript:void(0)" id="next"> <img
							src="/app/resources/img/right.png" width="50px" height="50px">
						</a>
					</div>
				</div>
			</div>
			<div id="itemList"
				style="position: relative; top: 20px; bottom: 10px;">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="186532">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_8207929/82079292631.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(186532, 386445);">비비고/한식간장김자반20G(NEW)/상온</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">923원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="923"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="비비고/한식간장김자반20G(NEW)/상온">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="178623">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_1492788/14927884219.20180730175158.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(178623, 386445);">비비고썰은배추김치더깔끔500G(용기)</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">3,959원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="3959"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="비비고썰은배추김치더깔끔500G(용기)">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="108544">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_2208137/22081377586.20200229154923.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(108544, 386445);">가쓰오 메밀생면568.4G</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">3,090원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="3090"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="가쓰오 메밀생면568.4G">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="410014">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_1728248/17282488309.20190130154914.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(410014, 153441);">사계절쌈장14k캔</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">22,909원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="22909"><input
					type="hidden" name="agent" value="153441"><input
					type="hidden" name="name" value="사계절쌈장14k캔">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="174150">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_1349763/13497636739.20191231194515.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(174150, 386445);">비비고 백김치
									500G(GGNH)</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">3,723원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="3723"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="비비고 백김치 500G(GGNH)">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="100177">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_8095458/8095458474.20191231160408.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(100177, 153441);">스팸클래식340g*3EA</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">11,338원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="11338"><input
					type="hidden" name="agent" value="153441"><input
					type="hidden" name="name" value="스팸클래식340g*3EA">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="108942">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_2270860/22708600483.20200430162843.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(108942, 386445);">맥스봉치즈350G(35G*10개)/냉장</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">3,960원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="3960"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="맥스봉치즈350G(35G*10개)/냉장">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="115715">
								</div>
								<div style="width: 100%;">
									<img src="/app/resources/img/CJ_logo_black.png" class="center"
										style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(115715, 386445);">주부유부왕500G</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">3,927원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="3927"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="주부유부왕500G">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="121068">
								</div>
								<div style="width: 100%;">
									<img src="/app/resources/img/CJ_logo_black.png" class="center"
										style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(121068, 153441);">뉴스모크햄(성남)1000G</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">3,250원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="3250"><input
					type="hidden" name="agent" value="153441"><input
					type="hidden" name="name" value="뉴스모크햄(성남)1000G">
				<table style="float: left; width: 50%; height: 300px;">
					<tbody>
						<tr>
							<td><div style="width: 20%;">
									&nbsp;&nbsp;<input type="checkbox" name="itemchk"
										value="172387">
								</div>
								<div style="width: 100%;">
									<img
										src="https://shopping-phinf.pstatic.net/main_2254456/22544563162.1.jpg?type=f133"
										class="center" style="height: 100px;">
								</div></td>
						</tr>
						<tr>
							<td><a id="title"
								href="javascript:goDetail(172387, 386445);">햄스빌베이컨 차돌박이스타일
									250G/냉장</a></td>
						</tr>
						<tr>
							<td style="font-family: CJBOLD; color: #FF7272;">4,248원</td>
						</tr>
						<tr>
							<td><input type="text" name="qty" style="width: 50%;">개</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="amount" value="4248"><input
					type="hidden" name="agent" value="386445"><input
					type="hidden" name="name" value="햄스빌베이컨 차돌박이스타일 250G/냉장">
			</div>
			<button type="button" onclick="pagingItem()" class="btn"
				style="width: 100%; border-radius: 0px;">더보기</button>
		</div>

		<button id="footerL" type="submit"
			onclick="javascipt: form.action='./items/sale'">주문하기</button>
		<button id="footerR" type="submit"
			onclick="javascipt: form.action='./items/insertBasket'">장바구니
			넣기</button>
	</form>

</body>
</html>