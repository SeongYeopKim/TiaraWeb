package kr.co.makeit.sms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * cafe24 제공 SMS 센더 자바
 * @since 2014. 10. 30.
 * @version 1.0
 * @author kimjinhyung
 * <pre>
 * @  수정일         수정자              수정내용
 * @ ---------    ---------   -------------------------------
 * @ 2014. 10. 30.	김진형			최초생성
 * </pre>
 * Copyright (C) by Make-IT All right reserved.
 */
@Component
public class SmsSender {
	private static final Logger logger = LoggerFactory.getLogger(SmsSender.class);
	private static final String CHARSET = "UTF-8";
	private static final String SMS_URL = "http://sslsms.cafe24.com/sms_sender.php";
	private static final String USER_ID = "tiaranail";
	private static final String SECURE_CODE = "a569073d748e56bfb591366745ab791f";
	
	/**
	 * 문자메시지 보내기
	 * @author kimjinhyung
	 * @date 2014. 10. 30.
	 * @param sendNum 발신자 번호 "031-xxxx-xxxx" 형식
	 * @param targetNum 수신자번호"010-XXXX-XXXX" 형식 유지
	 * @param msg 메시지내용 80바이트
	 * @return 결과
	 */
	public String sendSms(String sendNum, String targetNum, String msg) {
		String target[] = concatNum(sendNum);
		String returnString = "error";
		try {
			returnString = send(targetNum, target[0], target[1], target[2], msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	
	/**
	 * 폰번호 하이픈 구분
	 * @author kimjinhyung
	 * @date 2014. 10. 30.
	 * @param phoneNumber 폰 번호
	 * @return
	 */
	private static String[] concatNum(String phoneNumber) {
		int firstHipen = phoneNumber.indexOf("-");
		int secondHipen = phoneNumber.indexOf("-", firstHipen + 1);
		
		String args[] = new String[3];
		args[0] = phoneNumber.substring(0, firstHipen);
		args[1] = phoneNumber.substring(firstHipen + 1, secondHipen);
		args[2] = phoneNumber.substring(secondHipen + 1);
		
		return args;
	}
	
	/**
	 * 메시지 송신
	 * @author kimjinhyung
	 * @date 2014. 10. 30.
	 * @param rphone 발신자
	 * @param sphone1 수신자번호1
	 * @param sphone2 수신자번호2
	 * @param sphone3 수신자번호3
	 * @param msg 메시지
	 * @return 결과
	 * @throws Exception
	 */
	private String send(String rphone, String sphone1, String sphone2, String sphone3, String msg) throws Exception {
		System.out.println(sphone1 + sphone2 + sphone3);
	    String rdate = "";		//예약날짜
	    String rtime = "";		//예약시간
	    String mode = ("1");//BASE64쓸경우1
	    String testflag = "";//테스트시Y
	    String destination = "";//이름삽입번호 (메일머지)
	    String repeatFlag = "";	//반복할꺼Y
	    String repeatNum = "";		//반복횟수
	    String repeatTime = "";	//반복시간
	    String returnurl = "";					//리턴Url
	    String nointeractive = "";			//성공시1
		String smsType = ("S");			//발송타입

	    String[] host_info = SMS_URL.split("/");
	    String host = host_info[2];
	    String path = "/" + host_info[3];
	    int port = 80;

	    // 데이터 맵핑 변수 정의
	    String arrKey[]
	        = new String[] {"user_id","secure","msg", "rphone","sphone1","sphone2","sphone3","rdate","rtime"
	                                ,"mode","testflag","destination","repeatFlag","repeatNum", "repeatTime", "smsType"};
	    String valKey[]= new String[arrKey.length] ;
	    valKey[0] = USER_ID;
	    valKey[1] = SECURE_CODE;
	    valKey[2] = msg;
	    valKey[3] = rphone;
	    valKey[4] = sphone1;
	    valKey[5] = sphone2;
	    valKey[6] = sphone3;
	    valKey[7] = rdate;
	    valKey[8] = rtime;
	    valKey[9] = mode;
	    valKey[10] = testflag;
	    valKey[11] = destination;
	    valKey[12] = repeatFlag;
	    valKey[13] = repeatNum;
	    valKey[14] = repeatTime;
	    valKey[15] = smsType;

	    String boundary = "";
	    Random rnd = new Random();
	    String rndKey = Integer.toString(rnd.nextInt(32000));
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] bytData = rndKey.getBytes();
	    md.update(bytData);
	    byte[] digest = md.digest();
	    for(int i =0;i<digest.length;i++)
	    {
	        boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
	    }
	    boundary = "---------------------"+boundary.substring(0,10);

	    // 본문 생성
	    String data = "";
	    String index = "";
	    String value = "";
	    for (int i=0;i<arrKey.length; i++)
	    {
	        index =  arrKey[i];
	        value = valKey[i];
	        data +="--"+boundary+"\r\n";
	        data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
	        data += "\r\n"+value+"\r\n";
	        data +="--"+boundary+"\r\n";
	    }

	    //out.println(data);

	    InetAddress addr = InetAddress.getByName(host);
	    Socket socket = new Socket(host, port);
	    // 헤더 전송
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));
	    wr.write("POST "+path+" HTTP/1.0\r\n");
	    wr.write("Content-Length: "+data.length()+"\r\n");
	    wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
	    wr.write("\r\n");

	    // 데이터 전송
	    wr.write(data);
	    wr.flush();

	    // 결과값 얻기
	    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),CHARSET));
	    String line;
	    String alert = "";
	    ArrayList tmpArr = new ArrayList();
	    while ((line = rd.readLine()) != null) {
	        tmpArr.add(line);
	    }
	    wr.close();
	    rd.close();

	    String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
	    String[] rMsg = tmpMsg.split(",");
	    String Result= rMsg[0]; //발송결과
	    System.out.println(Result);
	    String Count= ""; //잔여건수
	    if(rMsg.length>1) {Count= rMsg[1]; }

	    //발송결과 알림
	    if(Result.equals("success")) {
	        alert = "성공적으로 발송하였습니다.";
	        alert += " 잔여건수는 "+ Count+"건 입니다.";
	    }
	    else {
	        alert = "[Error]"+Result;
	    }
	    
	    return alert;
		
	}
}
