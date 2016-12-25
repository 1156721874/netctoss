package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IAccountDao;

@Repository("UpdatePasswordAction")
@Scope("prototype")
public class UpdatePasswordAction {
private int id;
private String pwd;
private boolean pass;
@Resource
private IAccountDao dao ;
@Transactional(propagation=Propagation.REQUIRED)
public String execute(){
	try {
		pass = dao.updatePasswordById(pwd,id);
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
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
}
