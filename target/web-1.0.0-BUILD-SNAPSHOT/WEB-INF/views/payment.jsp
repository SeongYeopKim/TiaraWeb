<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(e) {
		var messege = new Array();
		var messegeNum;
		
		messege[0] = "id가 존재하지 않습니다.";
		messege[1] = "금액을 입력하세요";
		messege[2] = "금액을 정확히 입력해주세요";
		messege[3] = "적립금 금액을 입력하세요.";
		messege[4] = "체크박스를 확인하세요.";
		messege[5] = "고객이 아닌분은 적립금이 없습니다.";
		messege[6] = "적립금이 부족합니다.";
		messege[7] = "추가 결제할 방법을 선택하세요";
		messege[8] = "선입금은 아이디가 필요합니다.";
		messege[9] = "선입금한 금액이 없거나 부족합니다."

		if("${message}".length!=1){
			messegeNum = "${message}";
			alert(messegeNum);
			window.close();
			window.opener.prentLoad();
			window.opener.close();
		}else{
			messegeNum = "${message}"*1;
			
			alert(messege[messegeNum]);
			window.close();	
		}
		

		
		
	})
</script>
</head>
<body>
</body>
</html>