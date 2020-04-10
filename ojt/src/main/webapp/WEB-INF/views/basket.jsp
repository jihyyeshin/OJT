<%@ page language="java" contentType="text/html; charset=UTF-8; application/json;"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<title>basket</title>
</head>
<body>
<p> ${memberid}님의 장바구니</p>
<a href="javascript:history.back();">+</a>
<form method="post" onsubmit="_submit();">
	<table id="tbl"></table>
	<button type="submit" onclick="javascipt: form.action='./sale'">주문하기</button>
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
	function deleteB(){
       	var idx = $("#tbl tr td input").eq(2).val();
       	alert(idx);
       // $("#test").text(text);
		
		$.ajax({
			url:"./deleteBasket",
			type:"POST",
			//dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async:false,
			data:"idx="+idx,
			success: function(data){
				console.log(data+" Deleted");
				//print(data);
				if(data=="OK") showBasket();
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
			var str = '<tr><td><input type="checkbox" name="item" value="'+item.item+'"></td>';
			str += '<td><input type="hidden" name="price" value="'+item.price+'">'+item.price+'</td>';
			str += '<td><input type="hidden" name="idx" value="'+item.idx+'"></td>';
			str += '<td><input type="hidden" name="qty" value="'+item.qty+'">' + item.qty + '</td>';
			str += '<td><input type="button" onclick="deleteB()" value="삭제"></td></tr>';
			$('table').append(str);
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
	    var item=document.getElementsByName("item");
	    var price=document.getElementsByName("price");
	    var qty=document.getElementsByName("qty");
	    var idx=document.getElementsByName("idx");
	    if (typeof(item.length) == 'undefined') //단일
	    {
	        if (item[0].checked==true)
	        {
	        	idx[0].disabled=true;
	        	price[0].disabled=true;
	            qty[0].disabled=true;
	        }
	    } else { 
	    	//다중
	        for (i=0; i<item.length; i++)
	        {
	            if (item[i].checked==false)
	            {
	            	idx[i].disabled=true;
	            	price[i].disabled=true;
		            qty[i].disabled=true;
	            }
	        }
	    }
	    return true;
	}
</script>
</body>
</html>