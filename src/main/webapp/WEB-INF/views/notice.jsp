<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/notice.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="resources/js/urlCheck.js"></script>
<script>
	function tabControll(tab) {
		if (tab == 1) {
			$(".tabContent1").css("display", "block");
			$(".tabContent2").css("display", "none");
			$(".tabBtn:eq(0)").css("color", "#0e76bc");
		} else {
			$(".tabContent1").css("display", "none");
			$(".tabContent2").css("display", "block");
			$(".tabBtn:eq(1)").css("color", "#0e76bc");
		}

	}

	function urlCheck() {
		if("${sessionScope.user}"==""){
			window.location.href = "login.do";
		}
		var loca = window.location + "";
		var locaSplit = new Array();
		locaSplit = loca.split('/');

		var locaSplitLength = locaSplit.length;
		if (locaSplit[locaSplitLength - 1] == "noticeView.tiara") {
			tabControll(1);
		} else {
			tabControll(2);
		}
	}

	$(function(e) {
		urlCheck();

		$(".tabBtn").click(function(e) {
			var tindex = $(this).index() + 1;
			if (tindex == 1) {
				window.location.href = "noticeView.tiara";
			} else {
				window.location.href = "GoUploadNotice.tiara";
			}
		});

		$("#idcheck")
				.click(
						function(e) {

							var thisID = $("#id").val();
							window
									.open("nickNameCheck.tiara?id=" + thisID,
											'아이디 중복체크',
											'scrollbars=no,toolbar=no,resizable=no,width=300,height=200,left=0,top=0');
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
			</ul>
		</div>
		<div class="content">
			<table class="wrapTable" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="tabBtn">공지사항</td>
					<td class="tabBtn">공지사항 입력</td>
				</tr>
				<tr>
					<td colspan="2" class="tabTd">
						<div class="tabContent1">
							<table cellspacing="0">

								<tr class="tab1Title">

									<th>글번호</th>

									<th>제목</th>
									<th>내용</th>
									<th colspan="2">날짜</th>
								</tr>
								<c:forEach var="noticeList" items="${requestScope.noticeList }">

									<tr>

										<td>${noticeList.notice_num }</td>
										<td>${noticeList.title }</td>
										<td>${noticeList.content }</td>
										<td>${noticeList.date }</td>
										<td style="text-align: center"><form
												action="noticeDelete.tiara">
												<input name="num" type="hidden"
													value="${noticeList.notice_num }"> <input
													value="삭제" type="submit">

											</form></td>


									</tr>

								</c:forEach>
							</table>

						</div>
						<div class="tabContent2">
							<form action="UploadNotice.tiara" method="post">
								<table>
									<tr>
										<th>제목</th>
										<td><input type="text" name="title" required="required"
											placeholder="제목" style="width: 100%"></td>
									</tr>
									<tr>
										<th>내용</th>
										<td><textarea rows="20" cols="40" name="content"
												required="required" placeholder="내용" style="width: 100%"></textarea></td>
									</tr>
									<tr>
										<td colspan="2" style="text-align: center"><input
											type="submit" value="작성" class="btn"> <input
											type="reset" value="취소" class="btn"></td>
									</tr>
								</table>
							</form>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
