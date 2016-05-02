package com.tiaranail.web.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tiaranail.web.domain.ImageFile;


@Repository
public class ImageFileDaoImpl extends SqlSessionDaoSupport  {

	

	public int insertFile(ImageFile imagefile) {
		// TODO Auto-generated method stub
		
		Integer a;
		try {
			a= getSqlSession().update("image.insertFile", imagefile);
		} catch (Exception e) {
			
			// TODO: handle exception
			a= 9999;
			System.out.print(e.toString());
		}
		System.out.print(a+"dao"+"\n");
		return a;
	}

	public List<ImageFile> ViewImage() {
		// TODO Auto-generated method stub
		
		return getSqlSession().selectList("image.ViewImage");
	}

}
