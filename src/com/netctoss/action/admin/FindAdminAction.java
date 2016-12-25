package com.netctoss.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import Util.MD5Util;

import com.netctoss.action.BaseAction;

import pojo.Admin;
import pojo.Role;
import dao.DAOException;
import dao.IAdminDao;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class FindAdminAction extends BaseAction {
	//in
	private int privilegeid;
	private int roleid;
	//private int page=1;
//	private int pagesize;
	//private int totalpage;
	//out
private List<Admin> admins ;
private List<Role> roles;
private Admin admin ;
@Resource
private IAdminDao dao;
@Resource
private IRoleDao rdao;
public String execute(){
	//System.out.println("FindAdminAction...");
	try {
		admins = dao.findAll(privilegeid,roleid/**,page,pagesize*/);
		roles = rdao.findAll();
		/*for(int i=0;i<admins.size();i++){
			admins.get(i).createRolenames(admins.get(i).getRoleids());
			System.out.println("rolenames is :"+names);
		}
		totalpage = dao.findToatalPage(privilegeid, roleid, pagesize);
		System.out.println("admins size is"+admins.size()+"pagesize is :"*//**+pagesize+" TOTALPAGE is "+totalpage*//*);*/
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return "success";
}

public String findAdminById(){
	try {
		Admin sadmin =(Admin)session.get("admin");
		String password = MD5Util.parseMd5(sadmin.getPassword());//把带有明码的MD5码解析，返回明码
		String md5pwd = MD5Util.MD5(password);//将明码加密成md5码，向数据库查询
		admin= dao.findAdminById(sadmin.getAdminCode(),md5pwd);
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return "success";
}

public String updateUser(){
	try {
		dao.updateuser(admin);
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public String modPassword(){
	try {
		Admin sadmin =(Admin)session.get("admin");
		String password = MD5Util.parseMd5(sadmin.getPassword());//把带有明码的MD5码解析，返回明码
		String md5pwd = MD5Util.MD5(password);//将明码加密成md5码，向数据库查询
		Admin tadmin= dao.findAdminById(sadmin.getAdminCode(),md5pwd);
		tadmin.setPassword(MD5Util.MD5(admin.getNewpassword()));
		dao.moduserpassword(tadmin);
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public List<Admin> getAdmins() {
	return admins;
}
public void setAdmins(List<Admin> admins) {
	this.admins = admins;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}
public int getPrivilegeid() {
	return privilegeid;
}
public void setPrivilegeid(int privilegeid) {
	this.privilegeid = privilegeid;
}
public int getRoleid() {
	return roleid;
}
public void setRoleid(int roleid) {
	this.roleid = roleid;
}

public Admin getAdmin() {
	return admin;
}

public void setAdmin(Admin admin) {
	this.admin = admin;
}
}
