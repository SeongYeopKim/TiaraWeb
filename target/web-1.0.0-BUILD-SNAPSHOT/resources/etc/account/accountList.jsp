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
				window.location.href = "menu.tiara"
			});
			$("#bb").click(function(){
				window.location.href = "goUseMoney.tiara"
			});
	
			
		});
	</script>

</head>
<body>

<form action="listJson.tiara" method="post">
name : <input name="name" type="text"><br>
name : <input name="fadd" type="text"><br>
<input type="submit" value="전송">
</form>


<button id ="aa">메뉴로 </button>

${myList[0].name}
${myList[0].phone}
${myList[0].birth}
${myList[0].nic_name}
${myList[0].id}


<button id ="bb">결제하기  </button>

 

</body>
</html>