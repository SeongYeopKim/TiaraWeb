<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="UploadNotice.tiara" method="post" >
		<fieldset>
			<table>
					<th>제목</th>
					<td><input type="text" name="title" required="required" placeholder="제목"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="20" cols="40" name="content" required="required" placeholder="내용"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="작성">
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>

</body>
</html>