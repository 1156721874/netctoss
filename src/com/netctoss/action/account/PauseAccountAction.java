package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IAccountDao;

@Repository("PauseAccountAction")
@Scope("prototype")
public class PauseAccountAction {
//	 input
	private int id;
	// output 是否成功
	private boolean pass;
	
	@Resource
	private IAccountDao dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		try {
			dao.pauseAccount(id);
			dao.pauseService(id);
			pass = true;
			return "success";
		} catch (DAOException e) {
			e.printStackTrace();
			pass = false;
			return "error";
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
