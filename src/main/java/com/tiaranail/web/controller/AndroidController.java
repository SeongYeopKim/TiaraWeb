package com.tiaranail.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiaranail.web.domain.Account;
import com.tiaranail.web.domain.PayList;
import com.tiaranail.web.service.AccountServiceImpl;
import com.tiaranail.web.service.First_PayServiceImpl;

@Controller
public class AndroidController {
	Account account;
	List<Account> accountList;
	@Autowired
	AccountServiceImpl accountservice;
	@Autowired
	First_PayServiceImpl payService;

	@RequestMapping(value = "appLogin.tiara", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> login(
			@ModelAttribute Account account) {
		account = accountservice.AppLogin(account);
		Map<String, String> map = new HashMap<String, String>();
		if (account == null) {
			map.put("state", "flase");
		} else {
			map.put("id", account.getId());
			map.put("birth", account.getBirth());
			map.put("name", account.getName());
			map.put("phone", account.getPhone());
			map.put("state", "true");
		}
		return map;
	}

	@RequestMapping(value = "appMoneyPro.tiara", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> appMoneyPro(
			HttpServletRequest request, Model model) {

		String id = request.getParameter("id");
		Map<String, String> map = new HashMap<String, String>();
		Integer a = payService.showSavedMoney(id);
		Integer b = payService.showFirstPay(id);
		map.put("saved_money", a.toString());
		map.put("first_money", b.toString());
		return map;
	}

	@RequestMapping(value = "appPayList.tiara", method = RequestMethod.POST)
	public @ResponseBody List<PayList> appPayList(String id) {
		return payService.showPayList(id);
	}

	@RequestMapping(value = "appMyInfo.tiara", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> appMyInfo(
			HttpServletRequest request, Model model) {

		String id = "";
		id = request.getParameter("id");
		Map<String, String> map = new HashMap<String, String>();
		Integer a = payService.showSavedMoney(id);
		Integer b = payService.showFirstPay(id);
		map.put("saved_money", a.toString());
		map.put("first_money", b.toString());

		List<PayList> list = new ArrayList<PayList>();
		list = payService.showPayList(id);

		Map<String, Object> map1 = new HashMap<String, Object>();

		map1.put("2", list);
		map1.put("1", map);

		return map1;
	}

	@RequestMapping(value = "appKey.tiara", method = RequestMethod.GET)
	public String appKey(HttpServletRequest request, Model model) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", request.getParameter("id"));
		map.put("key", request.getParameter("key"));
		
		accountservice.insertKey(map);
		System.out.print(map.get("id"));
		return "redirect:/";
	}
	@RequestMapping(value ="appNickNameCheck.tiara", method = RequestMethod.GET)
	public String appNickCheck(@RequestParam Map<String, String> paramMap, Model model){
		model.addAttribute("result", accountservice.idCheck(paramMap.get("id")));
		return "account/appCheck";
	}
	
	
	
	@RequestMapping(value = "updateInpo.tiara", method = RequestMethod.POST)
	public String updateInpo(HttpServletRequest request, Model model) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", request.getParameter("id"));
		map.put("name", request.getParameter("name"));
		map.put("pw", request.getParameter("pw"));
		map.put("birth", request.getParameter("birth"));
		map.put("phone", request.getParameter("phone"));
		
		accountservice.updateInpo(map);
		System.out.print(map.get("name"));
		return "redirect:/";
	}

}