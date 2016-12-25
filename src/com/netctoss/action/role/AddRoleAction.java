package com.netctoss.action.role;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Role;
import dao.DAOException;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class AddRoleAction {
private Role role;

@Resource
private IRoleDao dao;
@Transactional(propagation=Propagation.REQUIRED)
public String execute(){
	try {
		dao.addRole(role);
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}
}
