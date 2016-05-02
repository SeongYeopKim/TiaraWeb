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

			<th>글번호 </th>

			<th>제목 </th>
			<th>내용 </th>
			<th>날짜 </th>
			
		</tr>
	<c:forEach var="noticeList" items="${requestScope.noticeList }">

		<tr>

			<td>${noticeList.notice_num }</td>
			<td>${noticeList.title }</td>
			<td>${noticeList.content }</td>
			<td>${noticeList.date }</td>


			<td></td>

		</tr>

	</c:forEach>
	</table>
	
	글삭제 (글번호입력) 
	<form action="noticeDelete.tiara" >
	<input name = "num">
	<input type = "submit">
	
	</form>
	
</body>
</html>