<%@ page language="java"
	contentType="text/html; charset=UTF-8; application/json;"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- 주문 내역 -->
<%@ include file="./header.jsp"%></head>
<link href="<c:url value="/resources/css/sale.css" />" rel="stylesheet">

<title>주문내역조회</title>
</head>
<body>
	<header id="header">
		<div onclick="location.href='./'" class="left"></div>
		<h3 class="logo">나의 주문정보</h3>
	</header>

	<div class="deliv-list">
		<form class="center" method="post">
			<div style="font-family: CJBOLD; margin-top: 10px; margin-bottom: 10px; text-align: center;">
				날짜: <input type="date" id="delivDate" name="delivDate" style="width:50%;height:100%;">
				<button type="button" class="btn" onclick="changeDate();">조회</button>
			</div>
		</form>
		<div class="blankDel" id="texxt"></div>
		<div id="saleList"></div>
		

	</div>
	<script type="text/javascript">
		var memberid=${param.memberid};
		$(document).ready(function(){
			var nowDate=getNowDate();
			$('#delivDate').val(nowDate);
			showSaleList(nowDate);
		});
		function changeDate(){
			var date=$('[name="delivDate"]');
			//alert(date.val());
			showSaleList(date.val());
		}
		function showSaleList(date){
			if(memberid!=null){
				$.ajax({
					url:"./showSaleList",
					type:"POST",
					dataType:"json",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					async:false,
					data:"memberid="+memberid+"&date="+date,
					success: function(data){
						console.log("success");
						print(data, date);
					},
					error:function(request,status, error){
						console.log("status:\n"+request.status+"\nerror:\n"+request.error);
					},
					complete:function(data){
						console.log("complete");
					}
				});
			}
		}
		// 가격 포맷 생성
		function numberWithCommas(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		function getNowDate(){
			return new Date(new Date().setDate(new Date().getDate())).toJSON().slice(0,10);
		}
		// 주문 정보 출력
		function print(data, date){
			var str='<div class="sales center"><h4 style="text-align: center;">배송예정날짜: '+date+'</h4>';
			var buF='';
			var buA='';
			var agentF;
			var agentA;
			var i=0;
			
			$.each(data, function(index, item){
				var amounts=numberWithCommas(item.amt_amount);
				
				// 배송 건
				if(item.gbn_agent=='F'){
					buF+='<tr><td>'+item.nm_item+'</td>';
					buF+='<td>'+amounts+'원</td></tr>';
					agentF=item.nm_agentform;
				}else if(item.gbn_agent=='A'){
					buA+='<tr><td>'+item.nm_item+'</td>';
					buA+='<td>'+amounts+'원</td></tr>';
					agentA=item.nm_agentform;
				}
			});
			// 신선
			if(buF.length!=0) {
				str+=('<h4 style="color:#5988ED;display : inline-block">신선 </h4>');
				str+=('<h4 style="color:#5988ED;font-family:CJLIGHT;display : inline-block">('+agentF+')</h4>');
				str+=('<table>'+buF+'</table>');
			}
			//console.log(buF.length);
			
			// 상온
			if(buA.length!=0) {
				str+=('<h4 style="color:#5988ED;display : inline-block">상온  </h4>');
				str+=('<h4 style="color:#5988ED;font-family:CJLIGHT;display : inline-block">('+agentA+')</h4>');
				str+=('<table>'+buA+'</table>');
			}
			//console.log(buA.length);

			// 전체
			$('#saleList').html('');//초기화
			if(buF.length==0 && buA.length==0) str='<div style="text-align:center;">해당 날짜에 주문 정보가 없습니다.</div>';
			else str+='</div><div class="blankDel"></div>';
			$('#saleList').append(str);
			console.log(i+" str len: "+str.length);
		}
	</script>
	
</body>
</html>