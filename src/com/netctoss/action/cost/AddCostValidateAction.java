package com.netctoss.action.cost;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Cost;
import dao.DAOException;
import dao.ICostDao;

@Repository("AddCostValidateAction")
@Scope("prototype")
public class AddCostValidateAction {
	//输入属性
private String name;
//输出属性
private boolean pass;
@Resource
private ICostDao costdao;
public String execute(){
//调用DAO，根据结果查询资费数据
	
	//判断结果，为空验证通过，否则不通过
	Cost c =null ;
	try {
		System.out.println("name: "+name);
		c=costdao.findByName(name);
	} catch (DAOException e) {
		e.printStackTrace();
		return "error";
	}
	if(c == null){
		pass = true;
	}else{
		pass = false;
	}
	return "success";
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public boolean isPass() {
	return pass;
}
public void setPass(boolean pass) {
	this.pass = pass;
}


public ICostDao getCostdao() {
	return costdao;
}


public void setCostdao(ICostDao costdao) {
	this.costdao = costdao;
}
}
