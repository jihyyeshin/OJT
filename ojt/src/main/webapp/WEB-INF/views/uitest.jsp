<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %></head>
 <link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
  <style type="text/css">
        .con_bb {width:100%; height:50%; margin:0 auto; padding-top:10%; overflow: hidden; position:relative; }
        .left {width:50px; left:5px; margin-top:25px; opacity: 0.7;position:absolute;}
        .right {width:50px; right:5px; margin-top:25px; opacity: 0.7;z-index:9999;position:absolute;}
        .rolling_panel {float:left;  position: relative; width: 100%; height: 50%; margin: 0; padding: 0; overflow: hidden; }
        .rolling_panel ul { position: absolute; margin: 5px; padding: 0; list-style: none; }
        .rolling_panel ul li { float: left; width: 360px; height: 320px;z-index:9999;}
        </style>
</head>
<body>
        <div class="con_bb">
          <div class="left"><a href="javascript:void(0)" id="prev"><img src="<c:url value="/resources/img/left.png" />" width="50px" height="50px"></a></div>
          <div class="rolling_panel">
              <ul>
                  <li><img src="<c:url value="/resources/img/CJ_logo.png" />" alt="a" width="100" height="88"></li>
                  <li><img src="<c:url value="/resources/img/back.png" />" alt="b" width="100" height="88"></li>
                  <li><img src="<c:url value="/resources/img/cart.png" />" alt="c" width="100" height="88"></li>
                  <li><img src="<c:url value="/resources/img/CJ_logo.png" />" alt="d" width="100" height="88"></li>
                  <li><img src="<c:url value="/resources/img/cart.png" />" alt="e" width="100" height="88"></li>
              </ul>
          </div>
          <div class="right"><a href="javascript:void(0)" id="next"><img src="<c:url value="/resources/img/right.png" />" width="50px" height="50px"></a></div>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                var $panel = $(".rolling_panel").find("ul");
                var itemWidth = $panel.children().outerWidth(); // 아이템 가로 길이
                
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
        </script>




</body>
</html>