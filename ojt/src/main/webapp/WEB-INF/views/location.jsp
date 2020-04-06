<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<h3>위치를 입력하세요</h3>
	<label for="myloc">위치1: </label>
	<input type="text" id="myloc" name="myloc"/>
	<input type="button" onclick="locCalc();" value="거리계산"/>
	<h3>가장 가까운 대리점의 위치</h3>
	<p id="resultAgent"></p>
	<p id="resultLocation"></p>
	<input type="button" onclick="showItem();" value="아이템 조회"/>
	
	<script>
	var agentId;
	
	function locCalc() {
			var geocoder = new kakao.maps.services.Geocoder();
			var loc=$("#myloc").val();
			
			if(loc=="" || loc==null || loc==undefined)
				$("#result").html("위치를 입력하세요!");
			else {
				var locArray=loc.split(' ');
				if(locArray[1]==undefined || locArray[1]=="" || locArray[1]==null)
					loc=locArray[0];
				else loc=locArray[0]+" "+locArray[1]; 
				var lat, lng;
				// 위경도 계산
				geocoder.addressSearch(loc, function(result, status) {
				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
				    	lat=result[0].y;
				    	lng=result[0].x;
				    	console.log("myloc: "+lat+", "+lng)
				    	// 통신
				    	$.ajax({
							url:"./locationTest",
							type:"POST",
							dataType:"text",
							contentType: "application/x-www-form-urlencoded; charset=UTF-8",
							async:false,
							data: "location="+loc+"&lat="+lat+"&lng="+lng,
							success: function(data){
								// 받아온 결과물 저장
								var resultArray=data.split('|');
								$("#resultAgent").html(resultArray[1]);
								$("#resultLocation").html(resultArray[2]);
								agentId=resultArray[0];// 아이템 검색 시 사용
							},
							error:function(request,status, error){
								console.log("status:\n"+request.status+"\nerror:\n"+request.error);
							},
							complete:function(data){
								console.log("success");
							}
						});
				    } 
				});
			}
		}
	function showItem(){
		console.log('??');
		//if(agentId!=null){
			$.ajax({
				url:"./showItem",
				type:"POST",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				async:false,
				success: function(data){
					console.log(data);
				},
				error:function(request,status, error){
					console.log("status:\n"+request.status+"\nerror:\n"+request.error);
				},
				complete:function(data){
					console.log("success");
				}
			});
		//}
	}
</script>
</body>
</html>