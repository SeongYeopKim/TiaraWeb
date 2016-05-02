<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
.paging-button {
  visibility: hidden;
}

.button-container {
  clear: both;
}
</style>
</head>
<body>
  <div id="login-container" class="pre-auth">
      This application requires access to your YouTube account.
      Please <a href="#" id="login-link">authorize</a> to continue.
    </div>
    <div id="video-container"></div>
    <div class="button-container">
      <button id="prev-button" class="paging-button" onclick="previousPage();">Previous Page</button>
      <button id="next-button" class="paging-button" onclick="nextPage();">Next Page</button>
    </div>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/apikey.js"></script>
    <script type="text/javascript" src="resources/js/youtube.js"></script>
    <script src="https://apis.google.com/js/client.js?onload=googleApiClientReady"></script>
</body>
</html>