package com.netctoss.action.admin;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Admin;
import pojo.Role;
import dao.DAOException;
import dao.IAdminDao;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class ToUpdateAdminAction {
	//in
	private int id;
	//out
	private Admin admin;
	private List<Role> roles;
	@Resource
private IAdminDao dao;
	@Resource
private IRoleDao rdao;
	public String execute(){
		try {
			admin = dao.findById(id);
			roles = rdao.findAll();
			System.out.println(Arrays.toString(admin.getRoleids())+"kkkkkkkkkkk" );
for(int i=0;i<roles.size();i++){
	System.out.println(roles.get(i).getId()+" : "+roles.get(i).getName());
}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return "success";
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
 
}
