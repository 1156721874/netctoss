package com.netctoss.action.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.DAOException;
import dao.IService;

@Repository("StartServiceAction")
@Scope("prototype")
public class StartServiceAction {
	private int id;

	private String pass;
	@Resource
	private IService dao;
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute() {
		String  flag;
		try {
			/**
			 * pass : 0 和帐务帐号状态不一致
			 * pass : 1 可以修改状态
			 * pass : 2 程序错误
			 */
			System.out.println("StartServiceAction ...");
			flag = dao.checkAccountStatusIsStart(id);
			if(flag.equals("1")||flag.equals("2")){
				pass = "0";
				}else{
					dao.startService(id);
					pass  = "1";
				}
			return "success";
		} catch (DAOException e) {
			e.printStackTrace();
			pass = "2";
			return "error";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
