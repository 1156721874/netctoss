package pojo;

import java.io.Serializable;

public class AdminRoleKey implements Serializable {
private int admin_id;
private int role_id;
public AdminRoleKey(){}
public int getAdmin_id() {
	return admin_id;
}
public void setAdmin_id(int admin_id) {
	this.admin_id = admin_id;
}
public int getRole_id() {
	return role_id;
}
public AdminRoleKey(int admin_id, int role_id) {
	super();
	this.admin_id = admin_id;
	this.role_id = role_id;
}
public void setRole_id(int role_id) {
	this.role_id = role_id;
}
@Override
public int hashCode() {
	final int PRIME = 31;
	int result = 1;
	result = PRIME * result + admin_id;
	result = PRIME * result + role_id;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	final AdminRoleKey other = (AdminRoleKey) obj;
	if (admin_id != other.admin_id)
		return false;
	if (role_id != other.role_id)
		return false;
	return true;
}
}
