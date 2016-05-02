<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
</head>
<body>
	<script type="text/javascript">
		alert("${remainSms}");
		window.location.href = "push.do";
	</script>
</body>
</html>
