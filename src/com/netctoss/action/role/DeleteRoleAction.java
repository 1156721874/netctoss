package com.netctoss.action.role;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class DeleteRoleAction {
private int id ;
@Resource
private IRoleDao dao;
@Transactional(propagation=Propagation.REQUIRED)
public String execute(){
	try {
		if(dao.deleteRoleById(id)){
			return "success";
		}
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return "error";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
