package com.netctoss.action.login;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Admin;
import Util.PowerUtil;

import com.netctoss.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

import dao.DAOException;

@Repository
@Scope("prototype")
public class LoginAction extends BaseAction {

	private String errorMsg;
	private Admin admin;
	private String validateCode;
	@Resource
	private PowerUtil powerutil;
	public String execute(){
		//Admin a = null;
		Set<String>  urls = null;
		try {
			urls= powerutil.findPower(admin.getAdminCode(), admin.getPassword());
		} catch (DAOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
			return "error";
		}
		if(urls==null){
			System.out.println("+++++");
			errorMsg = "帐号或密码错误";
			if(!validateCode.equalsIgnoreCase((String) session.get("imageCode"))){
				errorMsg+=", 验证码错误";
			}
			return "error";
		}else{
		//session.put("admin", a);
			//提取该用户的权限URL 存入session
			try {
				Set <String> seturl = powerutil.findPower(admin.getAdminCode(), admin.getPassword());
				session.put("urls", seturl);
				session.put("admin", admin);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			return "success";
		}
	}
	
	public String quit(){
	    session.put("admin", null);
	    session.put("urls", null);
		return "success";
	}
	
	public Admin getAdmin() {
		return admin;
	}
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getValidateCode() {
		return validateCode;
	}
	
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
}
