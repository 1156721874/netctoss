package com.netctoss.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import dao.DAOException;
import dao.IAdminDao;

@Repository
@Scope("prototype")
public class resetPasswordAction {
	private String ids;

	private boolean pass;
	@Resource
private IAdminDao dao;
	public String execute() {
		String str  = "";
		String  []arr = ids.split(",");
		for(int i=0;i<arr.length;i++){
			str+=(i==0)?arr[i].trim():","+arr[i].trim();
			System.out.println(str);
		}
		String[] pwd = str.split(",");
		try {
			pass = dao.resetPassword(pwd);
		} catch (DAOException e) {
			e.printStackTrace();
			pass = false;
		}
		System.out.println(str);
		return "success";
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
