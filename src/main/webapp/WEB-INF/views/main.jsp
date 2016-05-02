<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/main.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="resources/js/urlCheck.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script>
	function urlCheck() {
		var loca = window.location + "";
		var locaSplit = new Array();
		locaSplit = loca.split('/');
	}
	var dateArraytype = new Array();
	var moneyArraytype = new Array();

	var dateArraytype1 = new Array();
	var moneyArraytype1 = new Array();
	var dateArraytype2 = new Array();
	var moneyArraytype2 = new Array();
	var dateArraytype3 = new Array();
	var moneyArraytype3 = new Array();
	var dateArraytype4 = new Array();
	var moneyArraytype4 = new Array();
	var dateArraytype5 = new Array();
	var moneyArraytype5 = new Array();

	var arrayLength = new Array();
	<c:forEach var="saved" items="${requestScope.saved }">
	dateArraytype1.push("${saved.pay_date }");
	moneyArraytype1.push("${saved.pay_money }" * 1);
	</c:forEach>
	<c:forEach var="first" items="${requestScope.first }">
	dateArraytype2.push("${first.pay_date }");
	moneyArraytype2.push("${first.pay_money }" * 1);
	</c:forEach>
	<c:forEach var="cash" items="${requestScope.cash }">
	dateArraytype3.push("${cash.pay_date }");
	moneyArraytype3.push("${cash.pay_money }" * 1);
	</c:forEach>
	<c:forEach var="card" items="${requestScope.card }">
	dateArraytype4.push("${card.pay_date }");
	moneyArraytype4.push("${card.pay_money }" * 1);
	</c:forEach>
	<c:forEach var="charge" items="${requestScope.charge }">
	dateArraytype5.push("${charge.pay_date }");
	moneyArraytype5.push("${charge.pay_money }" * 1);
	</c:forEach>

	dateArraytype[1] = dateArraytype1;
	dateArraytype[2] = dateArraytype2;
	dateArraytype[3] = dateArraytype3;
	dateArraytype[4] = dateArraytype4;
	dateArraytype[5] = dateArraytype5;

	arrayLength.push(dateArraytype1.length);
	arrayLength.push(dateArraytype2.length);
	arrayLength.push(dateArraytype3.length);
	arrayLength.push(dateArraytype4.length);
	arrayLength.push(dateArraytype5.length);

	//배열이 제일 긴 타입 리턴
	var big;
	for (i = 1; i < 5; i++) {
		if (arrayLength[i - 1] < arrayLength[i]) {
			big = i;
		}
	}
	//배열이 제일 긴 타입 리턴
	$(function(e) {
		urlCheck();
		if ("${sessionScope.user}" == "") {
			window.location.href = "login.do";
		}
		$('.graph').highcharts({
			title : {
				text : '티아라 네일 장부',
				x : -20
			//center
			},
			subtitle : {
				text : '',
				x : -20
			},
			xAxis : {
				categories : dateArraytype[big + 1]
			},
			yAxis : {
				title : {
					text : '금액 (원)'
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},
			tooltip : {
				valueSuffix : '원'
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'middle',
				borderWidth : 0
			},
			series : [ {
				name : '적립금 결제',
				data : moneyArraytype1
			}, {
				name : '선입금 결제',
				data : moneyArraytype2
			}, {
				name : '현금 결제',
				data : moneyArraytype3
			}, {
				name : '카드 결제',
				data : moneyArraytype4
			}, {
				name : '선입금 충전내역',
				data : moneyArraytype5
			} ]
		});

		var total = new Array();
		total[0] = 0;
		total[1] = 0;
		total[2] = 0;
		total[3] = 0;
		total[4] = 0;
		for (i = 0; i < moneyArraytype1.length; i++) {
			total[0] += moneyArraytype1[i] * 1;
		}
		for (i = 0; i < moneyArraytype2.length; i++) {
			total[1] += moneyArraytype2[i] * 1;
		}
		for (i = 0; i < moneyArraytype3.length; i++) {
			total[2] += moneyArraytype3[i] * 1;
		}
		for (i = 0; i < moneyArraytype4.length; i++) {
			total[3] += moneyArraytype4[i] * 1;
		}
		for (i = 0; i < moneyArraytype5.length; i++) {
			total[4] += moneyArraytype5[i] * 1;
		}
		for (i = 0; i < 5; i++) {
			$("#total" + (i + 1)).html(total[i] + "원");
		}
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
		<div class="sideNav">
			<ul>
				<li><a href="home.tiara"><img
						src="resources/images/home/homeBtn.png"></a></li>
				<li><a href="list.tiara"><img
						src="resources/images/home/memberBtn.png"></a></li>
				<li><a href="noticeView.tiara"><img
						src="resources/images/home/noticeBtn.png"></a></li>
				<li><a href="push.do"><img
						src="resources/images/home/pushBtn.png"></a></li>
				<li><a href="goGalleryAdd.tiara"><img
						src="resources/images/home/galleryBtn.png"></a></li>
			</ul>
		</div>
		<div class="content">
			<div class="graph"></div>
			<div class="graphResult">
				<table cellspacing="0">
					<tr>
						<th colspan="2" style="text-align: center">기간 총액
						</td>
					</tr>
					<tr>
						<th>총 적립금 결제금액
						</td>
						<td id="total1"></td>
					</tr>
					<tr>
						<th>총 선입금 결제금액
						</td>
						<td id="total2"></td>
					</tr>
					<tr>
						<th>총 현금 결제금액
						</td>
						<td id="total3"></td>
					</tr>
					<tr>
						<th>총 카드 결제금액
						</td>
						<td id="total4"></td>
					</tr>
					<tr>
						<th>총 선입금 충전금액
						</td>
						<td id="total5"></td>
					</tr>
				</table>
				<table cellspacing="0">
					<tr>
						<th colspan="3" style="text-align: center">생일자</th>
					</tr>
					<tr>
						<td style="text-align: center; border-bottom: 2px double #878788;">이름</td>
						<td style="text-align: center; border-bottom: 2px double #878788;">ID</td>
						<td style="text-align: center; border-bottom: 2px double #878788;">전화번호</td>
					</tr>
					<c:forEach var="birth_account"
						items="${requestScope.birth_account }">
						<tr>
							<td>${birth_account.name}</td>
							<td>${birth_account.id}</td>
							<td>${birth_account.phone}</td>
					</c:forEach>
					<tr>
						<td colspan="3" style="text-align: center"><form
								action="birth_push.tiara">
								<input type=submit
									style="border-radius: 10px 10px; width: 40%; height: 40px; background: #1a75bc; color: #fff; cursor: pointer; font-size: 13px; border: 0;"
									value="생일축하 메세지 보내기">
							</form></td>
					</tr>
				</table>

			</div>
		</div>
	</div>
</body>
</html>
