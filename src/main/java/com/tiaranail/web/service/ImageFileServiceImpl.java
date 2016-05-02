package com.tiaranail.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaranail.web.dao.ImageFileDaoImpl;
import com.tiaranail.web.domain.ImageFile;
@Service
public class ImageFileServiceImpl {
	
	@Autowired
	ImageFileDaoImpl dao;
	

	public int InsertFile(ImageFile imageFile) {
		int a =0;
		try {
			dao.insertFile(imageFile);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			a =1;
		}
		System.out.print("\n"+"service"+a);
		return a;
	}


	public List<ImageFile> ViewImage() {
		// TODO Auto-generated method stub
		
		
		return dao.ViewImage();
	}

	

}
