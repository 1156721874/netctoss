package pojo;

import Util.PrivilegeReader;

public class Role {
private Integer id;
private String name;
private String[] privilegesIds;
private String privilegenames;

//权限名称是自运算的结果，想XML中查询即可
public String getPrivilegenames() {
	if(privilegesIds==null)
		return null;
	String privilegenames = "";
		for(int i=0;i<privilegesIds.length;i++){
			String privilegeid = privilegesIds[i];
			String privilegename = PrivilegeReader.getPrivilegeNameById(privilegeid);
			privilegenames += (i==0)? privilegename:","+privilegename;
			}
		return privilegenames;
		}
public void setPrivilegenames(String privilegenames) {
	this.privilegenames = privilegenames;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String[] getPrivilegesIds() {
	return privilegesIds;
}
public void setPrivilegesIds(String[] privilegesIds) {
	this.privilegesIds = privilegesIds;
}
@Override
public String toString() {
	return this.id+ " " +this.name;
}

}
