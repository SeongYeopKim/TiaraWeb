package com.tiaranail.web.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiaranail.web.domain.Account;
import com.tiaranail.web.domain.First_Pay;
import com.tiaranail.web.domain.PayList;
import com.tiaranail.web.service.AccountServiceImpl;
import com.tiaranail.web.service.First_PayServiceImpl;

@Controller
public class PayController {
	private static final Logger logger = LoggerFactory
			.getLogger(PayController.class);

	First_Pay pay;
	@Autowired
	First_PayServiceImpl first_payservice;
	@Autowired
	AccountServiceImpl accountService;

	@RequestMapping(value = "insert.tiara", method = RequestMethod.POST)
	public String InsertMoney(@ModelAttribute First_Pay paramMap) {
		first_payservice.insert_Pay(paramMap);
		logger.info("선입금 삽입 ");
		return "close";
	}

	@RequestMapping(value = "moneyPayment.tiara", method = RequestMethod.POST)
	// 계산함.
	public String moneyPayment(@RequestParam Map<String, Object> paramMap,
			Model model) {

//		0.id가 존재하지 않습니다.
//		1.금액을 입력하세요
//		2.금액을 정확히 입력해주세요
//		3.적립금 금액을 입력하세요.
//		4.체크박스를 확인하세요.
//		5.고객이 아닌분은 적립금이 없습니다.
//		6.적립금이 부족합니다.
//		7.추가 결제할 방법을 선택하세요
//		8.선입금은 아이디가 필요합니다.
//		9.선입금한 금액이 없거나 부족합니다.
		model.addAttribute("message", first_payservice.showPayment(paramMap));
		return "payment";
	}

	@RequestMapping(value = "date_find.tiara", method = {RequestMethod.POST,RequestMethod.GET})
	public String date_find(@RequestParam HashMap<String, Object> paramMap,
			Model model,HttpSession session) {
		java.util.Calendar cal = java.util.Calendar.getInstance();

			int mon = cal.get(cal.MONTH) + 1;
			int dat = cal.get(cal.DATE);
			String tempM = String.valueOf(mon);
			String tempD = String.valueOf(dat);
			String month = "";
			String day = "";

			if (mon < 10) {
				month = "0" + tempM + "-";
			} else
				month = tempM + "-";

			if (dat < 10) {
				day = month + "0" + tempD;
			} else
				day = month + tempD;

			Account account = new Account();
			account.setId("");
			List<PayList> allPayList = first_payservice.Date_find(paramMap);

			List<PayList> saved = new ArrayList<PayList>();
			List<PayList> first = new ArrayList<PayList>();
			List<PayList> cash = new ArrayList<PayList>();
			List<PayList> card = new ArrayList<PayList>();
			List<PayList> charge = new ArrayList<PayList>();

			List<PayList> savedsort = new ArrayList<PayList>();
			List<PayList> firstsort = new ArrayList<PayList>();
			List<PayList> cashsort = new ArrayList<PayList>();
			List<PayList> cardsort = new ArrayList<PayList>();
			List<PayList> chargesort = new ArrayList<PayList>();

			for (int i = 0; i < allPayList.size(); i++) {
				switch (Integer.parseInt(allPayList.get(i).getPay_type())) {
				case 1:
					saved.add(allPayList.get(i));
					break;
				case 2:
					first.add(allPayList.get(i));
					break;
				case 3:
					cash.add(allPayList.get(i));
					break;
				case 4:
					card.add(allPayList.get(i));
					break;
				case 5:
					charge.add(allPayList.get(i));
					break;
				}
			}
			if (saved != null) {
				savedsort = sort(saved);
			}

			if (first != null) {
				firstsort = sort(first);
			}
			if (cash != null) {
				cashsort = sort(cash);
			}
			if (card != null) {
				cardsort = sort(card);
			}
			if (charge != null) {
				chargesort = sort(charge);
			}

			model.addAttribute("saved", savedsort);
			model.addAttribute("first", firstsort);
			model.addAttribute("cash", cashsort);
			model.addAttribute("card", cardsort);
			model.addAttribute("charge", chargesort);

			// 생일인 사람 불러온다.
			model.addAttribute("birth_account", accountService.find_birth(day));

			return "main";
	}

	@RequestMapping(value = "showFirstPay.tiara", method = RequestMethod.POST)
	public String showFirstPay(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		model.addAttribute("saved_money", first_payservice.showSavedMoney(id));
		model.addAttribute("first_money", first_payservice.showFirstPay(id));
		return "money/payment"; // 선입금, 적립금 금액 출력
	}

	@RequestMapping(value = "showFirstPayList.tiara", method = RequestMethod.POST)
	public String showFirstPayList(HttpServletRequest request, // 선입금 금액
			Model model) {
		model.addAttribute("payList",
				first_payservice.showFirstPayList(request.getParameter("id")));
		return "money/showFirstPayList"; // 선입금 내역 출 //id입력하면 해당검색 입력안하면 전체검
	}

	@RequestMapping(value = "payList.tiara", method = RequestMethod.POST)
	public String payList(String id, Model model) {
		model.addAttribute("payList", first_payservice.showPayList(id));
		return "money/payList";
	} // 이용내역 리스트 출력

	@RequestMapping(value = "memberInfo.tiara", method = RequestMethod.GET)
	public String memberInfo(@RequestParam Map<String, String> map,HttpServletRequest request, Model model) {
		
		
		System.out.print(request.getParameter("name"));
		Account account = new Account();

		account.setId(map.get("id"));
		account.setName(uriDecoder(map.get("name")));
		account.setPhone(map.get("phone"));
		account.setBirth(map.get("birth"));
		account.setNic_name(map.get("nic_name"));

		System.out.println(account.getId());
		model.addAttribute("account", account);
		model.addAttribute("saved_money",
				first_payservice.showSavedMoney(account.getId()));
		model.addAttribute("first_money",
				first_payservice.showFirstPay(account.getId()));
		List<PayList> allPayList = first_payservice
				.showPayList(account.getId());

		List<PayList> saved = new ArrayList<PayList>();
		List<PayList> first = new ArrayList<PayList>();
		List<PayList> cash = new ArrayList<PayList>();
		List<PayList> card = new ArrayList<PayList>();
		List<PayList> charge = new ArrayList<PayList>();

		for (int i = 0; i < allPayList.size(); i++) {
			// SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			// String sDate=fmt.format(allPayList.get(i).getPay_date());

			// String eDate=fmt.format(allPayList.get(i).getPay_date());

			// allPayList.add(i, allPayList.get(i).setPay_date(eDate));
			switch (Integer.parseInt(allPayList.get(i).getPay_type())) {
			case 1:
				saved.add(allPayList.get(i));
				break;
			case 2:
				first.add(allPayList.get(i));
				break;
			case 3:
				cash.add(allPayList.get(i));
				break;
			case 4:
				card.add(allPayList.get(i));
				break;
			case 5:
				charge.add(allPayList.get(i));
				break;
			}
		}

		if (map.get("type") != null) {
			int type = Integer.parseInt(map.get("type"));
			switch (type) {
			case 0:
				model.addAttribute("payList", allPayList);
				break;
			case 1:
				model.addAttribute("payList", saved);
				break;
			case 2:
				model.addAttribute("payList", first);
				break;
			case 3:
				model.addAttribute("payList", cash);
				break;
			case 4:
				model.addAttribute("payList", card);
				break;
			case 5:
				model.addAttribute("payList", charge);
				break;

			}
		} else {
			model.addAttribute("payList",
					first_payservice.showPayList(account.getId()));
		}

		return "memberInfo";
	} // 이용내역 리스트 출력

	private List<PayList> sort(List<PayList> paylist) {
		ArrayList<PayList> pl = new ArrayList<PayList>();
		int now = 0;
		for (int i = 0; i < paylist.size(); i++) {
			if (i == 0) {
				PayList pay = new PayList();
				pay.setPay_date(paylist.get(i).getPay_date());
				pay.setPay_money(paylist.get(i).getPay_money());
				pl.add(pay);
			} else {
				if (pl.get(now).getPay_date()
						.equals(paylist.get(i).getPay_date())) {
					pl.get(now).setPay_money(
							pl.get(now).getPay_money()
									+ paylist.get(i).getPay_money());
				} else {
					now++;
					PayList pay = new PayList();
					pay.setPay_date(paylist.get(i).getPay_date());
					pay.setPay_money(paylist.get(i).getPay_money());
					pl.add(pay);
				}
			}
		}
		return pl;
	}
	
	private String urlEncoder(String key) {
		String tmp = null;
		try {
			tmp = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return tmp;
	}
	private String uriDecoder(String key) {
		String tmp="";
		try {
			tmp = URLDecoder.decode(key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmp;
	}
}
