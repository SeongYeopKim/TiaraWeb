<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="600">

		<tr>

			<th>아이디</th>

			<th>비밀번호</th>
		</tr>
		<c:forEach var="payList" items="${requestScope.payList }">


			<tr>

				<td>${payList.pay_id }</td>

				<td>${payList.pay_money }</td>

				<td></td>

			</tr>

		</c:forEach>

</body>
</html>