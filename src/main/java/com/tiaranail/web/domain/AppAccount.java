package com.tiaranail.web.domain;

public class AppAccount {
	String id;
	String name;
	String nic_name;
	String pw;
	String birth;
	String phone;
	String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNic_name() {
		return nic_name;
	}
	public void setNic_name(String nic_name) {
		this.nic_name = nic_name;
	}

}
