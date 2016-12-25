package com.netctoss.action.serviceInfo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import entity.ServerStatus;

import Util.Common;
import Util.PropertiesUtils;

@Repository
@Scope("prototype")
public class ServiceInfoAction {
	Map<String, Object> dataMap=null;
	private String key="";
	private String value="";
	public String warnInfo() throws Exception {
		dataMap=null;
		dataMap= new HashMap<String, Object>();
		ServerStatus status = Common.getServerStatus();
		String cpuUsage = status.getCpuUsage();
		long FreeMem = status.getFreeMem();
		long useMem = status.getUsedMem();
		long TotalMem = status.getTotalMem();
		String serverUsage = Common.fromUsage(useMem, TotalMem);
		dataMap.put("cpuUsage", cpuUsage);
		dataMap.put("FreeMem", FreeMem);
		dataMap.put("TotalMem", TotalMem);
		dataMap.put("serverUsage", serverUsage);
		long JvmFreeMem = status.getJvmFreeMem();
		long JvmTotalMem = status.getJvmTotalMem();
		String JvmUsage = Common.fromUsage(JvmTotalMem - JvmFreeMem, JvmTotalMem);
		dataMap.put("JvmFreeMem", JvmFreeMem);
		dataMap.put("JvmTotalMem", JvmTotalMem);
		dataMap.put("JvmUsage", JvmUsage);
		dataMap.put("cpu", PropertiesUtils.findPropertiesKey("cpu"));
		dataMap.put("jvm", PropertiesUtils.findPropertiesKey("jvm"));
		dataMap.put("ram", PropertiesUtils.findPropertiesKey("ram"));
		dataMap.put("toEmail", PropertiesUtils.findPropertiesKey("toEmail"));
		dataMap.put("diskInfos", status.getDiskInfos());
		return "success";
	}
	
	public String modifySer() throws Exception{
		dataMap=null;
		dataMap= new HashMap<String, Object>();
    	try {
		// 从输入流中读取属性列表（键和元素对）
    		System.out.println("key :"+key+" value :"+value);
    		PropertiesUtils.modifyProperties(key, value);
    		key=null;
    		value=null;
		} catch (Exception e) {
			dataMap.put("flag", false);
		}
    	dataMap.put("flag", true);
		return "success";
    }
	public static void main(String[] args) {
		// ServerInfoController.getServerBaseInfo(new ServerStatus());
	}
	
    public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
