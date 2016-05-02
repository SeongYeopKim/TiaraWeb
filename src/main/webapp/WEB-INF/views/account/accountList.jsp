<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript" src="http://code.jquery.com/jquery.js">
	
</script>
<script>
	$(document).ready(function(e) {

		$("#aa").click(function() {
			window.location.href = "menu.tiara"
		});
		$("#bb").click(function() {
			window.location.href = "goUseMoney.tiara"
		});

	});
</script>

</head>
<body>

	<form action="listJson.tiara" method="post">
		<select name="type">
			<option >id</option>
			<option >name</option>
			<option >phone</option>
			<option>all</option>
		</select> name : <input name="value" type="text"> 
		<input type="submit" value="전송">
	</form>

	<table border="1" width="600">

		<tr>

			<th>아이디</th>

			<th>비밀번호</th>
		</tr>

		<c:forEach var="account" items="${requestScope.account }">

			<tr>

				<td>${account.id }</td>

				<td>${account.name }</td>

				<td></td>

			</tr>

		</c:forEach>



	</table>


</body>
</html>