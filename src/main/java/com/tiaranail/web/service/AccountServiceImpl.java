package com.tiaranail.web.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaranail.web.dao.AccountDaoImpl;
import com.tiaranail.web.domain.Account;

@Service
public class AccountServiceImpl {

	@Autowired
	AccountDaoImpl dao;
	@Autowired
	First_PayServiceImpl payService;

	public int join(Account account) {
		int a = 0;
		try {
			dao.join(account);

		} catch (Exception e) {
			// TODO: handle exception
			a = 1;
		}
		payService.InsertSavedMoney(account.getId(), 0);

		return a;
	}

	public Account login(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account masterLogin(Account account) {
		// TODO Auto-generated method stub
		return dao.masterLogin(account);
	}

	public List<Account> accountList(Map<String, String> map) {
		String a = map.get("type");
		Account account = new Account();
		int k = 5;
		if (a.equals("name")) {
			k = 0;
		}
		if (a.equals("id")) {
			k = 1;
		}
		if (a.equals("phone"))
			k = 2;
		if (a.equals("all"))
			k = 3;

		switch (k) {
		case 0:
			account.setName(map.get("value"));
			return dao.accountList_name(account);

		case 1:
			account.setId(map.get("value"));
			return dao.accountList_id(account);
		case 2:
			account.setPhone(map.get("value"));
			return dao.accountList_phone(account);
		case 3:
			System.out.print("시발거");
			return dao.accountList_all();
		default:
			System.out.print("시방");
			return null;
		}
	}

	public Account AppLogin(Account account) {
		// TODO Auto-generated method stub
		return dao.AppLogin(account);
	}

	public String idCheck(String id) {
		return dao.idCheck(id);
	}

	public String findGcmKey(String id) {
		return dao.findGcmKey(id);
	}

	public List<String> AllGcmKey() {
		return dao.AllGcmKey();
	}

	public void insertKey(Map<String, String> map) {
		dao.insertKey(map);
	}

	public List<Account> find_birth(String day) {

		return dao.find_birth(day);
	}

	public class WeeklySearch extends TimerTask {

		@Override
		public void run() {
			System.out.println("WeeklySearch!");
		}
	}
	
	public class Scheduler {

	 public void  aa(String[] args) {
	  WeeklySearch weeklySearch = new WeeklySearch();
	  
	  Timer timer = new Timer();
	  Calendar date = Calendar.getInstance();
	  date.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
	  date.set(Calendar.AM_PM, Calendar.PM);
	  date.set(Calendar.HOUR, 11);
	  date.set(Calendar.MINUTE, 29);
	  date.set(Calendar.SECOND, 0);
	  date.set(Calendar.MILLISECOND, 0);
	  
	  timer.scheduleAtFixedRate(weeklySearch, date.getTime(), 
	    1000 * 60 * 60 * 24 * 7);
	 }
	}
	public String find_phone(String id) {

		return dao.find_phone(id);
	}
	public List<String> findNoKeyPhone() {

		return dao.findNoKeyPhone();
	}
	public List<String> findPhone() {

		return dao.findPhone();
	}

	public void updateInpo(Map<String, String> map) {
		// TODO Auto-generated method stub
		dao.updateInpo(map);
	}
}
