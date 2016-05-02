package com.tiaranail.web.domain;

import java.util.Date;

public class SavedMoney {
	String sm_id;
	int sm_sum,sm_new_money;
	Date date;
	public String getSm_id() {
		return sm_id;
	}
	public void setSm_id(String sm_id) {
		this.sm_id = sm_id;
	}
	public int getSm_sum() {
		return sm_sum;
	}
	public void setSm_sum(int sm_sum) {
		this.sm_sum = sm_sum;
	}
	public int getSm_new_money() {
		return sm_new_money;
	}
	public void setSm_new_money(int sm_new_money) {
		this.sm_new_money = sm_new_money;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
