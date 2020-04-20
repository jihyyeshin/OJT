<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp" %></head>
 <link href="<c:url value="/resources/css/item.css" />" rel="stylesheet">
  
</head>
<body>
<input type="text" id="search" placeholder="Type to search">
	<form class="list-group" name="paging" onsubmit="_submit();" method="post">
		<input type="hidden" name="memberid" value="123">
		<input type="hidden" name="agent" value="386445">
		<input type="hidden" name="item" value="">
		
		<table id="table">
			<tr>
				<td style="text-align: center;">
				<input type="checkbox" name="itemchk" value="103872">
				</td>
				<td>
				<a id="title" href="javascript:goDetail(103872);">핫도그375G</a>
				</td>
			</tr>
			<tr style="border-bottom:1px solid #d4d4d4;">
				<td>
				</td>
				<td style="width: 65%;">3020원</td>
				<td style="width: 25%;">
				<input type="text" name="qty" style="width: 50%;">개</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
var $rows = $('#table tr');
$('#search').keyup(function() {
    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
    
    $rows.show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});
</script>
</body>
</html>