package com.tiaranail.web.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tiaranail.web.domain.Account;

@Repository
public class AccountDaoImpl extends SqlSessionDaoSupport  {

	public int join(Account account) {
	
		Integer a = getSqlSession().update("member.join", account);
		return a;
	}

	public Account login(Account account) {
		// TODO Auto-generated method stub
		return null;
	}
	public Account masterLogin(Account account){
		account =getSqlSession().selectOne("member.masterlogin", account);
		return account;
		
	}

	public List<Account> accountList_name(Account account) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("member.list_name",account);
	}

	public List<Account> accountList_phone(Account account) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("member.list_phone",account);
	}

	public List<Account> accountList_id(Account account) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("member.list_id",account);
	}

	public List<Account> accountList_all() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("member.list_all");
	}

	public Account AppLogin(Account account) {
		// TODO Auto-generated method stub
		System.out.print("app Test");
		return getSqlSession().selectOne("member.app_login",account);
	}
	public String idCheck(String id) {
		return getSqlSession().selectOne("member.duplicateCheck", id);
	}
	public String findGcmKey(String id){
		return getSqlSession().selectOne("member.find_gcm_key", id);
	}
	public List<String> AllGcmKey(){
		return getSqlSession().selectList("all_gcm_key");
	}

	public void insertKey(Map<String,String> map){
		getSqlSession().update("insertKey",map);
	}
	public List<Account> find_birth(String day){
		return getSqlSession().selectList("find_birth", day);
	}
	public String find_phone(String id){	//키가 없는 사람 폰번호
		return getSqlSession().selectOne("find_phone", id);
	}
	public List<String> findNoKeyPhone(){  //키가 없는사람 모든 사람 폰번호
		return getSqlSession().selectList("find_no_key_phone");
	}

	public List<String> findPhone() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("find_phonee");
	}

	public void updateInpo(Map<String, String> map) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateInpo",map);
	}
}
