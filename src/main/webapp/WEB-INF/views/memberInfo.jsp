<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/memberInfo.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js">
	
</script>
<script>
		$(document).ready(function(e){
			if("${sessionScope.user}"==""){
				window.location.href = "login.do";
			}
				$("#type").val("<%=request.getParameter("type")%>")
								.attr("selected", "selected");

						$("#type").change(
								function(E) {
									var tloca = window.location + "";
									var lengthloca = tloca.length - 1;
									tloca = tloca.substring(0, lengthloca)
											+ $(this).val();
									window.location.href = tloca;
								});

						$(".goChargeBtn")
								.click(
										function(e) {
											window
													.open(
															"goInsertMoney.tiara?id=${account.id}",
															"금액 충전",
															"scrollbars=no,toolbar=no,resizable=yes,width=1000,height=800,left=100,top=100");
										})

					});
		function create(){
			win = open("",'w','width=1000,height=800');
		}
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
			<table class="contentWrapTable">
				<tr>
					<td>
						<table class="memberInfoTable" cellspacing="20">
							<tr>
								<td>아이디</td>
								<td>${account.id }</td>
							</tr>
							<tr>
								<td>이름</td>
								<td>${account.name }</td>
							</tr>
							<tr>
								<td>생일</td>
								<td>${account.birth }</td>
							</tr>
							<tr>
								<td>전화번호</td>
								<td>${account.phone }</td>
							</tr>
						</table>
					</td>
					<td>
						<div class="moneyWrap">
							<ul class="moneyTitle">
								<li>적립금</li>
								<li>선입금</li>
							</ul>
							<ul class="money">
								<li>${saved_money }</li>
								<li>${first_money }</li>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table class="list" cellspacing="0">
							<tr class="listTitle">
								<td>날짜</td>
								<td>이용내역</td>
								<td><select name="type" id="type">
										<option value="0">전체</option>
										<option value="1">적립금</option>
										<option value="2">선입금</option>
										<option value="3">현금</option>
										<option value="4">카드</option>
										<option value="5">충전내역</option>
								</select></td>
							</tr>
							<c:forEach var="payList" items="${requestScope.payList }">

								<tr>

									<td>${payList.pay_date }</td>

									<td >${payList.service }</td>
									<td >${payList.pay_money }</td>							
								</tr>

							</c:forEach>
							<tr>
								<td colspan="3" class="ttt" style="text-align: center; padding: 10px 0;">
									
									<form action="goPayment.tiara" method="post" target="w" onsubmit="create()">
									<input type="button" value="금액 충전" class="goChargeBtn">
										<input type="hidden" name="saved_money" value="${saved_money }">
										<input type="hidden" name="first_money" value="${first_money }">
										<input type="hidden" name="id" value="${account.id}">
										<input type="submit" value="결제 하기" class="goPayBtn">
									</form>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

	</div>
</body>
</html>
