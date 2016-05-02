<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Home</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<script type= "text/javascript" src= "http://code.jquery.com/jquery.js"> </script>
	<script>
		$(document).ready(function(e){
			
			$("#aa").click(function(){
				window.location.href = "login.tiara"
			});
			
			$("#bb").click(function(){
				window.location.href = "list.tiara"
			});
			
			$("#cc").click(function(){
				window.location.href = "moneyMenu.tiara"
			});
			$("#dd").click(function(){
				window.location.href = "NoticeMenu.tiara"
			});
			
		});
	</script>
</head>

<body>


<button id ="aa">회원가입</button>
<button id ="bb">회원목록보기</button>
<button id ="cc">적립금관련</button>
<button id ="dd">공지사항</button>
<button id =""></button>

</body>
</html>