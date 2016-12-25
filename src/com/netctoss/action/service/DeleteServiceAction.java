package com.netctoss.action.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IService;

@Repository("DeleteServiceAction")
@Scope("prototype")
public class DeleteServiceAction {
	private int id;

	private boolean pass;
	@Resource
private IService dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		try {
			pass = dao.deleteById(id);
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

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
