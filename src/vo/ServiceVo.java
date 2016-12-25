package vo;

import java.io.Serializable;

import pojo.Service;

public class ServiceVo extends Service implements Serializable {
private String idCardNo;
private String realName;
private String costName;
private String descr;
public String getCostName() {
	return costName;
}
public void setCostName(String costName) {
	this.costName = costName;
}
public String getDescr() {
	return descr;
}
public void setDescr(String descr) {
	this.descr = descr;
}
public String getIdCardNo() {
	return idCardNo;
}
public void setIdCardNo(String idCardNo) {
	this.idCardNo = idCardNo;
}
public String getRealName() {
	return realName;
}
public void setRealName(String realName) {
	this.realName = realName;
}
}
