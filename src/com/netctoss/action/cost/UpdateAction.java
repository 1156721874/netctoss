package com.netctoss.action.cost;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Cost;
import dao.DAOException;
import dao.ICostDao;

@Repository("UpdateAction")
@Scope("prototype")
public class UpdateAction {
	private Cost cost;
	@Resource
private ICostDao costdao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		boolean flag = false;
		try {
			flag = costdao.updateCost(cost);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		if (flag) {
			return "success";
		} else {
			return "error";
		}
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public ICostDao getCostdao() {
		return costdao;
	}

	public void setCostdao(ICostDao costdao) {
		this.costdao = costdao;
	}
}
