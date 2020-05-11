<%@ page language="java" contentType="text/html; charset=UTF-8; application/json;"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- 상품 주문 -->
<%@ include file="./header.jsp" %></head>
 <link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/itemrecommend.css" />" rel="stylesheet">

<title>주문</title>
</head>
<body>
	<header id="header" style="height:85px;">
		<div onclick="location.href='./'" class="left"></div>
		<h3 class="logo">상품 주문</h3>
		
		<!-- 장바구니 상품 조회 -->
		<form method="post">
			<button onclick="javascipt: form.action='./items/basket'" class="right"></button>
			<input type="hidden" name="memberid" value="${param.memberid}"/>
			<input type="hidden" name="agentF" value="${param.agentF}"/>
			<input type="hidden" name="agentA" value="${param.agentA}"/>
		</form>
		<input class="search" type="text" id="search" placeholder="상품 검색">
	</header>
	<form name="paging" onsubmit="_submit();" method="post">
		<input type="hidden" name="memberid" value="${param.memberid}"/>
		<input type="hidden" name="agentF" value="${param.agentF}"/>
		<input type="hidden" name="agentA" value="${param.agentA}"/>
		<input type="hidden" name="item" value=""/>
		<div class="item-list">
			<div class="recommend-list">
				<h4 style="text-align:center;">${param.memberid}님을 위한 추천 상품</h4>
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prev"> <img
							src="<c:url value="/resources/img/left.png" />" width="50px"
							height="50px">
						</a>
					</div>
					<div class="rolling_panel">
						<ul id="recList">
						</ul>
					</div>
					<div class="rightarrow">
						<a href="javascript:void(0)" id="next"> <img
							src="<c:url value="/resources/img/right.png" />" width="50px"
							height="50px">
						</a>
					</div>
				</div>
			</div>
			<div id="itemList" style="position:relative;top:20px;bottom:10px;"></div>
		</div> 
			
		<button id="footerL" type="submit" onclick="javascipt: form.action='./items/sale'">주문하기</button>
		<button id="footerR" type="submit" onclick="javascipt: form.action='./items/insertBasket'">장바구니 넣기</button>
	</form>
	
	<script type="text/javascript">
	var agentF=${param.agentF};
	var agentA=${param.agentA};
	var memberId=${param.memberid};
	// 로드되자마자
	$(document).ready(function(){
		showItem();
		search();
		// 추천 리스트
		showRecommendedItems();
		
		/* 상품 슬라이드 */
		var $panel = $(".rolling_panel").find("ul");
        	var itemWidth = $panel.children().outerWidth();
        	
        	// 이전 이벤트
        	$("#prev").on("click", prev);
        	$("#prev").mouseover();
        	// 다음 이벤트
        	$("#next").on("click", next);
        	$("#next").mouseover();
                
        	// 이전 이벤트 실행
        	function prev(e) {
            	$panel.css("left", - itemWidth);
            	$panel.prepend("<li>" + $panel.find("li:last").html() + "</li>");
            	$panel.animate({"left": "0px"}, function() {
                	$(this).find("li:last").remove();
                });
            }
        	// 다음 이벤트 실행
        	function next(e) {
            	$panel.animate({"left": - itemWidth + "px"}, function() {
                	$(this).append("<li>" + $(this).find("li:first").html() + "</li>");
                	$(this).find("li:first").remove();
                	$(this).css("left", 0);
                });
            }
	});
	// 대리점 별 아이템 및 가격 정보 조회
	function showItem(){
		
		if(agentF!=null && agentA!=null){
			$.ajax({
				url:"./showItem",
				type:"POST",
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				async:false,
				data:"agentF="+agentF+"&agentA="+agentA,
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

	// 아이템 정보 출력
	function print(data){
		$.each(data, function(index, item){
			var src=item.src;
			if(src == "") src="<c:url value="/resources/img/CJ_logo_black.png" />";
			//console.log("agent?"+item.agent);
			var amounts=numberWithCommas(item.amount);
			
			var str='<table style="float:left;width:50%;height:300px;">';
			str+=	'<tbody>';
			str+=	'<tr>';
			str+=	'<td>';
			str+=	'<div style="width: 20%;">';
			str+=	'&nbsp;&nbsp;';
			str+=	'<input type="checkbox" name="itemchk" value="'+item.item+'">';
			str+=	'</div>';
			str+=	'<div style="width: 100%;">';
			str+=	'<img src="'+src+'" class="center" style="height:100px;">';
			str+=	'</div>';
			str+=	'</td>';
			str+=	'</tr>';
			str+=	'<tr>';
			str+=	'<td><a id="title" href="javascript:goDetail('+item.item+', '+item.agent+');">'+item.name+'</a></td>';
			str+=	'</tr>';
			str+=	'<tr>';
			str+=	'<td style="font-family: CJBOLD;">' + amounts + '원</td>';
			str+=	'</tr>';
			str+=	'<tr>';
			str+=	'<td><input type="text" name="qty" style="width: 50%;">개</td>';
			str+=	'</tr>';
			str+=	'<tr>';
			str+=	'<td>&nbsp;&nbsp;</td>';
			str+=	'</tr>';
			str+=	'</tbody>';
			str+=	'</table>';
			str += '<input type="hidden" name="amount" value="'+item.amount+'">';
			str += '<input type="hidden" name="agent" value="'+item.agent+'">';
			str += '<input type="hidden" name="name" value="'+item.name+'">';
			$('#itemList').append(str);
		});
	}
	
	// 추천 아이템 및 가격 정보 조회
	function showRecommendedItems(){
		if(agentF!=null && agentA!=null &&memberId!=null){
			$.ajax({
				url:"./showRecommendedItems",
				type:"POST",
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				async:false,
				data:"agentF="+agentF+"&agentA"+agentA+"&memberid="+memberId,
				success: function(data){
					console.log("success");
					console.log(data);
					printRecItem(data);
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
	
	// 추천 아이템 출력
	function printRecItem(data){
		$.each(data, function(index, item){
			var src=item.src;
			if(src == "") src="<c:url value="/resources/img/CJ_logo_black.png" />";
			
			if(index==0) console.log("rec agent"+item.agent);
			
			var str = '<li>';
			str +=	'<table style="width: 100%;">';
			str += '<tr>';
			str += '<td style="width: 60%;">';
			str += '<img src="<c:url value="'+src+'" />"';
			str += 'style="text-align: center;" width="100" height="88"></td>';
			str += '<td style="width: 40%; text-align: left;">';
			str += '<br/>';
			str += '<h4><a id="title" href="javascript:goDetail('+item.item+', '+item.agent+');">'+item.name+'</a></h4>';
			str += '<p>' + item.amount + '원</p>';
			str += '</td>';
			str += '</tr>';
			str += '</table>';
			str += '</li>';
			
			$('#recList').append(str);
		});
	}
	
	// 상품 검색
	function search(){
		var $rows = $('#itemList table');
		$('#search').keyup(function() {
		    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
		    
		    $rows.show().filter(function() {
		        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
		        return !~text.indexOf(val);
		    }).hide();
		});
	}
	
	// go 디테일 페이지
	function goDetail(item, agent){
		var f=document.paging;
		f.item.value=item;
		console.log("detail click item!"+item);
		console.log("detail click!"+agent);
		f.agent.value=agent;
		f.agentF.value=agentF;
		f.agentA.value=agentA;
		
		f.memberid.value=${param.memberid};

		f.action="./items/detail"
		f.method="post"
		f.submit();
	};
	
	// 체크박스에 보내기
	function _submit()
	{
	    //같이 보낼 값
	    var itemchk=document.getElementsByName("itemchk");
	    var amount=document.getElementsByName("amount");
	    var qty=document.getElementsByName("qty");
	    var agent=document.getElementsByName("agent");
	    var name=document.getElementsByName("name");
	    if (typeof(itemchk.length) == 'undefined') //단일
	    {
	        if (itemchk[0].checked==true)
	        {
	        	name[0].disabled=true;
	        	amount[0].disabled=true;
	            qty[0].disabled=true;
	            agent[0].disabled=true;
	        }
	    } else { 
	    	//다중
	        for (i=0; i<itemchk.length; i++)
	        {
	            if (itemchk[i].checked==false)
	            {
	            	name[i].disabled=true;
	            	amount[i].disabled=true;
		            qty[i].disabled=true;
		            agent[i].disabled=true;
	            }
	        }
	    }
	    return true;
	}
	
	
</script>
</body>
</html>