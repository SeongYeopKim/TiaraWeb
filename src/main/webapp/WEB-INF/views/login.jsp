<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<link href="resources/css/login.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery.js">
	
</script>
</head>
<body>
	<div class="wrap">
		<div class="head">
			<div class="logo">
				<img src="resources/images/home/logo.png">
			</div>
			<div class="loginTitle">
				<img src="resources/images/login/loginTitle.png">
			</div>
		</div>
		<div class="content">
			<div class="loginBox">
				<form action="firstlogin.tiara" method="post">
					<table border="0" cellspacing="10">
						<tr>
							<td><img src="resources/images/login/idText.png"></td>
							<td><input type="text" name="id" class="inputId"></td>
							<td rowspan="2"><input type="submit" class="loginBtn"
								value=""></td>
						</tr>
						<tr>
							<td><img src="resources/images/login/passText.png"></td>
							<td><input type="password" name="pw" class="inputPass">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</div>
</body>
</html>
