package com.netctoss.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public abstract class BaseAction implements SessionAware{
protected Map<String,Object> session;
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
}
