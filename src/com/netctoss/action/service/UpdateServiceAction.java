package com.netctoss.action.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Service;
import dao.DAOException;
import dao.IService;

@Repository("UpdateServiceAction")
@Scope("prototype")
public class UpdateServiceAction {
private Service service;
@Resource
private IService dao;
@Transactional(propagation=Propagation.REQUIRED)
public String execute(){
	boolean flag = false;
	try {
		flag = dao.updataService(service);
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	if(flag){
		return "success";
	}
	return "error";
}
public Service getService() {
	return service;
}
public void setService(Service service) {
	this.service = service;
}
}
