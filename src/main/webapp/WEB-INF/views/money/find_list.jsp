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

${birth_account}

	<table border="1" width="600">

		<tr>

			<th>아이디</th>

			<th>비밀번호</th>
		</tr>

		<c:forEach var="test" items="${requestScope.test }">

			<tr>

				<td>${test.pay_id }</td>

				<td>${test.left_money }</td>

				<td></td>

			</tr>

		</c:forEach>
		</table>
</body>
</html>