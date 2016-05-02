<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="showFirstPayList.tiara" method="post">

		id : <input name="id"><br> <input type="submit"
			value="확인 ">
	</form>

	<table border="1" width="600">

		<tr>

			<th>id</th>
			<th>날짜</th>
			<th>선결제총금액</th>
			<th>남은금액</th>
			<th>할인비율</th>
		</tr>

		<c:forEach var="payList" items="${requestScope.payList }">

			<tr>

				<td>${payList.id}</td>
				<td>${payList.fp_date}</td>
				<td>${payList.total_money}</td>
				<td>${payList.left_money }</td>
				<td>${payList.fp_rate*100 }</td>


			</tr>

		</c:forEach>



	</table>




</body>
</html>