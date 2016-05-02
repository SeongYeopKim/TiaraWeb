package com.tiaranail.web.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile {

	private String file_title, file_content, file_url;
	private MultipartFile upladfile;
	private Date file_date;
	public String getFile_title() {
		return file_title;
	}
	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}
	public String getFile_content() {
		return file_content;
	}
	public void setFile_content(String file_content) {
		this.file_content = file_content;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public MultipartFile getUpladfile() {
		return upladfile;
	}
	public void setUpladfile(MultipartFile upladfile) {
		this.upladfile = upladfile;
	}
	public Date getFile_date() {
		return file_date;
	}
	public void setFile_date(Date file_date) {
		this.file_date = file_date;
	}
	
}
