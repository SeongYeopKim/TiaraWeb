package com.tiaranail.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.makeit.gcm.GCMSender;
import kr.co.makeit.sms.SmsSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiaranail.web.domain.Account;
import com.tiaranail.web.service.AccountServiceImpl;

@Controller
public class TestController {
	@Autowired
	GCMSender gcmSender;
	@Autowired
	AccountServiceImpl service;
	@Autowired
	SmsSender smsSender;
	final static String SendNum = "031-433-4450";
	String birthMessage  = "님, 생일 축하합니다.";

	@RequestMapping("test.jin")
	public String test(HttpServletRequest request) {

		// String key =
		// "APA91bHQYFjfJg_DssbggQmKzjN5EqxZ05wWhVa_e9a3i_IItJQQU2htWkRWJh0QD8oiNJmuiSP7vO3uGHbVdligYXpPBAEcd17fxTlpFsooyKR0jsxbH0SHW66vczZhBppKgkPZYUNVx_o0AzLhe3J_D3hg91yC7A";

		String id = request.getParameter("id");

		String key = "";
		if ((key = service.findGcmKey(id)) != null) {
			String title = request.getParameter("title");
			String msg = request.getParameter("msg");
			gcmSender.setMessage(title, msg);
			gcmSender.sendMessage(key);
			System.out.println("key:" + key);
		}

		return "redirect:/home.tiara";
	}

	public String push(Account account) {
		if (account.getMykey() != null) {
			String title = account.getName() + "회원님 생일을 맞이하여 네일아트로 기분 한번 내보세요!";
			String msg = "";
			gcmSender.setMessage(title, msg);
			gcmSender.sendMessage(account.getMykey());
		} else if (account.getPhone() != null) {
			// SMS 전송하기
		}
		return "redirect:/home.tiara";

	}

	@RequestMapping("birth_push.tiara")
	public String birth_push() {

		java.util.Calendar cal = java.util.Calendar.getInstance();

		Date date = new Date();
		SimpleDateFormat sdf  = new SimpleDateFormat("MM-dd"); 
		
		List<Account> a = new ArrayList<Account>();
		a = service.find_birth(sdf.format(date));

		String title = "";
		String msg = "생일축하합니다";
		
		
		for(int i=0; i<a.size(); i++){
			title= a.get(i).getName()+ birthMessage;
			gcmSender.setMessage(title, msg);
			
			gcmSender.sendMessage(a.get(i).getMykey());
		}
//		if (a != null) {
//			for (int i = 0; i < a.size(); i++) {
//				this.push(a.get(i));
//			}
//		}

		return "redirect:/home.tiara";
	}

	@RequestMapping(value = "message.tiara", method = RequestMethod.POST)
	public String PushOrSms(HttpServletRequest request ,Model model) {
		String title = request.getParameter("title");
		String msg = request.getParameter("msg");
		String option = request.getParameter("opt");
		String remainSms = null;
		System.out.println(title + msg);
		
		
		// all : 푸쉬나 문자나 보내기 push: 푸쉬만 전부다 sms : 만 전부다
		if (option.equals("all")) {

			System.out.println(title + msg + "test push");
			gcmSender.setMessage(title, msg);
			gcmSender.sendMultiMessage(service.AllGcmKey());
			System.out.println(service.AllGcmKey().get(0));

			// 키 없는 사람에게 sms보내기
			List<String> a = service.findNoKeyPhone();
			if (a.size() > 0) {
				for (int i = 0; i < a.size(); i++) {
					remainSms= smsSender.sendSms(SendNum, a.get(i), msg);
					
				}
				model.addAttribute("remainSms",remainSms);
				System.out.print(remainSms);
			}

			// findNoKeyPhone();

		} else if (option.equals("push")) {
			gcmSender.setMessage(title, msg);
			gcmSender.sendMultiMessage(service.AllGcmKey());
			System.out.println(service.AllGcmKey().get(0));

		} else if (option.equals("sms")) {
			List<String> a = service.findPhone();
			System.out.print(a.size());
			if (a.size() > 0) {
				for (int i = 0; i < a.size(); i++) {
					remainSms = smsSender.sendSms(SendNum, a.get(i), msg);
				}
				model.addAttribute("remainSms",remainSms);
				System.out.print(remainSms);
			}
		}
		// 개인에게 보내기.
		// } else if (option.equals("sel")) {
		// String id = request.getParameter("id");
		// String key = service.findGcmKey(id);
		// String aaa = " ";
		// if (key != null) {
		// gcmSender.setMessage(title, msg);
		// gcmSender.sendMessage(key);
		// }else{
		// String phone = service.find_phone(id);
		// if(phone!=null)
		// aaa = smsSender.sendSms(SendNum, phone, title+ " " +msg);
		// System.out.print(aaa);
		// //sms 보내기. service.find_phone(id);
		// }
		// }

		return "pushAfter";
	}

}
