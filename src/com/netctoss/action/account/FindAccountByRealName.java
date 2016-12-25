package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import dao.DAOException;
import dao.IAccountDao;

@Repository("FindAccountByRealName")
@Scope("prototype")
public class FindAccountByRealName {
	private String realname;
private boolean pass;
@Resource
private IAccountDao dao;
	public String execute() {
		try {
			pass = dao.findAccountByRealName(realname);
			System.out.println("pass :"+pass);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
