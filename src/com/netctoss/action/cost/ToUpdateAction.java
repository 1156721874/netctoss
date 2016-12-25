package com.netctoss.action.cost;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Cost;
import dao.DAOException;
import dao.ICostDao;

@Repository("ToUpdateAction")
@Scope("prototype")
public class ToUpdateAction {
	// input data
	private int id;
	// output data
	private Cost cost;
	@Resource
	private ICostDao costdao;
	public String execute() {
		try {
			cost = costdao.findById(id);
			if (cost != null) {
				return "success";
			} else {
				return "error";
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ICostDao getCostdao() {
		return costdao;
	}

	public void setCostdao(ICostDao costdao) {
		this.costdao = costdao;
	}
}
