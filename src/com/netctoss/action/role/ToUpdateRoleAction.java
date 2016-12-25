package com.netctoss.action.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Privilege;
import pojo.Role;
import Util.PrivilegeReader;
import dao.DAOException;
import dao.IRoleDao;

@Repository
@Scope("prototype")
public class ToUpdateRoleAction {
	//in
	private int id;
	
	private List<Privilege> privileges;
	private Role role;
@Resource
	private IRoleDao dao;
	public String execute() {
		try {
			role = dao.findById(id); 
			privileges = PrivilegeReader.getPrivileges();
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
