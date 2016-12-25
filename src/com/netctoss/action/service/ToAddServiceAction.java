package com.netctoss.action.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Cost;
import dao.DAOException;
import dao.ICostDao;

@Repository("ToAddServiceAction")
@Scope("prototype")
public class ToAddServiceAction {
	private List<Cost> costs;
	@Resource
private ICostDao dao;
	public String execute() {
		try {
			costs = dao.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}
}
