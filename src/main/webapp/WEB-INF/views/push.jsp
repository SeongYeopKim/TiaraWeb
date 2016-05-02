<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/push.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="resources/js/urlCheck.js"></script>
<script>
	function getByteLength(s) {
		var b, i, c;
		for (b = i = 0; c = s.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2
				: 1)
			;
		return b;
	}

	function radioChecked() {
		$(".btn").change(function(e) {
			switch ($(".btn:checked").val() * 1) {
			case 1:
				$(".phoneTitle").html("전체전송 (문자메세지 + 푸시알림)");
				$("#opt").attr("value", "all");
				break;
			case 2:
				$(".phoneTitle").html("문자메세지만 전송");
				$("#opt").attr("value", "sms");
				break;
			case 3:
				$(".phoneTitle").html("푸시알림으로만 전송");
				$("#opt").attr("value", "push");
				break;
			default:
				break;
			}
		})
	}
	$(function(e) {
		if ("${sessionScope.user}" == "") {
			window.location.href = "login.do";
		}
		var flag = 0;
		var beforeBody = "";
		radioChecked();
		$(".msgBody").keydown(function(e) {
			var thisLength = getByteLength($(this).val());
			$(".byteView").html(thisLength + "/120");
			if (flag == 0 && thisLength > 120) {
				alert("40자가 초과하여 메세지를 보낼수 없습니다.");
				$(this).val(beforeBody);
				flag = 1;
			} else if (thisLength < 120) {
				flag = 0;
			}
			beforeBody = $(this).val();
		}).click(function(e) {
			if ($(this).val() == "내용을 입력해 주세요") {
				$(this).val("");
			}
			;
		});

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
			<div class="title">메세지 발송</div>

			<div class="groupWrap">
				<label for="sendAll"><input type="radio" name="radioGroup"
					id="sendAll" value="1" checked="checked" class="btn">전체전송(문자+푸시알림)</label>
				<label for="sendMessege"><input type="radio"
					name="radioGroup" id="sendMessege" value="2" class="btn">문자메세지</label>
				<label for="sendPush"><input type="radio" name="radioGroup"
					id="sendPush" value="3" class="btn">푸시알림</label>
			</div>
			<form action="message.tiara" method="post">
				<input type="hidden" name="title" value="티아라에서 알립니다."> <input
					type="hidden" name="opt" value="all" id="opt">
				<div class="phone">
					<div class="phoneTitle">전체전송 (문자메세지 + 푸시알림)</div>
					<textarea name="msg" class="phoneText msgBody">내용을 입력해 주세요</textarea>
					<input type="submit" value="" class="sendBtn">
					<!-- <input type="button" value="asdfasdf" class="sendBtn"> -->
					<div class="byteView">0/120</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
