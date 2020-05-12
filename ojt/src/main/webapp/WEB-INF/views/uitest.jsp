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
		<div class="sales center">
			<h4 style="text-align: center;">배송예정날짜: 2020-05-13</h4>
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
		<div style="width: 100%; height: 10px; background-color: #F5F5F5;"></div>
		<div class="sales center">
			<h4 style="text-align: center;">배송예정날짜: 2020-05-14</h4>
			<h4 style="color: #5988ED;">신선</h4>
			<table>
				<tbody>
					<tr>
						<td>한뿌리 홍삼정 골드클래스 100g*2/상온</td>
						<td>828,000원</td>
					</tr>
					<tr>
						<td>비비고/한식간장김자반20G(NEW)/상온</td>
						<td>1,400원</td>
					</tr>
				</tbody>
			</table>
			<h4 style="color: #5988ED;">상온</h4>
			<table>
				<tbody>
					<tr>
						<td>스팸클래식340g*3EA</td>
						<td>90,704원</td>
					</tr>
					<tr>
						<td>사계절쌈장14k캔</td>
						<td>2,280원</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="width: 100%; height: 10px; background-color: #F5F5F5;"></div>
	</div>
	<div id="footerDelivDate">날짜 선정</div>
	<button id="footer" type="submit" onclick="javascipt: form.action='./items/sale'">주문하기</button>
	<!-- <script type="text/javascript">
		var memberid = 123;
		$(document).ready(function() {
			showSaleList();
		});

		function showSaleList() {
			if (memberid != null) {
				$
						.ajax({
							url : "./showSaleList",
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
		// 가격 포맷 생성
		function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		function getNowDate(vary) {
			return new Date(new Date().setDate(new Date().getDate() + vary))
					.toJSON().slice(0, 10);
		}
		// 주문 정보 출력
		function print(data) {
			var str = new Array();
			var buF = new Array();
			var buA = new Array();
			var i = 0;
			for (i = 0; i < 3; i++) {
				buF[i] = '';
				buA[i] = '';
				str[i] = '<div class="sales center"><h4 style="text-align: center;">배송예정날짜: '
						+ getNowDate(i) + '</h4>';
			}
			$.each(data, function(index, item) {
				var amounts = numberWithCommas(item.amt_amount);

				// 오늘, 내일, 모레 배송 건
				for (i = 0; i < 3; i++) {
					if (item.dt_deliv == getNowDate(i)) {
						if (item.gbn_agent == 'F') {
							buF[i] += '<tr><td>' + item.nm_item + '</td>';
							buF[i] += '<td>' + amounts + '원</td></tr>';
						} else if (item.gbn_agent == 'A') {
							buA[i] += '<tr><td>' + item.nm_item + '</td>';
							buA[i] += '<td>' + amounts + '원</td></tr>';
						}
					}
				}
			});
			// 신선
			for (i = 0; i < 3; i++) {
				if (buF[i].length != 0)
					str[i] += ('<h4 style="color: #5988ED;">신선</h4><table>'
							+ buF[i] + '</table>');
				console.log(i + " F len: " + buF[i].length);
			}
			// 상온
			for (i = 0; i < 3; i++) {
				if (buA[i].length != 0)
					str[i] += ('<h4 style="color: #5988ED;">상온</h4><table>'
							+ buA[i] + '</table>');
				console.log(i + " A len: " + buF[i].length);
			}
			// 전체
			for (i = 0; i < 3; i++) {
				if (buF[i].length == 0 && buA[i].length == 0)
					str[i] = '';
				else
					str[i] += '</div><div style="width:100%;height:10px;background-color:#F5F5F5;"></div>';
				$('#saleList').append(str[i]);
				console.log(i + " str len: " + str[i].length);
			}

		}
	</script> -->


</body>
</html>