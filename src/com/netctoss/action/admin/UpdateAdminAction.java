package com.netctoss.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Admin;
import dao.DAOException;
import dao.IAdminDao;

@Repository
@Scope("prototype")
public class UpdateAdminAction {
private Admin admin;
@Resource
private IAdminDao dao;
@Transactional(propagation=Propagation.REQUIRED)
public String execute(){
	try {
		dao.updateAdmin(admin);
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return "success";
	}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
}
