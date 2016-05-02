package com.tiaranail.web.domain;

import org.springframework.web.multipart.MultipartFile;

public class Gallery {
	private int gNum;
	private String gTitle;
	private String gBody;
	private String gThumbNail;
	private String gFileName;
	private String gFileUrl;
	private MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getgNum() {
		return gNum;
	}
	public void setgNum(String gNum) {
		this.gNum = Integer.parseInt(gNum);
	}
	public String getgTitle() {
		return gTitle;
	}
	public void setgTitle(String gTitle) {
		this.gTitle = gTitle;
	}
	public String getgBody() {
		return gBody;
	}
	public void setgBody(String gBody) {
		this.gBody = gBody;
	}
	public String getgThumbNail() {
		return gThumbNail;
	}
	public void setgThumbNail(String gThumbNail) {
		this.gThumbNail = gThumbNail;
	}
	public String getgFileName() {
		return gFileName;
	}
	public void setgFileName(String gFileName) {
		this.gFileName = gFileName;
	}
	public String getgFileUrl() {
		return gFileUrl;
	}
	public void setgFileUrl(String gFileUrl) {
		this.gFileUrl = gFileUrl;
	}
	
	@Override
	public String toString() {
		return "Gellery [gNum=" + gNum + ", gTitle=" + gTitle + ", gBody="
				+ gBody + ", gThumbNail=" + gThumbNail + ", gFileName="
				+ gFileName + ", gFileUrl=" + gFileUrl + "]";
	}
}
