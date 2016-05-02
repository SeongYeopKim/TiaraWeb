package com.tiaranail.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiaranail.web.domain.Notice;
import com.tiaranail.web.service.NoticeServiceImpl;

@Controller
public class NoticeController {

	Notice notice;
	@Autowired
	NoticeServiceImpl noticeService;
	
	List<Notice> noticeList;
	
	
	@RequestMapping(value="ShowNotice.tiara", method=RequestMethod.GET)
	public @ResponseBody List<Notice> ShowNotice(){
		
		noticeList = new ArrayList<Notice>();
		return (List<Notice>) noticeService.ShowNotice() ;
	}
	
	@RequestMapping(value="UploadNotice.tiara", method={RequestMethod.POST,RequestMethod.GET})
	public String UploadNotice(@ModelAttribute Notice paramMap){
		int a = noticeService.UploadNotice(paramMap);
		// 가입실패시 1 반환 성공시 0 반환.
		return "redirect:/noticeView.tiara";
	}

	@RequestMapping(value="noticeView.tiara", method=RequestMethod.GET)
	public String NoticeView(Model model){
		model.addAttribute("noticeList",noticeService.ShowNotice());
		return "notice" ;
	}
	
	
	@RequestMapping(value="noticeDelete.tiara", method=RequestMethod.GET)
	public String noticeDelete(HttpServletRequest request,Model model){
		noticeService.Delete(request.getParameter("num"));
		
		return "redirect:/noticeView.tiara" ;
	}

	
	

}
