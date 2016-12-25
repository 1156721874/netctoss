package com.netctoss.action.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pojo.Account;

import com.netctoss.action.BaseAction;

import dao.DAOException;
import dao.IAccountDao;

@Repository
@Scope("prototype")
public class FindAccountAction extends BaseAction {
	private int page = 1;
	private int totalpage;
	private int pagesize;
	// input
	private String idcard_no;// 身份证
	private String login_name;// 登录名
	private String real_name;// 姓名
	private String status;// 状态
	// output
	private List<Account> accounts;
	@Resource
	private IAccountDao dao;
	@Transactional(readOnly=true)
	public String execute() {
		//System.out.println(page+"+++++++++++++++");
		try {
			accounts = dao.findByCondition(idcard_no, login_name, real_name,
					status, page, pagesize);
			totalpage = dao.totalPages(idcard_no, login_name, real_name,
					status, pagesize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getIdcard_no() {
		return idcard_no;
	}

	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}



}
