package com.netctoss.action.cost;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.ICostDao;

@Repository("StartCostAction")
@Scope("prototype")
public class StartCostAction {
	private int id;
	private boolean pass;
	@Resource
	private ICostDao costdao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		try {
			pass = costdao.startCost(id);
			System.out.println("start cost pass :"+pass);
		} catch (DAOException e) {
			e.printStackTrace();
			pass = false;
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

	public ICostDao getCostdao() {
		return costdao;
	}

	public void setCostdao(ICostDao costdao) {
		this.costdao = costdao;
	}
}
