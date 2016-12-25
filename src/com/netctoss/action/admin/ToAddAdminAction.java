package com.netctoss.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Role;
import dao.DAOException;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class ToAddAdminAction {
private List<Role> rolelist;
@Resource
private IRoleDao dao;
public String execute(){
	try {
		rolelist = dao.findAll();
	/*	for(int i=0;i<rolelist.size();i++){
			System.out.println(rolelist.get(i).getId()+" : "+rolelist.get(i).getName());
		}*/
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}
public List<Role> getRolelist() {

	return rolelist;
}
public void setRolelist(List<Role> rolelist) {
	this.rolelist = rolelist;
}
}
