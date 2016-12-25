package com.netctoss.action.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Cost;
import pojo.Service;
import dao.DAOException;
import dao.ICostDao;
import dao.IService;

@Repository("ToUpdateServiceAction")
@Scope("prototype")
public class ToUpdateServiceAction {
private int id;
private Service service;
private List<Cost> costs;
@Resource
private ICostDao cdao;
@Resource
private IService dao;
public String execute(){
	try {
		service = dao.findServiceById(id);
		costs = cdao.findAll();
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
public Service getService() {
	return service;
}
public void setService(Service service) {
	this.service = service;
}
public List<Cost> getCosts() {
	return costs;
}
public void setCosts(List<Cost> costs) {
	this.costs = costs;
}
}
