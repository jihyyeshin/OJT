<%@ page language="java" contentType="text/html; charset=UTF-8; application/json;"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %></head>
 <link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
<title>basket</title>
</head>
<body>
<header id="header">
	<div onclick="location.href='../items'" class="plus">+</div>
	<h3 class="logo">${memberid}님의 장바구니</h3>
</header>
<form method="post" onsubmit="_submit();">
	<div class="list-group" id="bst"></div>
	<button id="footer" type="submit" onclick="javascipt: form.action='./saleBasket'">주문하기</button>
	<input type="hidden" name="memberid" value="${memberid}"/>
	<input type="hidden" name="agent" value="${agent}"/>
</form>

<script type="text/javascript">
	var memberid=${memberid};
	// 로드되자마자
	$(document).ready(function(){
		showBasket();
	});
	
	// 장바구니 삭제
	function delete_func(btn){
		var parent=btn.parentNode;
		$.ajax({
			url:"./deleteBasket",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data:"idx="+parent.id,
			success: function(data){
				window.location.reload(false);
			},
			error:function(request,status, error){
				console.log("status:\n"+request.status+"\nerror:\n"+request.error);
			},
			complete:function(data){
				console.log("completeD");
			}
		});
	} 
	
	function print(data){
		$.each(data, function(index, item){
			var str = '<div id="'+item.idx+'"><table>';
			str += '<tr><td><input type="checkbox" name="itemchk" value="'+item.item+'"></td>';
			str += '<td id="title">'+item.name+'</td>';
			str += '<td><input type="button" value="X" onclick="delete_func(this);"/></td></tr>'
			str += '<tr style="border-bottom:1px solid #d4d4d4;"><td></td><td style="width:80%;">'+item.price+'</td>';
			str += '<td>'+item.qty + '개</td></tr></table>';
			str += '<input type="hidden" name="idx" value="'+item.idx+'">';
			str += '<input type="hidden" name="qty" value="'+item.qty+'">';
			str += '<input type="hidden" name="amount" value="'+item.price+'"></div>';
			
			$('#bst').append(str);
		});
	}
	// 장바구니 조회
	function showBasket(){
		if(memberid!=null){
			$.ajax({
				url:"./showBasket",
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
	
	// 체크박스에 보내기
	function _submit()
	{
	    //같이 보낼 값
	    var itemchk=document.getElementsByName("itemchk");
	    var amount=document.getElementsByName("amount");
	    var qty=document.getElementsByName("qty");
	    var idx=document.getElementsByName("idx");
	    if (typeof(itemchk.length) == 'undefined') //단일
	    {
	        if (itemchk[0].checked==true)
	        {
	        	idx[0].disabled=true;
	        	amount[0].disabled=true;
	            qty[0].disabled=true;
	        }
	    } else { 
	    	//다중
	        for (i=0; i<itemchk.length; i++)
	        {
	            if (itemchk[i].checked==false)
	            {
	            	idx[i].disabled=true;
	            	amount[i].disabled=true;
		            qty[i].disabled=true;
	            }
	        }
	    }
	    return true;
	}
</script>
</body>
</html>