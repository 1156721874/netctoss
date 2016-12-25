package com.netctoss.action.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import vo.ServiceVo;
import dao.DAOException;
import dao.IService;

@Repository("FindServiceAction")
@Scope("prototype")
public class FindServiceAction {
	private int page= 1;
private int pagesize;
private int  totalpage;
private List<ServiceVo> servicevos ;

private String os_username;
private String unix_host;
private String idCardNo;
private String status;
@Resource
private IService dao;
public String execute(){
	try {
		servicevos = dao.findByCondition(os_username, unix_host, idCardNo, status, page, pagesize);
		totalpage = dao.totalPages(os_username, unix_host, idCardNo, status, pagesize);
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public String getIdCardNo() {
	return idCardNo;
}

public void setIdCardNo(String idCardNo) {
	this.idCardNo = idCardNo;
}

public String getOs_username() {
	return os_username;
}

public void setOs_username(String os_username) {
	this.os_username = os_username;
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

public List<ServiceVo> getServicevos() {
	return servicevos;
}

public void setServicevos(List<ServiceVo> servicevos) {
	this.servicevos = servicevos;
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

public String getUnix_host() {
	return unix_host;
}

public void setUnix_host(String unix_host) {
	this.unix_host = unix_host;
}

}
