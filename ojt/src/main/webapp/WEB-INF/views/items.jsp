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
		<input type="hidden" name="saleDiv" value="sale" />
		<input type="hidden" name="item" value=""/>
		<input type="hidden" name="itemDiv" value=""/><!-- 아이템 디테일 화면과 구분 -->
		<div class="item-list">
			<div class="recommend-list">
				<h4 style="text-align:center;">${param.memberid}님을 위한 추천 상품</h4>
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prevR"> <img
							src="<c:url value="/resources/img/left.png" />" width="50px"
							height="50px">
						</a>
					</div>
					<div class="rolling_panel" id="rollR">
						<ul id="recList">
						</ul>
					</div>
					<div class="rightarrow">
						<a href="javascript:void(0)" id="nextR"> <img
							src="<c:url value="/resources/img/right.png" />" width="50px"
							height="50px">
						</a>
					</div>
				</div>
			</div>
			
			<div class="lvl-list">
				<h4 style="text-align:center;color:#FF7272;">이런 상품은 어떤가요?</h4>
				<div class="con_bb">
					<div class="leftarrow">
						<a href="javascript:void(0)" id="prevL"> <img
							src="<c:url value="/resources/img/left.png" />" width="50px"
							height="50px">
						</a>
					</div>
					<div class="rolling_panel" id="rollL">
						<ul id="lvlList">
						</ul>
					</div>
					<div class="rightarrow">
						<a href="javascript:void(0)" id="nextL"> <img
							src="<c:url value="/resources/img/right.png" />" width="50px"
							height="50px">
						</a>
					</div>
				</div>
			</div>
			
			<div id="itemList" style="position:relative;top:20px;bottom:10px;"></div>
			<button type="button" onclick="pagingItem()" class="btn" style="width:100%;border-radius: 0px;">더보기</button>
		</div> 
		
		<button id="footerL" type="submit" onclick="javascipt: form.action='./items/saleCheck'">주문하기</button>
		<button id="footerR" type="submit" onclick="javascipt: form.action='./items/insertBasket'">장바구니 넣기</button>
	</form>
	
	<script type="text/javascript">
	var agentF=${param.agentF};
	var agentA=${param.agentA};
	var memberId=${param.memberid};
	
	var page=1;
/********************************************************** ready Func **********************************************************/

	// 로드되자마자
	$(document).ready(function(){
		/* 상품 조회 */ 
		// 상품 일부 조회(더보기 버튼 사용)
		showItem(page++);
		search();
		// 추천 리스트
		showRecommendedItems();
		// 상품군 리스트
		showLvlItems();
		
		/* 상품 슬라이드 */
		slide('R');
		slide('L');
	});
	
/********************************************************** 상품 조회 **********************************************************/
	// 대리점 별 아이템 및 가격 정보 조회
	function showItem(page){
		if(agentF!=null && agentA!=null){
			$.ajax({
				url:"./showItem",
				type:"POST",
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				async:false,
				data:"agentF="+agentF+"&agentA="+agentA+"&page="+page,
				success: function(returnData){
					console.log("success");
					var data=returnData.list;
					
					if(page==1){
						// html 비우기
						$('#itemList').html("");
					}
					if(returnData.startNum <= returnData.totCnt){
						if(data.length>0){//데이터가 있을 경우
							print(data);
						}else{
							alert('더 이상 데이터가 없습니다.');
						}
					}
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
					printRecItem('rec', data);
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
	
	// 베스트 상품의 동일 상품군 조회
	function showLvlItems(){
		if(agentF!=null && agentA!=null &&memberId!=null){
			$.ajax({
				url:"./showLvlItems",
				type:"POST",
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				async:false,
				data:"agentF="+agentF+"&agentA"+agentA+"&memberid="+memberId,
				success: function(data){
					console.log("success");
					printRecItem('lvl', data);
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
	
/********************************************************** 출력 Func **********************************************************/
	// 아이템 정보 출력
	function print(data){
		$.each(data, function(index, item){
			var src=item.src;
			if(src == "") src="<c:url value="/resources/img/CJ_logo_black.png" />";
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
			str+=	'<td style="font-family: CJBOLD;color:#FF7272;">' + amounts + '원</td>';
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
	// 추천 아이템 2가지 출력
	function printRecItem(value, data){
		$.each(data, function(index, item){
			var src=item.src;
			if(src == "") src="<c:url value="/resources/img/CJ_logo_black.png" />";
			
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
			/* str += '<input type="hidden" name="itemchk" value="'+item.item+'">'; */
			str += '</tr>';
			str += '</table>';
			str += '</li>';
			
			$('#'+value+'List').append(str);
		});
	}
	
	
/********************************************************** 상품 검색 & 디테일  ********************************************************/
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
		// paging form 안에 있는 value에 값을 할당하여 보내야 함.
		// 이 때, f.item.value가 여러군데 있다면, 하나만 보내지는 것이 아니라, list로 보내짐
		var f=document.paging;
		f.item.value=item;
		f.agent.value=agent;
		f.agentF.value=agentF;
		f.agentA.value=agentA;
		
		f.memberid.value=${param.memberid};

		f.action="./items/detail"
		f.method="post"
		f.submit();
	};
	
	
/********************************************************** Submit Func **********************************************************/
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
	        if (itemchk[0].checked==false)
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

/********************************************************** 기타 Func **********************************************************/
	// 페이징 처리
	function pagingItem(){
   		//console.log(page);
		showItem(page);
		page++;
	}
	/* $(window).scroll(function(){   //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
		alert("hi scroll");
	     if($(window).scrollTop() >= $(document).height() - $(window).height()){
	    	 console.log("??");
	    	 showItem(page);
	         page++;   
	     } 
	});
	 */

	// 가격 포맷 생성
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	 
	function slide(val){
		var $panel = $("#roll"+val).find("ul");
        var itemWidth = $panel.children().outerWidth();
        	
        // 이전 이벤트
        $("#prev"+val).on("click", prev);
        $("#prev"+val).mouseover();
        // 다음 이벤트
        $("#next"+val).on("click", next);
        $("#next"+val).mouseover();
            
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
	}

</script>
</body>
</html>