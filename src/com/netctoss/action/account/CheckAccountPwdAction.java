package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import dao.DAOException;
import dao.IAccountDao;

@Repository("CheckAccountPwdAction")
@Scope("prototype")
public class CheckAccountPwdAction {
	private int id;
	private String pwd;
@Resource
	private IAccountDao dao;
	public String execute() {
		System.out.println("llllllll");
		try {
			pwd = dao.findPwdById(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
