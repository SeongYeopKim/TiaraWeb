package com.tiaranail.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:/login.do";
	}

	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String goMenu(){
		
		
		return "login";
	}
	
	@RequestMapping(value="signUp.tiara", method=RequestMethod.GET)
	public String loginForm(){
		
		
		logger.info("회원가 ");
		return "side";
	}
	
	@RequestMapping(value="list.tiara", method=RequestMethod.GET)
	public String AccountList(){
		return "side";
	}
	
	@RequestMapping(value="moneyMenu.tiara",method=RequestMethod.GET)
	public String goMoneyMenu(){
		return "money/moneyMenu";
		
	}
	//병주 수정
	@RequestMapping(value="goInsertMoney.tiara", method=RequestMethod.GET)
	public String GoMoney(){ 
		return "charge";
	}
	
	@RequestMapping(value="goShowFirstPayList.tiara", method=RequestMethod.GET)
	public String GoUseMoney(){
		return "money/showFirstPayList";
	}
	@RequestMapping(value="goMasterLogin.tiara", method=RequestMethod.GET)
	public String GoMasterLogin(){
		return "login";
	}
	@RequestMapping(value="goUpload.tiara", method=RequestMethod.GET)
	public String GoUpload(){
		return "image/image";
	}
	@RequestMapping(value="Upload.tiara", method=RequestMethod.GET)
	public String Upload(){
		return "image/upload";
	}
	@RequestMapping(value="NoticeMenu.tiara", method=RequestMethod.GET)
	public String GoNoticeMenu(){
		return "notice";
	}
	@RequestMapping(value="GoUploadNotice.tiara", method=RequestMethod.GET)
	public String GoUploadNotice(){
		return  "notice";
	}
	//병주수정
	@RequestMapping(value="goPayment.tiara", method=RequestMethod.POST) //결제
	public String goPayment(@RequestParam Map<String,String> map, Model model){
		model.addAttribute("saved_money",map.get("saved_money"));
		model.addAttribute("first_money",map.get("first_money"));
		model.addAttribute("id",map.get("id"));
		return  "pay";
	}
	
	@RequestMapping(value="goAppLogin.tiara", method=RequestMethod.GET) //결제
	public String goAppLogin(){
		return  "app/appLogin";
	}
	@RequestMapping(value="goDate_find.tiara", method=RequestMethod.GET) //결제
	public String goDate_find(){
		return  "money/date_find";
	}
	@RequestMapping(value="goAppMoneyPro.tiara", method=RequestMethod.GET) //결제
	public String goAppMoneyPro(){
		return  "app/appMoney";
	}
	@RequestMapping(value="goAppPayList.tiara", method=RequestMethod.GET) //결제
	public String goAppPayList(){
		return  "app/appPayList";
	}
	// 이병주 추가
	
	@RequestMapping(value="goPush.tiara", method=RequestMethod.GET) //푸쉬 
	public String goPush(){
		return  "GCM/push";
	}
	
	
	@RequestMapping(value="memberInfo.do", method=RequestMethod.GET)
	public String memberInfo(){
		return  "memberInfo";
	}
	
	@RequestMapping(value="graph.do", method=RequestMethod.GET)
	public String graph(){
		return  "main";
	}
	@RequestMapping(value="push.do", method=RequestMethod.GET)
	public String push(){
		return  "push";
	}
	//이병주 추가 끝
	
	//성엽 추가 시작 
	@RequestMapping(value="goPushPage.tiara", method=RequestMethod.GET)
	public String goPushPage(){ 
		return  "pushPage";
	}
	
	
	
}
