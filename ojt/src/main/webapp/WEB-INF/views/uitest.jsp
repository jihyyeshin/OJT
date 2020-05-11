<%@ page language="java"
	contentType="text/html; charset=UTF-8; application/json;"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>basket</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<!-- Bootstrap default CSS -->
<link href="/app/resources/css/base.css" rel="stylesheet">
<link href="/app/resources/css/test.css" rel="stylesheet">

</head>
<body>
	<header id="header">
		<div onclick="location.href='../items'" class="left"></div>
		<h3 class="logo">123님의 장바구니</h3>
	</header>
	
	<div class="basket-group">
		<form>
			<div class="recent-order ">
				<div style="height:10px;width:100%;background-color:white;"></div>
				<h4 style="text-align:center;">최근 주문 내역 (2020-05-11)</h4>
				<table class="recent-list">
					<tr>
						<td style="width: 50%;">이름</td>
						<td style="width: 50%;">1,000(원) * 100(개)</td>
					</tr>
				</table>
				<table>
					<tr style="height: 5px;">
						<td></td>
					</tr>
					<tr>
						<td><button type="button" class="btn" style="width:90%;">그대로 주문</button></td>
					</tr>
					<tr style="height: 10px;">
						<td></td>
					</tr>
				</table>

			
			</div>
		</form>
		<div style="height:10px;width:100%;background-color:#d4d4d4;"></div>
		<div style="height:10px;width:100%;background-color:white;"></div>
		

		<form method="post" onsubmit="_submit();">
	<div id="itemList" class="basket-list">
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="idx" value="341"><input
				type="hidden" name="agent" value="386445"><input
				type="hidden" name="qty" value="100"><input type="hidden"
				name="amount" value="14">
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="410014"></td>
						<td id="title">냠냠2</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(342);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">114(원)
							* 20(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="idx" value="342"><input
				type="hidden" name="agent" value="153441"><input
				type="hidden" name="qty" value="20"><input type="hidden"
				name="amount" value="114">
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<table style="border-bottom: 1px solid #d4d4d4; width: 100%;">
				<tbody>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="itemchk" value="186532"></td>
						<td id="title">냠냠</td>
						<td style="width: 15%;"><input type="button" value="X"
							onclick="delete_func(341);"></td>
					</tr>
					<tr>
						<td></td>
						<td style="width: 75%; font-family: CJBOLD; color: #FF7272;">14(원)
							* 100(개)</td>
						<td></td>
					</tr>
				</tbody>
			</table>
		<button id="footer" type="submit"
			onclick="javascipt: form.action='./saleBasket'">주문하기</button>
		<input type="hidden" name="memberid" value="123"> <input
			type="hidden" name="agentF" value="386445"> <input
			type="hidden" name="agentA" value="153441">
		</div>
	</form>
	
</div>
	<script type="text/javascript">
		var memberid = 123;

		// 로드되자마자
		document.addEventListener("DOMContentLoaded", function() {
			showBasket();
		});

		// 가격 포맷 생성
		function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		// 장바구니 삭제
		function delete_func(idx) {
			$
					.ajax({
						url : "./deleteBasket",
						type : "GET",
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						data : "idx=" + idx + "&memberid=" + memberid,
						success : function(data) {
							alert("삭제되었습니다.");

							localStorage.memberid = memberid;
							memberid = localStorage.memberid;
						},
						error : function(request, status, error) {
							console.log("status:\n" + request.status
									+ "\nerror:\n" + request.error);
						},
						complete : function(data) {
							window.location.reload();
						}
					});
		}

		function print(data) {
			$
					.each(
							data,
							function(index, item) {
								var str = '<table style="border-bottom:1px solid #d4d4d4;width:100%;"><tr><td style="text-align: center;"><input type="checkbox" name="itemchk" value="'+item.item+'"></td>';
								str += '<td id="title">' + item.name + '</td>';
								str += '<td style="width: 15%;"><input type="button" value="X" onclick="delete_func('
										+ item.idx + ');"/></td></tr>'
								str += '<tr><td></td><td style="width:75%;font-family:CJBOLD;color:#FF7272;">'
										+ numberWithCommas(item.price)
										+ ' * '
										+ item.qty
										+ '</td><td></td></tr></table>';
								str += '<input type="hidden" name="idx" value="'+item.idx+'">';
								str += '<input type="hidden" name="agent" value="'+item.agent+'">';
								str += '<input type="hidden" name="qty" value="'+item.qty+'">';
								str += '<input type="hidden" name="amount" value="'+item.price+'">';

								$('#itemList').append(str);
							});
		}
		// 장바구니 조회
		function showBasket() {
			if (memberid != null) {
				$
						.ajax({
							url : "./showBasket",
							type : "POST",
							dataType : "json",
							contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							async : false,
							data : "memberid=" + memberid,
							success : function(data) {
								console.log("success");
								print(data);
							},
							error : function(request, status, error) {
								console.log("status:\n" + request.status
										+ "\nerror:\n" + request.error);
							},
							complete : function(data) {
								console.log("complete");
							}
						});
			}
		}

		// 체크박스에 보내기
		function _submit() {
			//같이 보낼 값
			var itemchk = document.getElementsByName("itemchk");
			var amount = document.getElementsByName("amount");
			var qty = document.getElementsByName("qty");
			var idx = document.getElementsByName("idx");
			var agent = document.getElementsByName("agent");

			if (typeof (itemchk.length) == 'undefined') //단일
			{
				if (itemchk[0].checked == true) {
					idx[0].disabled = true;
					amount[0].disabled = true;
					qty[0].disabled = true;
					agent[0].disabled = true;
				}
			} else {
				//다중
				for (i = 0; i < itemchk.length; i++) {
					if (itemchk[i].checked == false) {
						idx[0].disabled = true;
						amount[0].disabled = true;
						qty[0].disabled = true;
						agent[i].disabled = true;
					}
				}
			}
			return true;
		}
	</script>

</body>
</html>