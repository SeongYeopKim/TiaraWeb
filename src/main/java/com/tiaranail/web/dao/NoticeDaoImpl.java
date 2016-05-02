package com.tiaranail.web.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tiaranail.web.domain.Notice;

@Repository
public class NoticeDaoImpl extends SqlSessionDaoSupport  {

	

	public List<Notice> NoticeList() {
		

		return getSqlSession().selectList("notice.showNotice");
		
	}

	public int UploadNotice(Notice not) {
		Integer a = getSqlSession().update("notice.UploadNotice", not);
		
				
		
		return a;
	}
	public void Delete(String num){
		getSqlSession().update("notice.delete", num);
	}
}

