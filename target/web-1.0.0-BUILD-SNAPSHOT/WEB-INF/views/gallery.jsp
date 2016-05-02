<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/gallery.css" rel="stylesheet">
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
		var loca = window.location + "";
		var locaSplit = new Array();
		locaSplit = loca.split('/');

		var locaSplitLength = locaSplit.length;
		if (locaSplit[locaSplitLength - 1] == "goGalleryAdd.tiara") {
			tabControll(1);
		} else {
			tabControll(2);
		}
	}

	$(function(e) {
		urlCheck();
		if ("${sessionScope.user}" == "") {
			window.location.href = "login.do";
		}
		$(".tabBtn").click(function(e) {
			if ($(this).index() == 0) {
				window.location.href = "goGalleryAdd.tiara";
			} else {
				window.location.href = "youtube.tiara";
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
			<table class="wrapTable" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="tabBtn">갤러리</td>
					<td class="tabBtn">셀프네일</td>
				</tr>
				<tr>
					<td colspan="2" class="tabTd">
						<div class="tabContent1">

							<table>
								<tr>
									<td>번호</td>
									<td>제목</td>
									<td>내용</td>
									<td>이미지</td>
								</tr>
								<tr>
								<form action="addGallery.tiara" method="post" enctype="multipart/form-data">
									<td>추가</td>
									<td><input type="text" name="gTitle"></td>
									<td><textarea name="gBody"></textarea></td>
									<td><input type="file" name="file"></td>
									<td><input type="submit" value="전송"></td>
									</form>
								</tr>
								<c:forEach items="${list}" var="list">
									<tr>
										<td>${list.gNum}</td>
										<td>${list.gTitle}</td>
										<td>${list.gBody}</td>
										<td><img src="${list.gThumbNail}"></td>
										<td>
											<form action="galleryRemove.tiara">
												<input type="hidden" name="gNum" value="${list.gNum}">
												<input type="submit" value="삭제">
											</form>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="tabContent2">
							<table>
								<tr>
									<td>번호</td>
									<td>제목</td>
									<td>링크주소</td>
									<td>삭제</td>
								</tr>
								<form action="addVideo.tiara" method="post">
									<tr>
										<td>추가</td>
										<td><input type="text" name="video_title"></td>
										<td><input type="text" name="video_url"></td>
										<td><input type="submit" value="작성"></td>
									</tr>
								</form>
								<c:forEach var="video" items="${requestScope.video }">
									<tr>
										<td>${video.video_num }</td>
										<td>${video.video_title }</td>
										<td>${video.video_url }</td>
										<td><form action="videoRemove.tiara" method="get"
												class="formText">
												<input type="hidden" name="video_num"
													value="${video.video_num }"> <input type="submit"
													value="삭제">
											</form></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
