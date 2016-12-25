package Util.mail;

import java.util.Properties;

import Util.Common;
import Util.PropertiesUtils;
import entity.ServerStatus;
import org.springframework.stereotype.Component;
@Component
public class MailTask {
	public void task() throws Exception {
		ServerStatus status = Common.getServerStatus();
		String cpuUsage = status.getCpuUsage();// CPU使用率
		String serverUsage = Common.fromUsage(status.getUsedMem(), status.getTotalMem());// 系统内存使用率
		String JvmUsage = Common.fromUsage(status.getJvmFreeMem(), status.getJvmTotalMem());// 计算ＪＶＭ内存使用率
		Properties prop = PropertiesUtils.getProperties();
		String cpu = prop.getProperty("cpu");
		String jvm = prop.getProperty("jvm");
		String ram = prop.getProperty("ram");
		String email = prop.getProperty("toEmail");
		// 当系统消耗内存大于或等于用户设定的内存时，发送邮件
		String cpubool = "";
		String jvmbool = "";
		String rambool = "";
		String mark = "<font color='red'>";
		boolean cpuflag = false;
		boolean jvmflag = false;
		boolean serverflag = false;
		if (Double.parseDouble(cpuUsage) > Double.parseDouble(cpu)) {
			cpubool = "style=\"color: red;font-weight: 600;\"";
			mark += "CPU当前：" + cpuUsage + "%,超出预设值  " + cpu + "%<br>";
			cpuflag = true;
		}
		if (Double.parseDouble(JvmUsage) > Double.parseDouble(jvm)) {
			jvmbool = "style=\"color: red;font-weight: 600;\"";
			mark += "JVM当前：" + JvmUsage + "%,超出预设值 " + jvm + "%<br>";
			jvmflag = true;
		}
		if (Double.parseDouble(serverUsage) > Double.parseDouble(ram)) {
			rambool = "style=\"color: red;font-weight: 600;\"";
			mark += "内存当前：" + serverUsage + "%,超出预设值  " + ram + "%";
			serverflag = true;
		}
		mark += "</font>";
		// 邮件内容

		String title = "服务器预警提示";
		String centent = "当前时间是：" + Common.fromDateH() + "<br/><br/>" + "<style type=\"text/css\">" + ".common-table{" + "-moz-user-select: none;" + "width:100%;" + "border:0;" + "table-layout : fixed;" + "border-top:1px solid #dedfe1;" + "border-right:1px solid #dedfe1;" + "}" +

		"/*header*/" + ".common-table thead td,.common-table thead th{" + "    height:23px;" + "   background-color:#e4e8ea;" + "   text-align:center;" + "   border-left:1px solid #dedfe1;" + "}" +

		".common-table thead th, .common-table tbody th{" + "padding-left:7px;" + "padding-right:7px;" + "width:15px;" + "text-align:center;" + "}" +

		".common-table tbody td,  .common-table tbody th{" + "    height:25px!important;" + "border-bottom:1px solid #dedfe1;" + "border-left:1px solid #dedfe1;" + "cursor:default;" + "word-break: break-all;" + "-moz-outline-style: none;" + "_padding-right:7px;" + "text-align:center;" + "}</style>"
				+ "<table class=\"common-table\">" + "<thead>" + "<tr>" + "<td width=\"100\">名称</td>" + "<td width=\"100\">参数</td>" + "<td width=\"275\">预警设置</td>" + "</tr>" + "</thead>" + "<tbody id=\"tbody\">" + "<tr " + cpubool + "><td>CPU</td><td style=\"text-align: left;\">当前使用率：" + cpuUsage
				+ "</td><td>使用率超出  " + cpu + "%,,发送邮箱提示 </td></tr>" + "<tr " + rambool + "><td>服务器内存</td><td style=\"text-align: left;\">当前使用率：" + serverUsage + "</td><td>使用率超出  " + ram + "%,发送邮箱提示 </td></tr>" + "<tr " + jvmbool + "><td>JVM内存</td><td style=\"text-align: left;\">当前使用率：" + JvmUsage
				+ "</td><td>使用率超出  " + jvm + "%,,发送邮箱提示 </td></tr>" + "</tbody>" + "</table>";
		if (!Common.isEmpty(cpubool) || !Common.isEmpty(jvmbool) || !Common.isEmpty(rambool)) {
			try {
				if(cpuflag&&jvmflag&&serverflag){
			     MailSenderInfo mailInfo = new MailSenderInfo();    
			     mailInfo.setMailServerHost("smtp.163.com");    
			     mailInfo.setMailServerPort("25");    
			     mailInfo.setValidate(true);    
			     mailInfo.setUserName(PropertiesUtils.findPropertiesKey("fromEmail"));    
			     mailInfo.setPassword(PropertiesUtils.findPropertiesKey("emailPassword"));//您的邮箱密码    
			     mailInfo.setFromAddress(PropertiesUtils.findPropertiesKey("fromEmail"));    
			     mailInfo.setToAddress(PropertiesUtils.findPropertiesKey("toEmail"));    
			     mailInfo.setSubject(title);    
			     mailInfo.setContent(centent);    
			        //这个类主要来发送邮件   
			     SimpleMailSender sms = new SimpleMailSender();   
			         sms.sendHtmlMail(mailInfo);//发送html格式   
				System.err.println("发送邮件！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("发送邮件失败！");
			}
		}
	}
}
