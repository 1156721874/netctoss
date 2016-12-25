package com.netctoss.action.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Service;
import dao.DAOException;
import dao.IService;

@Repository("AddServiceAction")
@Scope("prototype")
public class AddServiceAction {
	private Service service;
	@Resource
private IService dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		try {
			System.out.println("AddServiceAction..."+service.getAccount_id());
			dao.addService(service);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
