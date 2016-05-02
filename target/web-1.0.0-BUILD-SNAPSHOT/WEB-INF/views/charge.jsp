<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/charge.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js">
	
</script>
<script>
var money = 0;


		$(document).ready(function(e){
			if("${sessionScope.user}"==""){
				window.location.href = "login.do";
			}
				$("#type").val("<%=request.getParameter("type")%>").attr("selected", "selected");

				$("#type").change(function(E) {
					var tloca = window.location + "";
					var lengthloca = tloca.length - 1;
					tloca = tloca.substring(0, lengthloca) + $(this).val();
					window.location.href = tloca;
				});

				$("#plus").click(function(e) {
					money += 100000;
					$("#money").val(money);
					
					
					var moneyString = money + "";
					if (money < 1000000) {
						moneyString = moneyString.substring(0, 2);
					} else {
						moneyString = moneyString.substring(0, 3);
					}
					$(".total_money").html(moneyString + "만원");

				})
				$("#minus").click(function(e) {
					if (money > 0) {
						
						money -= 100000;
						moneyString = money + "";
						
						$("#money").val(money);
						
						if (money < 1000000) {
							moneyString = moneyString.substring(0, 2);
						} else {
							moneyString = moneyString.substring(0, 3);
						}
						$(".total_money").html(moneyString + "만원");
					}
				})
			});
</script>
</head>
<body>
	<div class="wrap">
		<div class="head">
			<div class="logo">
				<a href="home.tiara"><img src="resources/images/home/logo.png"></a>
			</div>
		</div>
		<div class="content">
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td><input type="button" value="+" id="plus"></td>
					<td class="total_money">0 원</td>
					<td><input type="button" value="-" id="minus"></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center; padding: 10px 0;">
						<form action="insert.tiara" method="post">
							<input type="hidden" name="id"
								value="<%=request.getParameter("id")%>"> <input
								type="hidden" name="total_money" value="0" id="money"> <input
								type="submit" value="충전" class="sub">
						</form>
					</td>
				</tr>
			</table>
		</div>

	</div>
</body>
</html>
