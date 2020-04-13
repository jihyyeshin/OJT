<%@ page language="java" contentType="text/html; charset=UTF-8; application/json;"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %></head>
<title>주문</title>
</head>
<body>
	<h3>아이템 주문</h3>
	<form method="post">
		<button type="submit" onclick="javascipt: form.action='./items/basket'">장바구니</button>
		<input type="hidden" name="memberid" value="${param.memberid}"/>
		<input type="hidden" name="agent" value="${param.agent}"/>
	</form>
	<form name="paging" onsubmit="_submit();" method="post">
		<button type="submit" onclick="javascipt: form.action='./items/sale'">주문하기</button>
		<button type="submit" onclick="javascipt: form.action='./items/insertBasket'">장바구니넣기</button>
		<input type="hidden" name="memberid" value="${param.memberid}"/>
		<input type="hidden" name="agent" value="${param.agent}"/>
		<input type="hidden" name="item"/>
		<table></table>
	</form>
	<script type="text/javascript">
	var agentId=${param.agent};
	// 로드되자마자
	$(document).ready(function(){
		showItem();
	});
	
	// 아이템 정보 출력
	function print(data){
		$.each(data, function(index, item){
			var str = '<tr><td><input type="checkbox" name="itemchk" value="'+item.item+'"></td>';
			str += '<td><a href="javascript:goDetail('+item.item+');">'+item.name+'</a></td>';
			str += '<td><input type="hidden" name="name" value="'+item.name+'"></td>'
			str += '<td><input type="hidden" name="price" value="'+item.amount+'">' + item.amount + '</td>';
			str += '<td><input type="text" name="qty">개</td></tr>';
			$('table').append(str);
		});
	}
	// go 디테일 페이지
	function goDetail(item){
		var f=document.paging;
		f.item.value=item;
		f.agent.value=${param.agent};
		f.memberid.value=${param.memberid};

		f.action="./items/detail"
		f.method="post"
		f.submit();
	};
	
	// 체크박스에 보내기
	function _submit()
	{
	    //같이 보낼 값
	    var item=document.getElementsByName("item");
	    var price=document.getElementsByName("price");
	    var qty=document.getElementsByName("qty");
	    if (typeof(item.length) == 'undefined') //단일
	    {
	        if (item[0].checked==true)
	        {
	        	
	        	price[0].disabled=true;
	            qty[0].disabled=true;
	        }
	    } else { 
	    	//다중
	        for (i=0; i<item.length; i++)
	        {
	            if (item[i].checked==false)
	            {
	            	price[i].disabled=true;
		            qty[i].disabled=true;
	            }
	        }
	    }
	    return true;
	}
	
	// 대리점 별 아이템 및 가격 정보 조회
	function showItem(){
		if(agentId!=null){
			$.ajax({
				url:"./showItem",
				type:"POST",
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				async:false,
				data:"agent="+agentId,
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
</script>
</body>
</html>