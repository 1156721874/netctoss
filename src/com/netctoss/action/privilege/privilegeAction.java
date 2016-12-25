package com.netctoss.action.privilege;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Privilege;
import Util.PrivilegeReader;

@Repository("PrivilegeAction")
@Scope("prototype")
public class privilegeAction {
private Privilege privilege;
private List<Privilege> privileges;
private String existid;
private String id;


public String list(){
	privileges  = PrivilegeReader.getPrivileges();
	return "success";
}

public String checkIsExistId(){
	try {
System.out.println(id+"+++++++++++++++++++++++++++++++++++++++++++++++++++");		
		Set ids = PrivilegeReader.getPrivileigeIds();
		if(ids.contains(id)){
			existid = "0";
			return "success";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "success";
}

public String addPrivilege(){
	try {
		System.err.println(privilege.getStrurls()+"++++++++++"+ privilege.getName() +"--------------------------------------------------------");
		String [] strs  = privilege.getStrurls().split(",");
		List privilegelist = Arrays.asList(strs);
		privilege.setUrls(privilegelist);
		int result = PrivilegeReader.addPrivilege(privilege);
		if(result==0){
			return "error";
		}
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public String toModPrivilege(){
	List<Privilege> prilist =  PrivilegeReader.getPrivileges();
	for(Privilege obj:prilist){
		if(obj.getId().equals(id)){
			privilege = obj;
		}
	}
	return "success";
}

public String modPrivilege(){
	try {
		String [] strs  = privilege.getStrurls().split(",");
		List privilegelist = Arrays.asList(strs);
		privilege.setUrls(privilegelist);
		PrivilegeReader.modPrivilege(privilege);
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public String delPrivilege(){
	try {
		PrivilegeReader.delprivilege(id);
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
	return "success";
}

public List<Privilege> getPrivileges() {
	return privileges;
}

public void setPrivileges(List<Privilege> privileges) {
	this.privileges = privileges;
}

public Privilege getPrivilege() {
	return privilege;
}

public void setPrivilege(Privilege privilege) {
	this.privilege = privilege;
}
public String getExistid() {
	return existid;
}

public void setExistid(String existid) {
	this.existid = existid;
}
public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}
}
