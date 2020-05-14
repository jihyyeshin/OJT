<%@ page language="java"
	contentType="text/html; charset=UTF-8; application/json;"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp"%></head>
<link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
<title>basket</title>
</head>
<body>
	<header id="header">
		<div onclick="location.href='../items'" class="left"></div>
		<h3 class="logo">${param.memberid}님의장바구니</h3>
	</header>
	<div class="basket-group">
		<form method="post" action="./saleSameCheck">
			<input type="hidden" name="memberid" value="${param.memberid}" /> 
			<input type="hidden" name="agentF" value="${param.agentF}" /> 
			<input type="hidden" name="agentA" value="${param.agentA}" />
			<input type="hidden" name="saleDiv" value="saleRecent">
			<div class="recent-order ">
				<div style="height: 10px; width: 100%; background-color: white;"></div>
				<h4 style="text-align: center;" id="recentdate"></h4>
				<table class="recent-list" id="recentList"></table>
				<table id="saleSame"></table>
			</div>
		</form>
		<div class="blankDel"></div>
		<div style="height: 10px; width: 100%; background-color: white;"></div>

		<form method="post" onsubmit="_submit();" id="basketSale">
			<div id="itemList" class="basket-list"></div>
			<button id="footer" type="submit"
				onclick="javascipt: form.action='./saleCheck'">주문하기</button>
			<input type="hidden" name="memberid" value="${param.memberid}" /> 
			<input type="hidden" name="agentF" value="${param.agentF}" /> 
			<input type="hidden" name="agentA" value="${param.agentA}" />
			<input type="hidden" name="saleDiv" value="saleBasket" />
		</form>
	</div>

	<script type="text/javascript">
		var memberid = ${param.memberid};

		// 로드되자마자
		document.addEventListener("DOMContentLoaded", function() {
			showBasket();
			showRecent();
		});

		// 가격 포맷 생성
		function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		// 장바구니 삭제
		function delete_func(idx) {
			$.ajax({
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

		
		// 장바구니 조회
		function showBasket() {
			if (memberid != null) {
				$.ajax({
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
		// 최근 구매 상품 조회
		function showRecent(){
			if (memberid != null) {
				$.ajax({
					url : "./showRecent",
					type : "POST",
					dataType : "json",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					async : false,
					data : "memberid=" + memberid,
					success : function(data) {
						console.log("success");
						printRecent(data);
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
		
		function print(data) {
			if(data.length==0) {
				var str='<h4 style="text-align:center;font-style:italic;opacity:0.5;color:#5988ED;">장바구니가 비었습니다.</h4>'
				$('#itemList').append(str);
			}
			else{
				$.each(data,function(index, item) {
					var str = '<table style="border-bottom:1px solid #d4d4d4;width:100%;"><tr><td style="text-align: center;"><input type="checkbox" name="itemchk" value="'+item.item+'"></td>';
					str += '<td id="title">' + item.name + '</td>';
					str += '<td style="width: 15%;"><input type="button" value="X" onclick="delete_func('+ item.idx + ');"/></td></tr>'
					str += '<tr><td></td><td style="width:75%;font-family:CJBOLD;color:#FF7272;">'
						+ numberWithCommas(item.price)+ '(원) * '+ item.qty + '(개)</td><td></td></tr></table>';
					str += '<input type="hidden" name="idx" value="'+item.idx+'">';
					str += '<input type="hidden" name="agent" value="'+item.agent+'">';
					str += '<input type="hidden" name="qty" value="'+item.qty+'">';
					str += '<input type="hidden" name="amount" value="'+item.price+'">';
					str += '<input type="hidden" name="name" value="'+item.name+'">';
					$('#itemList').append(str);
				});
			}
			
		}
		
		function printRecent(data) {
			if(data.length==0) {
				var str='<h4 style="text-align:center;font-style:italic;opacity:0.5;color:#5988ED;">주문 내역이 없습니다.</h4>'
				$('#recentList').append(str);
			}else{
				var rd=data[0].dt_sale;
				$("#recentdate").append('최근 주문 내역 ('+rd+')');
				
				$.each(data,function(index, item) {
					var str = '<tr style="border-top:1px solid #d4d4d4;">';
					str += '<td style="width: 50%;">'+item.name+'</td>';
					str += '<td style="width: 50%;">'+numberWithCommas(item.price)+'(원) * '+item.qty+'(개)</td>';
					str += '<input type="hidden" name="qtyS" value="'+item.qty+'">';
					str += '<input type="hidden" name="amountS" value="'+item.price+'">';
					str += '<input type="hidden" name="itemchkS" value="'+item.item+'">';
					str += '<input type="hidden" name="agentS" value="'+item.agent+'">';
					str += '<input type="hidden" name="nameS" value="'+item.name+'">';
					str += '</tr>';
						
					$('#recentList').append(str);
				
				});
				
				var str = '<tr style="height: 5px;">';
				str += '<td></td>';
				str += '</tr>';
				str += '<tr>';
				str += '<td><button type="submit" class="btn" style="width: 90%;" >그대로 주문하기</button></td>';
				str += '</tr>';
				str += '<tr style="height: 10px;">';
				str += '<td></td>';
				str += '</tr>';
				$('#saleSame').append(str);
			}
		}

		// 체크박스에 보내기
		function _submit() {
			//같이 보낼 값
			var itemchk =document.getElementsByName("itemchk");
			var amount = document.getElementsByName("amount");
			var qty = document.getElementsByName("qty");
			var idx = document.getElementsByName("idx");
			var agent = document.getElementsByName("agent");
			var name=document.getElementsByName("name");
			if (typeof (itemchk.length) == 'undefined') //단일
			{
				if (itemchk[0].checked == false) {
					idx[0].disabled = true;
					amount[0].disabled = true;
					qty[0].disabled = true;
					agent[0].disabled = true;
					name[0].disabled=true;
				}
			} else {
				//다중
				for (i = 0; i < itemchk.length; i++) {
					if (itemchk[i].checked == false) {
						idx[i].disabled = true;
						amount[i].disabled = true;
						qty[i].disabled = true;
						agent[i].disabled = true;
						name[i].disabled=true;
					}
				}
			}
			return true;
		}
	</script>
</body>
</html>