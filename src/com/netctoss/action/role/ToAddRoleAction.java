package com.netctoss.action.role;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import Util.PrivilegeReader;

import pojo.Privilege;

@Repository
@Scope("prototype")
public class ToAddRoleAction {
private List<Privilege> privileges;

public String execute(){
	privileges = PrivilegeReader.getPrivileges();
	return "success";
}

public List<Privilege> getPrivileges() {
	return privileges;
}

public void setPrivileges(List<Privilege> privileges) {
	this.privileges = privileges;
}
}
