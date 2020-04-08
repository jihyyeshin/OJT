<%@ page language="java" contentType="text/html; charset=UTF-8; application/json;"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ad9f9f4c70ec392a1d41fe14ab44d29&libraries=services"></script>
<title>Location</title>
</head>
<body>
	<h3>자재 정보</h3>
	<table></table>
	<script type="text/javascript">
	var agentId=${param.agent};// 임시 agentId
	
	// 로드되자마자
	$(document).ready(function(){
		showItem();
	});
	
	// 아이템 정보 출력
	function print(data){
		$.each(data, function(index, item){
			var str = '<tr><td>'+item.name+'</td>';
			str += '<td>' + item.amount + '</td></tr>';
			$('table').append(str);
		});
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