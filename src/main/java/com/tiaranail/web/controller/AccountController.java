package com.tiaranail.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiaranail.web.domain.Account;
import com.tiaranail.web.service.AccountServiceImpl;

@Controller
public class AccountController {
	Account account;
	List<Account> accountList;
	@Autowired
	AccountServiceImpl accountservice;

	@RequestMapping(value = "signUp.tiara", method = RequestMethod.POST)
	public String loginAfter(@ModelAttribute Account paramMap) {
		// 가입실패시 1 반환 성공시 0 반환.
		accountservice.join(paramMap);
		return "redirect:/list.tiara";
	}

	@RequestMapping(value = "firstlogin.tiara", method = RequestMethod.POST)
	public String firstLogin(@ModelAttribute Account paramMap, HttpSession session) {
		account = new Account();
		account = accountservice.masterLogin(paramMap);
		
		
		java.util.Calendar cal = java.util.Calendar.getInstance(); 
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
		

		String now = format.format(cal.getTime());
		
		cal.add(cal.DATE, -7); // 7일(일주일)을 뺀다
		
		String before = format.format(cal.getTime());
		
		if (account == null) {
			session.setAttribute("state", "fail");
			return "redirect:/goMasterLogin.tiara";
		} else {
			session.setAttribute("user", account);
			return "redirect:date_find.tiara?start_date="+before+"&end_date="+now+"&id=";
		}
	}
	@RequestMapping(value = "home.tiara", method = RequestMethod.GET)
	public String home() {
		
		java.util.Calendar cal = java.util.Calendar.getInstance(); 
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
		
		
		String now = format.format(cal.getTime());
		
		cal.add(cal.DATE, -7); // 7일(일주일)을 뺀다
		
		String before = format.format(cal.getTime());
		
			return "redirect:date_find.tiara?start_date="+before+"&end_date="+now+"&id=";
	}

	@RequestMapping(value = "listJson.tiara", method = RequestMethod.POST)
	public String godogo(@RequestParam Map<String, String> paramMap,
			HttpServletRequest request, Model model) {
		model.addAttribute("account", accountservice.accountList(paramMap));

		// List<Account>
//		return "account/accountList";
		return "side";
	}
	
	@RequestMapping(value ="nickNameCheck.tiara", method = RequestMethod.GET)
	public String nickCheck(@RequestParam Map<String, String> paramMap, Model model){
		model.addAttribute("result", accountservice.idCheck(paramMap.get("id")));
		return "checkId";
	}
	@RequestMapping(value ="nickNameCheckAnd.tiara", method = RequestMethod.GET)
	public @ResponseBody int nickCheckAnd(@RequestParam Map<String, String> paramMap){
		return Integer.parseInt(accountservice.idCheck(paramMap.get("id")));
	}


}
