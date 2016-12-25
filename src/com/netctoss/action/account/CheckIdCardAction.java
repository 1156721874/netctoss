package com.netctoss.action.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Account;
import dao.DAOException;
import dao.IAccountDao;

@Repository("CheckIdCardAction")
@Scope("prototype")
public class CheckIdCardAction {
	private Account account;
	private String idcard;
	@Resource
private IAccountDao dao;
	public String execute() {
		try {
			account = dao.findAccountByIdCard(idcard);
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
}
