<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type= "text/javascript" src= "http://code.jquery.com/jquery.js"> </script>
	<script>
		$(document).ready(function(e){
			$("#aa").click(function(){
				window.location.href = "ShowNotice.tiara"
			});
			$("#cc").click(function(){
				window.location.href = "noticeView.tiara"
			});
			
			$("#bb").click(function(){
				window.location.href = "GoUploadNotice.tiara"
			});
		
			
		});
	</script>
</head>

<body>

<button id ="aa">공지사항 json으로 보기</button>
<button id ="cc">공지사항 param으로 보기</button>

<button id ="bb">공지사항입력 </button>
</body>
</html>