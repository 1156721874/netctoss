package pojo;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Admin {
	private Integer id; // ID

	private String adminCode;// ADMIN_CODE

	private String password;// PASSWORD
	
	private String newpassword;
	
	private String name;

	private String telephone;

	private String email;

	private Date enrollDate;

	private Integer[] roleids;

	private String rolenames;
	
	private Set<Role> roles = new HashSet<Role>();
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public String getRolenames() {
		StringBuffer mrolenames=new StringBuffer();
		for(Role role :getRoles()){
			mrolenames.append(" "+role.getName());
		}
		//String  a = mrolenames.toString().split(" ")[0];
		return mrolenames.toString();
	}

	public String getAdminCode() {
		return adminCode;
	}

	@Override
	public String toString() {
		return "id :" + id + "name :" + this.name + "roleids :"
				+ Arrays.toString(this.roleids);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public Integer[] getRoleids() {
		return roleids;
	}

	public void setRoleids(Integer[] roleids) {
		this.roleids = roleids;
	}
	
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}


}
