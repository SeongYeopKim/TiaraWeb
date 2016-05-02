<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<script type= "text/javascript" src= "http://code.jquery.com/jquery.js"> </script>
	<script>
		$(document).ready(function(e){
			
			$("#aa").click(function(){
				window.location.href = "goInsertMoney.tiara"
			});
			$("#bb").click(function(){
				window.location.href = "goShowFirstPayList.tiara"
			});
			$("#cc").click(function(){
				window.location.href = "goPayment.tiara"
			});
			$("#dd").click(function(){
			});
			
		});
	</script>
</head>
<body>

<button id ="aa">돈넣기페이지</button>
<button id ="bb">선입금리스트출 </button>
<button id ="cc">결제하기 </button>
<button id ="dd">----</button>

</body>
</html>