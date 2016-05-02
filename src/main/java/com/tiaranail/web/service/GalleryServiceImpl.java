package com.tiaranail.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaranail.web.dao.GalleryDaoImpl;
import com.tiaranail.web.domain.Gallery;
import com.tiaranail.web.domain.Video;

@Service
public class GalleryServiceImpl {

	@Autowired
	GalleryDaoImpl dao;
	
	public List<Gallery> getGalleryTotalList(){
		return dao.getGalleryTotalList();
	}
	public void addGallery(Gallery gallery){
		dao.addGallery(gallery);
	}
	public void addVideo(Video video)
	{
		dao.addVideo(video);
	}
	public List<Video> getVideoList(){
		return dao.getVideoList();
	}
	public void removeGall(Gallery gallery){
		dao.removeGall(gallery);
	}
	public void removeVideo(Video video) {
		// TODO Auto-generated method stub
		dao.removeVideo(video);
		
	}
}
