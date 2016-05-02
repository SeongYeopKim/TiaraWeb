package com.tiaranail.web.service;


import java.util.List;

import kr.co.makeit.gcm.GCMSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaranail.web.dao.NoticeDaoImpl;
import com.tiaranail.web.domain.Notice;
@Service
public class NoticeServiceImpl {
	
	@Autowired
	NoticeDaoImpl dao;
	@Autowired
	GCMSender gcmSender;
	@Autowired
	AccountServiceImpl service;


	public List<Notice> ShowNotice() {
		// TODO Auto-generated method stub
		return dao.NoticeList();
	}


	public int UploadNotice(Notice not) {
		// TODO Auto-generated method stub
		String title = "티아라";
		String msg = "공지사항이 등록되었습니다.";
		List<String> list = service.AllGcmKey();
		System.out.print(list.size());
		gcmSender.setMessage(title, msg);
		gcmSender.sendMultiMessage(list);
		
		return  dao.UploadNotice(not);
	}
	
	public void Delete(String num){
		dao.Delete(num);
	}

	

}
