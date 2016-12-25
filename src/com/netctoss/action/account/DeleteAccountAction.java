package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IAccountDao;

@Repository("DeleteAccountAction")
@Scope("prototype")
public class DeleteAccountAction {
private int id;
private boolean pass;
@Resource
private IAccountDao dao;
@Transactional(propagation=Propagation.REQUIRED)
public String execute() {
	System.out.println("action id "+id);
	try {
		pass = dao.deleteById(id);
		//dao.deleteServicesById(id);
		System.out.println("delete pass :"+pass);
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
