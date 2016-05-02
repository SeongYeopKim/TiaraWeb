package com.tiaranail.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tiaranail.web.domain.ImageFile;
import com.tiaranail.web.service.ImageFileServiceImpl;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	ImageFileServiceImpl service;
	
	@RequestMapping(value="FileUpload.tiara", method=RequestMethod.GET)
	public ModelAndView fileForm(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fileForm");
		return mv;
		
	}
	
	@RequestMapping(value = "FileUpload.tiara", method = RequestMethod.POST)
    public String fileSubmit(@ModelAttribute ImageFile img,HttpServletRequest request) {
        MultipartFile uploadfile = img.getUpladfile();
        logger.info("1 ");
        if (uploadfile != null) {
            String fileName = uploadfile.getOriginalFilename();
            img.setFile_title(fileName);
            try {
               
            	String path = request.getSession().getServletContext().getRealPath("/");
                File file = new File(path +"resources/"+ fileName);
                System.out.print(path + fileName+"\n");
                uploadfile.transferTo(file);
                logger.info("sdfsdf ");
                img.setFile_url("resources/"+fileName);
                
                service.InsertFile(img);
                System.out.print("123\n"+ img.getFile_url()+"123\n"+img.getFile_content()+"123\n"+img.getFile_title()+"123\n");
                
            } catch (IOException e) {
            	logger.info("로긴 띄우기 ");
            	e.printStackTrace();
            } // try - catch
        } // if
        // 데이터 베이스 처리를 현재 위치에서 처리
        return "redirect:goUpload.tiara"; // 리스트 요청으로 보내야하는데 일단 제외하고 구현
    }
	

	@RequestMapping(value = "ViewImage.tiara", method = RequestMethod.GET)
    public @ResponseBody List<ImageFile> ViewImage() {
		
        return (List<ImageFile>) service.ViewImage(); // 리스트 요청으로 보내야하는데 일단 제외하고 구현
    }
	
	
}
