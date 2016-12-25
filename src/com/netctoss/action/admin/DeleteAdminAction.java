package com.netctoss.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import dao.DAOException;
import dao.IAdminDao;

@Repository
@Scope("prototype")
public class DeleteAdminAction {
private int id;
@Resource
private IAdminDao dao;
public String execute(){
	try {
		dao.deleteAdminById(id);
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
}
