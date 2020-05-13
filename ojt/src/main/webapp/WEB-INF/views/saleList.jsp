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
	<div class="deliv-list" id="saleList">
		
	</div>
	<script type="text/javascript">
		var memberid=${param.memberid};
		$(document).ready(function(){
			showSaleList();
		});
		
		function showSaleList(){
			if(memberid!=null){
				$.ajax({
					url:"./showSaleList",
					type:"POST",
					dataType:"json",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					async:false,
					data:"memberid="+memberid,
					success: function(data){
						console.log("success");
						print(data);
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
		function getNowDate(vary){
			return new Date(new Date().setDate(new Date().getDate()+vary)).toJSON().slice(0,10);
		}
		// 주문 정보 출력
		function print(data){
			var str = new Array();
			var buF = new Array();
			var buA = new Array();
			var i=0;
			for(i=0;i<3;i++){
				buF[i]='';
				buA[i]='';
				str[i]='<div class="sales center"><h4 style="text-align: center;">배송예정날짜: '+getNowDate(i)+'</h4>';
			}
			$.each(data, function(index, item){
				var amounts=numberWithCommas(item.amt_amount);
				
				// 오늘, 내일, 모레 배송 건
				for(i=0;i<3;i++){
					if(item.dt_deliv == getNowDate(i)){
						if(item.gbn_agent=='F'){
							buF[i]+='<tr><td>'+item.nm_item+'</td>';
							buF[i]+='<td>'+amounts+'원</td></tr>';
						}else if(item.gbn_agent=='A'){
							buA[i]+='<tr><td>'+item.nm_item+'</td>';
							buA[i]+='<td>'+amounts+'원</td></tr>';
						}
					}
				}
			});
			// 신선
			for(i=0;i<3;i++){
				if(buF[i].length!=0) str[i]+=('<h4 style="color: #5988ED;">신선</h4><table>'+buF[i]+'</table>');
				console.log(i+" F len: "+buF[i].length);
			}
			// 상온
			for(i=0;i<3;i++){
				if(buA[i].length!=0) str[i]+=('<h4 style="color: #5988ED;">상온</h4><table>'+buA[i]+'</table>');
				console.log(i+" A len: "+buF[i].length);
			}
			// 전체
			for(i=0;i<3;i++){
				if(buF[i].length==0 && buA[i].length==0) str[i]='';
				else str[i]+='</div><div style="width:100%;height:10px;background-color:#F5F5F5;"></div>';
				$('#saleList').append(str[i]);
				console.log(i+" str len: "+str[i].length);
			}
			
		}
	</script>
	
</body>
</html>