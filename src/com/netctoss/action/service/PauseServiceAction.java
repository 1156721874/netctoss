package com.netctoss.action.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IService;

@Repository("PauseServiceAction")
@Scope("prototype")
public class PauseServiceAction {
//	 input
	private int id;
	// output 是否成功
	private boolean pass;
	@Resource
	private IService dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute(){
		try {
			pass  = dao.pauseService(id);
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
