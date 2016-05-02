package kr.co.makeit.gcm;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * GCM 보내기 위한 클래스
 * @since 2014. 8. 20.
 * @version 1.0
 * @author jinhyung
 * <pre>
 * @  수정일         수정자              수정내용
 * @ ---------    ---------   -------------------------------
 * @ 2014. 8. 20.	jinhyung			최초생성
 * </pre>
 * Copyright (C) by Make-IT All right reserved.
 */
@Component
@Scope("prototype")
public class GCMSender {
	private static final Logger logger = LoggerFactory.getLogger(GCMSender.class);
	
	private Sender gcmSender; // GCM Sender
	private Message gcmMessage; // GCM MEssage
	private Result gcmResult; // GCM Result (단일전송)
	private MulticastResult gcmMultiResult; // GCM Multi Result (일괄 전송)

	// 개발자 콘솔에서 발급받은 API Key
	private static String API_KEY = "AIzaSyB79QGSAPHBdKru-aUblt8p04c1hsK8X98";
	// 메세지의 고유 ID(?)정도로 생각하면 됩니다. 메세지의 중복수신을 막기 위해 랜덤값을 지정합니다
	private static String COLLAPSE_KEY = String.valueOf(Math.random() % 100 + 1);
	// 기기가 활성화 상태일 때 보여줄 것인지.
	private static boolean DELAY_WHILE_IDLE = true;
	// 기기가 비활성화 상태일 때 GCM Storage에 보관되는 시간
	private static int TIME_TO_LIVE = 3;
	// 메세지 전송 실패시 재시도할 횟수
	private static int RETRY = 3;

	/**
	 * 메시지 설정
	 * @author kimjinhyung
	 * @date 2014. 9. 28.
	 * @param title 제몰
	 * @param msg 내용
	 */
	public void setMessage(String title, String msg) {
		gcmSender = new Sender(API_KEY);
		gcmMessage = new Message.Builder().collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
				.addData("title", title).addData("msg", msg)
				.build();
	}

	/**
	 * 단일 전송시 사용
	 * @author jinhyung
	 * @date 2014. 8. 20.
	 * @param regId 보낼 단말기의 Reg ID
	 */
	public void sendMessage(String regId) {
		// 단일전송시에 사용
		try {
			gcmResult = gcmSender.send(gcmMessage, regId, RETRY);
		} catch (IOException e) {
			logger.info("IOException " + e.getMessage());
		}
		logger.info(
				"getCanonicalIds : " + gcmResult.getCanonicalRegistrationId()
						+ "\n" + "getMessageId : " + gcmResult.getMessageId());
	}
	/**
	 * 다중 전송시 사용
	 * @author jinhyung
	 * @date 2014. 8. 20.
	 * @param regIdList 보낼 단말기들의 Reg ID List
	 */
	public void sendMultiMessage(List<String> regIdList){
		// 일괄전송시에 사용
				try {
					gcmMultiResult = gcmSender.send(gcmMessage, regIdList, RETRY);
				} catch (IOException e) {
				}
				logger.info("getCanonicalIds : " + gcmMultiResult.getCanonicalIds()
						+ "\n" + "getSuccess : " + gcmMultiResult.getSuccess() + "\n"
						+ "getTotal : " + gcmMultiResult.getTotal() + "\n"
						+ "getMulticastId : " + gcmMultiResult.getMulticastId());
	}

}
