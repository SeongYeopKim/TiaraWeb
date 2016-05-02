<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/home.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js">
	
</script>
<script>
	$(document).ready(function(e) {

		$(".adminIcon").click(function() {
			window.location.href = "goMasterLogin.tiara"
		});
		$(".tabletIcon").click(function() {
			window.location.href = "goUpload.tiara"
		});
		$(".pushIcon").click(function() {
			window.location.href = "goPushPage.tiara"
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
		<div class="content">
			<ul>
				<li class="adminIcon"><img
					src="resources/images/home/adminIcon.png"></li>
				<li class="tabletIcon"><img
					src="resources/images/home/tabletIcon.png"></li>
				
			</ul>
		</div>


	</div>
</body>
</html>
