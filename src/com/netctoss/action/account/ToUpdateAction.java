package com.netctoss.action.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Account;
import dao.DAOException;
import dao.IAccountDao;

@Repository("ToUpdateAccountAction")
@Scope("prototype")
public class ToUpdateAction {
	private int id;
private Account account;
private String reidcard;
private Date birthdate;
@Resource
private IAccountDao dao;
	public String execute() {
		try {
			account = dao.findAccount(id);
			reidcard = dao.findIdCardNoById(account.getId());
			String str = account.getIdcard_no();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
			String datestr = str.substring(6, 10)+"/"+str.substring(10,12)+"/"+str.substring(12,14);
			birthdate = sdf.parse(datestr);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		} 
		return "success";
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getReidcard() {
		return reidcard;
	}

	public void setReidcard(String reidcard) {
		this.reidcard = reidcard;
	}
}
