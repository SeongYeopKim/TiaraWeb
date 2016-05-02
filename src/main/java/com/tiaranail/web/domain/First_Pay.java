package com.tiaranail.web.domain;

import java.sql.Date;

public class First_Pay {
	String id;
	double fp_rate;
	int total_money,left_money,fp_num;
	Date fp_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getFp_rate() {
		return fp_rate;
	}
	public void setFp_rate(double fp_rate) {
		this.fp_rate = fp_rate;
	}
	public int getTotal_money() {
		return total_money;
	}
	public void setTotal_money(int total_money) {
		this.total_money = total_money;
	}
	public int getLeft_money() {
		return left_money;
	}
	public void setLeft_money(int left_money) {
		this.left_money = left_money;
	}
	public int getFp_num() {
		return fp_num;
	}
	public void setFp_num(int fp_num) {
		this.fp_num = fp_num;
	}
	public Date getFp_date() {
		return fp_date;
	}
	public void setFp_date(Date fp_date) {
		this.fp_date = fp_date;
	}
	
	
}