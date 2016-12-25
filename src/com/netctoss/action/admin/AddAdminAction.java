package com.netctoss.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Admin;
import dao.DAOException;
import dao.IAdminDao;

@Service("AddAdminAction")
@Scope("prototype")
public class AddAdminAction {
private Admin admin;
@Resource
private IAdminDao admindao;
@Transactional(propagation=Propagation.REQUIRED)
public String execute(){
	try {
		//System.out.println("保存之前admin :" +admin);
		//System.out.println(Arrays.toString( admin.getRoleids())+"++++++");
		admindao.addAdmin(admin);
	/*	System.out.println("保存之后的ID："+admin.getId());
		for(Integer roleId : admin.getRoleids()){
			AdminRole ar = new AdminRole();
			ar.setKey(new AdminRoleKey(
				admin.getId(),roleId));
			adminroledao.save(ar);
		}*/
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
}
