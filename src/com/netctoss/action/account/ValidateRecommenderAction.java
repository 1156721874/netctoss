package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import dao.DAOException;
import dao.IAccountDao;

@Repository("ValidateRecommenderAction")
@Scope("prototype")
public class ValidateRecommenderAction {
	private String reidcard;
	private int pass;
	@Resource
	private IAccountDao dao;
	public String execute() {
		try {
			pass = dao.findByIdCard(reidcard);
			System.out.println("pass:"+pass);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String getReidcard() {
		return reidcard;
	}

	public void setReidcard(String reidcard) {
		this.reidcard = reidcard;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}
}
