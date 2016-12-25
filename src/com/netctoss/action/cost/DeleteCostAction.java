package com.netctoss.action.cost;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.ICostDao;

@Repository("DeleteCostAction")
@Scope("prototype")
public class DeleteCostAction {
	private Integer id;
	@Resource
	private ICostDao costdao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute(){
		try {
			System.out.println(id);
			costdao.deleteById(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public ICostDao getCostdao() {
		return costdao;
	}
	public void setCostdao(ICostDao costdao) {
		this.costdao = costdao;
	}
	
}
