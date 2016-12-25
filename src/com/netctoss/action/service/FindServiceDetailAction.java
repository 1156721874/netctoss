package com.netctoss.action.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import vo.ServiceVo;
import dao.DAOException;
import dao.IService;

@Repository("FindServiceDetailAction")
@Scope("prototype")
public class FindServiceDetailAction {
private int id;
private ServiceVo service;
@Resource
private IService dao;
public String execute(){
	try {
		service = dao.findServiceById(id);
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
public ServiceVo getService() {
	return service;
}
public void setService(ServiceVo service) {
	this.service = service;
}
}
