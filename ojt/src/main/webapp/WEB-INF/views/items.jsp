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
		
		<form method="post">
			<button onclick="javascipt: form.action='./items/basket'" class="right"></button>
			<input type="hidden" name="memberid" value="${param.memberid}"/>
			<input type="hidden" name="agent" value="${param.agent}"/>
		</form>
		<input class="search" type="text" id="search" placeholder="상품 검색">
	</header>
	<form name="paging" onsubmit="_submit();" method="post">
		<input type="hidden" name="memberid" value="${param.memberid}"/>
		<input type="hidden" name="agent" value="${param.agent}"/>
		<input type="hidden" name="item" value=""/>
		<div class="item-list">
			<div class="recommend-list">
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prev"> <img
							src="<c:url value="/resources/img/left.png" />" width="50px"
							height="50px">
						</a>
					</div>

					<div class="rolling_panel">
						<ul>
							<li>
								<table>
									<tr>
										<td style="width: 60%;"><img
											src="<c:url value="/resources/img/sampleimg.png" />"
											style="text-align: center;" width="100" height="88"></td>
										<td style="width: 40%; text-align: left;">
											<p>제품 이름</p>
											<p>1000원</p>
										</td>
									</tr>
								</table>
							</li>
							<li>
								<table>
									<tr>
										<td style="width: 60%;"><img
											src="<c:url value="/resources/img/sampleimg.png" />"
											style="text-align: center;" width="100" height="88"></td>
										<td style="width: 40%; text-align: left;">
											<p>제품 이름2</p>
											<p>2000원</p>
										</td>
									</tr>
								</table>
							</li>
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
			<div id="itemList" class ="list-group"></div>
		</div> 
			
		<button id="footerL" type="submit" onclick="javascipt: form.action='./items/sale'">주문하기</button>
		<button id="footerR" type="submit" onclick="javascipt: form.action='./items/insertBasket'">장바구니 넣기</button>
	</form>
	
	<script type="text/javascript">
	var agentId=${param.agent};
	// 로드되자마자
	$(document).ready(function(){
		showItem();
		search();
		
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
	
	// 아이템 정보 출력
	function print(data){
		$.each(data, function(index, item){
			var str = '<table style="border-bottom:1px solid #d4d4d4;width:100%;"><tr><td style="text-align: center;">';
			str += '<input type="checkbox" name="itemchk" value="'+item.item+'"></td>';			
			str += '<td><a id="title" href="javascript:goDetail('+item.item+');">'+item.name+'</a></td></tr>';
			str += '<tr><td></td><td style="width: 65%;">' + item.amount + '원</td>';
			str += '<td style="width: 25%;"><input type="text" name="qty" style="width: 50%;">개</td></tr></table>';
			str += '<input type="hidden" name="amount" value="'+item.amount+'">';
			str += '<input type="hidden" name="name" value="'+item.name+'">';
			$('#itemList').append(str);
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
	    var itemchk=document.getElementsByName("itemchk");
	    var amount=document.getElementsByName("amount");
	    var qty=document.getElementsByName("qty");
	    var name=document.getElementsByName("name");
	    //alert(itemchk[0]);
	    if (typeof(itemchk.length) == 'undefined') //단일
	    {
	        if (itemchk[0].checked==true)
	        {
	        	name[0].disabled=true;
	        	amount[0].disabled=true;
	            qty[0].disabled=true;
	        }
	    } else { 
	    	//다중
	    	//alert(itemchk.length);
	        for (i=0; i<itemchk.length; i++)
	        {
	            if (itemchk[i].checked==false)
	            {
	            	name[i].disabled=true;
	            	amount[i].disabled=true;
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