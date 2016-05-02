package com.tiaranail.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.makeit.gcm.GCMSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaranail.web.dao.AccountDaoImpl;
import com.tiaranail.web.dao.First_PayDaoImpl;
import com.tiaranail.web.domain.First_Pay;
import com.tiaranail.web.domain.PayList;
import com.tiaranail.web.domain.SavedMoney;

@Service
public class First_PayServiceImpl {
	@Autowired
	First_PayDaoImpl dao;
	@Autowired
	AccountDaoImpl accDao;

	@Autowired
	GCMSender gcmSender;
	@Autowired
	AccountServiceImpl service;
	
	public int insert_Pay(First_Pay pay) {
		// TODO Auto-generated method stub
		int money = pay.getTotal_money();
		double rate = 0;
		pay.setLeft_money(money);
		switch (money) {
		case 100000:
			rate = 0.1;
			break;
		case 200000:
			rate = 0.2;
			break;
		case 300000:
			rate = 0.3;
			break;
		default:
			break;
		}
		pay.setFp_rate(rate);
		int a = dao.insert(pay);

		addPayList(pay.getId(), money, "5", rate, money,"선입금");

		return a;
	}

	public List<First_Pay> showFirstPayList(String id) {
		if (id.equals("")) {
			return dao.showAllFirstPayList();
		}
		return dao.showList(id);
	}

	public List<PayList> Date_find(HashMap<String, Object> map) {
		if (map.get("id").equals("")) {
			return dao.Date_Find(map);
		}
		return dao.Date_Find_id(map);
	}

	public int showFirstPay(String id) {
		if (id.equals("")) {
			return 0;
		}
		List<First_Pay> list = dao.SelectID_Test(id);
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getLeft_money();
			;
		}
		return sum;
	}

	public int showSavedMoney(String id) {
		if (id.equals("")) {
			return 0;
		}
		return dao.FindSaveMoney(id).getSm_sum();
	}

	public List<PayList> showPayList(String id) {
		if (id.equals("")) {
			return dao.showAllPayList();
		}
		return dao.showPayList(id);
	}

	public String showPayment(Map<String, Object> paramMap) {
		// 1. 아이디 가 정확한가? 정확하지 않으면 리턴
		// 2. 금액이 숫자인가? 숫자가 아니라면 리턴
		// 3. 체크가 하나도 안되어있다면? 체크하라고 리턴
		String result = "", tempResult = "";
		

		String first_money = "";
		if ((first_money = (String) paramMap.get("first_money")) == null) {
			first_money = "";
		}
		String cash = "";
		if ((cash = (String) paramMap.get("cash")) == null) {
			cash = "";
		}
		String card = "";
		if ((card = (String) paramMap.get("card")) == null) {
			card = "";
		}

		String sm = "";
		if ((sm = (String) paramMap.get("sm")) == null) {
			sm = "";
		} // //체크박스 정보 받기.

		String id = (String) paramMap.get("id");
		if (accDao.idCheck((String) paramMap.get("id")).equals("0")) { // 아이디가
																		// 존제하는지
																		// 확인
			if (id != "")
				return "0";
			id = "noId";
		}
		int useMoney = 0;
		try {
			if (paramMap.get("money") == "")
				return "1";

			useMoney = Integer.valueOf((String) paramMap.get("money")); // 원하는
																		// 금액
																		// 입력.
		} catch (NumberFormatException e) {
			return "2";
		}

		int useSavedMoney = 0;
		try {
			if (paramMap.get("saved_money") != "" && sm.equals("on"))
				useSavedMoney = Integer.parseInt((String) paramMap
						.get("saved_money")); // 원하는 금액 입력.
			if (paramMap.get("saved_money") == "" && sm.equals("on"))
				return "3";

		} catch (NumberFormatException e) {
			return "2";
		}

		if (first_money.equals("") && cash.equals("") && card.equals("")
				&& sm.equals("")) {
			return "4";
		}

		if (sm.equals("on")) {
			if (id.equals("noId")) { // id가없으면 적립금도 없다.

				return "5";
			}
			int mySavedMoney = dao.FindSaveMoney(id).getSm_sum();
			if (mySavedMoney < useSavedMoney) // id가 있지만, 보유금액 이 입력 금액보다 적다.
				return "6";
			// id도 있고, 적립금도 충분히 있다ㅇ
			useMoney = useMoney - useSavedMoney;
			if (useMoney != 0)
				if (first_money.equals("") && cash.equals("")
						&& card.equals("")) {
					return "7";
				}
			addPayList(id, useMoney, "1", 0, mySavedMoney, (String)paramMap.get("service"));
			InsertSavedMoney(id, -useSavedMoney);
			result = tempResult + "사용한 적립금은 :" + String.valueOf(useSavedMoney)
					+ "남은 적립금은 : " + String.valueOf(mySavedMoney);
			tempResult = result;
		}
		if (cash.equals("on")) {
			result = tempResult + "현금 결제 금액: " + useMoney;
			tempResult = result;
			addPayList(id, useMoney, "3", 0, 0, (String)paramMap.get("service"));
			if (!id.equals("noId")) {
				InsertSavedMoney(id, (int) (useMoney * 0.03));
			}
		}
		if (card.equals("on")) {
			addPayList(id, useMoney, "4", 0, 0, (String)paramMap.get("service"));

			if (!id.equals("noId")) {
				InsertSavedMoney(id, (int) (useMoney * 0.02));
			}

			result = tempResult + "카드 사용 금액: " + useMoney;
			tempResult = result;

		}

		if (first_money.equals("on") && id.equals("noId") ) {
			return "8";
		}
		if (first_money.equals("on") && !id.equals("noId")) {
			int count = dao.countFirstPay(id);
			int state = 0; // 0 . 결제완료 1 . 충전하고 다시 쓰기 or 잔액은 현금이나 카드 결제.
			int needMoney = useMoney;
			First_Pay pay = dao.SelectId(id);
			int left_money = 0;
			try {
				left_money = pay.getLeft_money();
			} catch (NullPointerException e) {
				return "9";
			}
			System.out.print("left_money = " + left_money + "\n");
			if (left_money != -1) { // 쿼리 실행 성공
				if (left_money >= needMoney) { // 필요 금액보다 남은 적립금이 많을 경우
					needMoney = (int) (needMoney - (needMoney * pay
							.getFp_rate()));
					left_money = left_money - needMoney;
					pay.setLeft_money(left_money);

					int test = dao.useMoney(pay);
					System.out.print(left_money - needMoney + "dd" + "\n");
					if (test == 1) { // 잘 실행되었다면
						addPayList(pay.getId(), needMoney, "2",
								pay.getFp_rate(), left_money, (String)paramMap.get("service"));

						state = 0;
						result = tempResult + "선입금 사용 금액은 :" + needMoney;
						tempResult = result;

					}
				} else { // 적립금액이 부족할때. (0.다른 충전된 3하기. 1. 또 충전하고 거기서 쓰기 or 현금이나
							// 카드결
					if (count > 1) {
						needMoney = needMoney
								- (left_money + (int) (left_money * pay
										.getFp_rate())); // 잔여
						pay.setLeft_money(0);

						int tempMoney = left_money
								- (int) (left_money * pay.getFp_rate());
						dao.useMoney(pay);
						addPayList(pay.getId(), tempMoney, "2",
								pay.getFp_rate(), 0, (String)paramMap.get("service"));

						result = tempResult + "+" + String.valueOf(tempMoney);

						pay = dao.SelectId(id);

						left_money = pay.getLeft_money();
						if (left_money != -1) { // 쿼리 실행 성공
							needMoney = (int) (needMoney - (needMoney * pay
									.getFp_rate()));
							left_money = left_money - needMoney;
							pay.setLeft_money(left_money);

							int test = dao.useMoney(pay);
							System.out.print(left_money - needMoney + "dd"
									+ "\n");
							if (test == 1) { // 잘 실행되었다면
								addPayList(pay.getId(), needMoney, "2",
										pay.getFp_rate(), left_money, (String)paramMap.get("service"));

								state = 0;
							}
						} else if (count == 1) {
							needMoney = needMoney
									- (left_money + (int) (left_money * pay
											.getFp_rate())); // 잔여
							pay.setLeft_money(0);
							dao.useMoney(pay);
							state = needMoney;
						}
					}
				}
			}

		}

		return result;

	}

	public void addPayList(String id, int money, String type, double rate,
			int leftMoney,String addService) {
		
		PayList pList = new PayList();
		pList.setPay_id(id);
		pList.setPay_money(money);
		pList.setPay_type(type);
		pList.setRate(rate);
		pList.setleft_money(leftMoney);
		switch (Integer.parseInt(type)) {
		case 1:
			pList.setService("적립금 사용 - "+addService);
			break;
		case 2:
			pList.setService("선입금 사용 - "+addService);
			break;
		case 3:
			pList.setService("현금 사용 - "+addService);
			break;
		case 4:
			pList.setService("카드 사용 - "+addService);
			break;
		case 5:
			pList.setService("선입금 - "+addService);
			break;

		default:
		//	pList.setService("적립금 사용");
			break;
		}
		
		payPush(id, "티아라 네일 결제내역 알림", pList.getService() + pList.getPay_money()+"원 처리 되었습니다");
		System.out.print(pList.getService());
		dao.writeList(pList); // 결제목록에 추가하기.
		

	}

	public void InsertSavedMoney(String id, int money) {
		SavedMoney sm = dao.FindSaveMoney(id);
		if (sm == null) {
			sm = new SavedMoney();
			sm.setSm_id(id);
			sm.setSm_new_money(money);
			sm.setSm_sum(money);
			dao.InsertSavedMoney(sm);
		} else {
			int sum = sm.getSm_sum();
			sm.setSm_sum(sum + money);
			sm.setSm_new_money(money);
			dao.AddSaveMoney(sm);

		}

	}

	// 병주 추가
	public List<PayList> FindPayListType(HashMap<String, Object> map) {
		return dao.FindPayListType(map);
	}
	
	//푸쉬 보내기 
	public void payPush(String id, String title,String msg){
		String key = "";
		if ((key = service.findGcmKey(id)) != null) {
			gcmSender.setMessage(title, msg);
			gcmSender.sendMessage(key);
			System.out.println("key:" + key);
		}//else if 문자보내 
		
	}

}
