package com.tiaranail.web.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tiaranail.web.domain.Gallery;
import com.tiaranail.web.domain.Video;

@Repository
public class GalleryDaoImpl extends SqlSessionDaoSupport  {

	public List<Gallery> getGalleryTotalList(){
		return getSqlSession().selectList("gallery.getTotalList");
	}
	public void addGallery(Gallery gallery){
		getSqlSession().insert("gallery.addGallery",gallery);
	}
	public List<Video> getVideoList(){
		return getSqlSession().selectList("gallery.getVideoList");
	}
	public void addVideo(Video video){
		getSqlSession().insert("gallery.addVideo",video);
	}
	public void removeGall(Gallery gallery) {
		getSqlSession().delete("gallery.remove", gallery);		
	}
	public void removeVideo(Video video) {
		getSqlSession().delete("gallery.remove_video", video);	
		
	}
}
