<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/pay.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js">
	
</script>
<script>
var money = 0;

function alertMessage(){
	var alertText = "금액을 총 "+$("#money").val()+"결제하며\n적립금은 총 "+$("#saved_money").val()+"사용하여 결제 하시겠습니까?";
	if(confirm(alertText)) 
	  { 
		window
		.open("",'check',
				'scrollbars=no,toolbar=no,resizable=no,width=100,height=100,left=0,top=0');
	  } 
}
function prentLoad(){
	opener.location.reload();
}
		$(document).ready(function(e){
				$("#type").val("<%=request.getParameter("type")%>").attr(
						"selected", "selected");
			});
		
	$(function(e){
		if("${sessionScope.user}"==""){
			window.location.href = "login.do";
		}
	})
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
			<form action="moneyPayment.tiara" target="check" method="post" id="payFrom" onsubmit="alertMessage()">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="2" style="text-align: center">${id }님의사용 가능 금액:</th>
					</tr>
					<tr>
						<th>적립금 :</th>
						<td> ${saved_money } 원</td>
					</tr>
					<tr>
						<th> 선입금 : </th>						
						<td>${first_money } 원</td>
					</tr>
					<tr>
						<th>결제방법 :</th>
						<td>선입금사용 <input type="checkbox" name="first_money">
							현금 <input type="checkbox" name="cash"> 카드 <input
							type="checkbox" name="card"> 적립금사용 <input type="checkbox"
							name="sm" id="saved"><br></td>
					</tr>
					<tr>
						<th>결제 금액 :</th>
						<td><input type="text" name="money" id="money"></td>
					</tr>
					<tr>
						<th>적립금 사용 :</th>
						<td><input type="text" name="saved_money" id="saved_money"></td>
					</tr>
					<tr>
						<th>사용 내역 :</th>
						<td><input type="text" name="service"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center; padding: 10px 0;">

							<input type="hidden" name="id" value="${id }"> <input
							type="submit" value="결 제" class="sub">

						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
</body>
</html>
