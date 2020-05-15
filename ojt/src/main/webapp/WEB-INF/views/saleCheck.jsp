<%@ page language="java"
	contentType="text/html; charset=UTF-8; application/json;"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<div class="left" id="left"></div>
		<h3 class="logo">주문 상품 상세</h3>
	</header>
	<form name="paging" method="post" id="frm">
		<input type="hidden" name="memberid" value="${memberid}"/>
		<input type="hidden" name="agentF" value="${agentF}"/>
		<input type="hidden" name="agentA" value="${agentA}"/>
		
		<input type="hidden" name="item" value=""/>
		<div class="sale-list" id="saleList">
		
			<div class="sales center" id="salesF"></div>
			<div class="sales center" id="salesA"></div>
			
			<div class="blankDel"></div>
			<div style="font-family:CJBOLD;margin-top:10px;margin-bottom:10px;margin-left:20px;margin-right:20px;">
			<table style="width:100%;">
				<tr>
					<td style="border-bottom: 0px;text-align: left;">최종 결제금액</td>
					<td style="color:#FF7272;border-bottom: 0px;text-align: right;" id="totVal"></td>
				</tr>
			</table>
			</div>
			<div class="blankDel"></div>
			
		</div>
		<button id="footer" type="submit">주문하기</button>
		
	</form>
	<script type="text/javascript">
	  var totVal=${totVal};
	  var slist=${slist};
	  var saleDiv="${saleDiv}";
	  var A=0, F=0;
	  var strA='<h4 style="color: #5988ED;">상온</h4><table>';
	  var strF='<h4 style="color: #5988ED;">신선</h4><table>';
	  $(document).ready(function(){
		  printSaleList(slist);
		  
		  document.getElementById('totVal').innerHTML=( numberWithCommas(totVal) +" 원");
		  document.getElementById('footer').onclick=function(){
			  $("#frm").attr("action", "./${saleDiv}");
		  }
		  var left = document.getElementById('left');
		  if(saleDiv!="sale"){
			  left.setAttribute('onclick', "location.href=\'../items/basket\'");
		  }else{
			  left.setAttribute('onclick', "location.href=\'../items\'");
		  }
			  
	  });
	  
	  function numberWithCommas(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	  }
	  function getNowDate(){
			return new Date(new Date().setDate(new Date().getDate()+1)).toJSON().slice(0,10);
	  }
	  
	  function printSaleList(slist){
		  $.each(slist, function(index, item){
			  // 신선인 경우
			  if(item.gbn_agent=='F'){
				  str=   '<tr>'
				  str += '<td>'+item.name+'</td>';
				  str += '<td>'+numberWithCommas(item.amount)+'원</td>'
				  str += '<td><input type="hidden" name="qty" value="'+item.qty+'"></td>';
				  str += '<td><input type="hidden" name="amount" value="'+item.price+'"></td>';
				  str += '<td><input type="hidden" name="itemchk" value="'+item.item+'"></td>';
				  str += '<td><input type="hidden" name="amount" value="'+item.amount+'"></td>';
				  str += '<td><input type="hidden" name="agent" value="'+item.agent+'"></td>';
				  str += '<td><input type="hidden" name="name" value="'+item.name+'"></td>';
				  str += '<td><input type="hidden" name="idx" value="'+item.idx+'"></td>';
				  str += '</tr>';
				  strF+=str;
				  F++;
			  }else{ // 상온인 경우
				  str=   '<tr>'
				  str += '<td>'+item.name+'</td>';
				  str += '<td>'+numberWithCommas(item.amount)+'원</td>'
				  str += '<td><input type="hidden" name="qty" value="'+item.qty+'"></td>';
				  str += '<td><input type="hidden" name="amount" value="'+item.price+'"></td>';
				  str += '<td><input type="hidden" name="itemchk" value="'+item.item+'"></td>';
				  str += '<td><input type="hidden" name="amount" value="'+item.amount+'"></td>';
				  str += '<td><input type="hidden" name="agent" value="'+item.agent+'"></td>';
				  str += '<td><input type="hidden" name="name" value="'+item.name+'"></td>';
				  str += '<td><input type="hidden" name="idx" value="'+item.idx+'"></td>';
				  str += '</tr>';
				  strA+= str;
				  A++;
			  }
		}); 
		 
		if(F!=0){
			 strF+='</table>';
			 $('#salesF').append(strF);
			 console.log(strF);
			 strF='<div class="blankDel"></div>';
			 strF+='<div style="font-family:CJBOLD;margin-top:10px;margin-bottom:10px;margin-left:20px;">';
			 strF+='희망 배송 날짜: <input type="date" name="delivDateF" name=""value='+getNowDate()+'></div>';
			 $('#salesF').after(strF);
		}
		if(A!=0){
			 strA+='</table>';
			 $('#salesA').before('<div class="blankDel"></div>');
			 $('#salesA').append(strA);

			 strA='<div class="blankDel"></div>';
			 strA+='<div style="font-family:CJBOLD;margin-top:10px;margin-bottom:10px;margin-left:20px;">';
			 strA+='희망 배송 날짜: <input type="date" name="delivDateA" value='+getNowDate()+'></div>';
			 
			 $('#salesA').after(strA);
		}
		 
	  }
	</script>
</body>
</html>