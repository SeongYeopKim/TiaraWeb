package com.tiaranail.web.domain;

import java.sql.Date;

public class Notice {
	String title,content,notice_num;
	Date date;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getnotice_num() {
		return notice_num;
	}
	public void setnotice_num(String notice_num) {
		this.notice_num = notice_num;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
