package com.tiaranail.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tiaranail.web.domain.First_Pay;
import com.tiaranail.web.domain.PayList;
import com.tiaranail.web.domain.SavedMoney;

@Repository
public class First_PayDaoImpl extends SqlSessionDaoSupport {

	public int insert(First_Pay pay) {
		getSqlSession().insert("money.insert", pay);
		return 0;
	}

	public List<First_Pay> showList(String id) {

		return getSqlSession().selectList("money.showFirstPayList", id);
	}

	public int countFirstPay(String id) {

		return getSqlSession().selectOne("money.countFirstPay", id);
	}

	public First_Pay SelectId(String id) {
		First_Pay pay = new First_Pay();
		try {
			pay = getSqlSession().selectOne("money.SelectId", id);
		} catch (Exception e) {
			pay.setLeft_money(-1);
			System.out.print("aa" + e);
		}
		return pay;
	}

	public int useMoney(First_Pay pay) {
		int good = 0;

		try {
			System.out.print("ssss" + pay.getId() + "ssss"
					+ pay.getLeft_money() + "노노");
			getSqlSession().update("money.useMoney", pay);
			good = 1;

		} catch (Exception e) { // / exception 찾아보기
			good = 0;
		}
		return good;
	}

	public int writeList(PayList pList) {
		int check = 0;

		try {
			getSqlSession().insert("money.writeList", pList);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			check = -1;
		}
		return check;
	}

	public List<PayList> Date_Find(HashMap<String, Object> map) {

		return getSqlSession().selectList("date_find", map);
	}

	public List<PayList> Date_Find_id(HashMap<String, Object> map) {

		return getSqlSession().selectList("date_find_id", map);
	}

	public List<First_Pay> SelectID_Test(String id) {

		return getSqlSession().selectList("SelectID_Test", id);
	}

	 

	public List<PayList> showPayList(String id) {

		return getSqlSession().selectList("show_pay_list", id);
	}

	public List<PayList> showAllPayList() {

		return getSqlSession().selectList("show_all_pay_list");
	}
	
	public List<First_Pay> showAllFirstPayList(){
		return getSqlSession().selectList("showAllFirstPayList");
	}
	public void InsertSavedMoney(SavedMoney sm){
		getSqlSession().insert("insert_saved_money", sm);
		
	}
	public void AddSaveMoney(SavedMoney sm){
		 getSqlSession().insert("add_saved_money", sm);
		
	}
	public SavedMoney FindSaveMoney(String id){
		return getSqlSession().selectOne("find_saved_money", id);
	}
	//병주 추가
	public List<PayList> FindPayListType(HashMap<String, Object> map){
		return getSqlSession().selectOne("show_all_pay_type", map);
	}
	
	//병주 추가 끝

}
