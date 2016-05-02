<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.android.gcm.server.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	boolean SHOW_ON_IDLE = false;    //기기가 활성화 상태일때 보여줄것인지
	int LIVE_TIME = 1800;  //이건 시간이 아니고 초를 의미..
	int RETRY = 3;  //메시지 전송실패시 재시도 횟수
	String title = "타아를";
	String photo = "http://ncc.phinf.naver.net/20140224_275/13932310408233kJzp_JPEG/a01.jpg";
	String contents = "테스팅중11";
	String link = "<a href='http://ncc.phinf.naver.net/20140224_275/13932310408233kJzp_JPEG/a01.jpg?type=w646'>이미지 보기</a>";
	String msg = "테스트 메시지";
	
	String regid="APA91bFPBbv7x8Po716cxfLVpECnZ-sIDASkquqGNKfemYD1-1zmT6eYydOdtt5Iw1UANxfhG8CPODH3HSNbhbn_9dpQ1zNkcqLni1kfJajjb9YabfssjZqiMRIt9yb0mn5t-uamlMMyiFSRw95bR1Tu56nG9H92xg";
	String regid2="APA91bGMX6dN_xD8eBFPheCxKH_nNSExTCMJSHP0_OBVCkewoqzo_BQNpl9QsxQpzpD2YdrZlVCjt6pZDjXugsmIo2VmrNfzZsNMyXDWHw8stLeCJ6urtNbju38jv8J0aJJEIGXYZUE8";
	String regid3="APA91bGHqFHWXDjTooV75gIrE5nBMOaKVdK2DPY9CEGvLUJ49ZGMmoWtEr3ZduF2xWXeUion9Xt-vzyYXiCSn1uTD64AHCj-lsaqbhFPOO7MKmAVDhESfYOaf1poDFg6eldClvqPZsVqbV8lIEs8rBMPXXVT7DayxQ";
       
       
	 Sender sender = new Sender("AIzaSyAvU8zGH13cg1hTnnRoqgi5yPSjRTFnl4s");
       //Sender sender = new Sender("AIzaSyDZT9Yxlp9VQzzliSkrKvSAAb2W0UsJ9Uo");
	 Message message = new Message.Builder()
	 .delayWhileIdle(SHOW_ON_IDLE)
	 .timeToLive(LIVE_TIME)
	 .addData("title", title)
	 .addData("photo", photo)
	 .addData("contents", contents)
	 .addData("link", link)
	 .addData("msg", msg).build();

	 /* 
	 Message message = new Message.Builder()
		                    //.collapseKey("collapseKey" + System.currentTimeMillis()) //주석막음
		                    .delayWhileIdle(SHOW_ON_IDLE)
		                    .timeToLive(LIVE_TIME)
		                    .addData("test",msg)
		                    .build();  */
		                    Result result  = sender.send(message, regid, RETRY);
		                    Result result2  = sender.send(message, regid2, RETRY);
		                    Result result3  = sender.send(message, regid3, RETRY);
		
	%>
</body>
</html>

