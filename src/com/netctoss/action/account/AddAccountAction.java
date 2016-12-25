package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Account;
import dao.DAOException;
import dao.IAccountDao;

@Repository("AddAccountAction")
@Scope("prototype")
public class AddAccountAction {
	private Account account;
	@Resource
	private IAccountDao dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		if(account.getRecommender_id()==0){
			System.out.println("++++++++" + account.getRecommender_id()+"++++++");
		}else{System.out.println("--------------");}
		boolean flag = false;
		try {
			flag = dao.addAccount(account);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		if(flag){
			return "success";
		}
		return "error";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
