package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Account;
import dao.DAOException;
import dao.IAccountDao;

@Repository("UpdateAccountAction")
@Scope("prototype")
public class UpdateAccountAction {
	private Account account;
	@Resource
private IAccountDao dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
	//	System.out.println("update action is :"+account);
		try {
			dao.updateAccount(account);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
