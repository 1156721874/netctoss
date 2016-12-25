package com.netctoss.action.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Role;
import dao.DAOException;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class FindRoleAction {
private List<Role> roles = new ArrayList<Role>();
private int page=1;
private int pagesize;
private  int totalpage;
@Resource
private IRoleDao dao;

public String execute(){
	System.out.println(" page :"+page+" totalpage :"+totalpage);
	try {
		List<Role> temproles = dao.findAll();
		//System.out.println(temproles);
		int start = (page-1)*pagesize+1;
		int end = (page*pagesize)>temproles.size()?(page-1)*pagesize+(temproles.size()%pagesize):(page*pagesize);
		//System.out.println("temproles size :"+temproles.size());
		totalpage = (temproles.size()%pagesize==0)?temproles.size()/pagesize:(temproles.size()/pagesize)+1;
		for(int i=start-1; i<end;i++){
		//	System.out.println(temproles.get(i).getId());
			roles.add(temproles.get(i));
		}
		//System.out.println(" page :"+page+" totalpage :"+totalpage+" start :"+start+" end :"+end);
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return "success";
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
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
public int getTotalpage() {
	return totalpage;
}
public void setTotalpage(int totalpage) {
	this.totalpage = totalpage;
}
}
