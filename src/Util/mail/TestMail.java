package Util.mail;

import Util.PropertiesUtils;

public class TestMail {
	public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.163.com");    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName(PropertiesUtils.findPropertiesKey("fromEmail"));    
     mailInfo.setPassword(PropertiesUtils.findPropertiesKey("emailPassword"));//您的邮箱密码    
     mailInfo.setFromAddress(PropertiesUtils.findPropertiesKey("fromEmail"));    
     mailInfo.setToAddress(PropertiesUtils.findPropertiesKey("toEmail"));    
     mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");    
     mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//发送文体格式    
         sms.sendHtmlMail(mailInfo);//发送html格式   
   }  
}
/*
 * 最后，给出朋友们几个注意的地方： 
1、使用此代码你可以完成你的javamail的邮件发送功能。三个类缺一不可。 
2、这三个类我打包是用的com.util.mail包，如果不喜欢，你可以自己改，但三个类文件必须在同一个包中 
3、不要使用你刚刚注册过的邮箱在程序中发邮件，如果你的163邮箱是刚注册不久，那你就不要使用“smtp.163.com”。因为你发不出去。刚注册的邮箱是不会给你这种权限的，也就是你不能通过验证。要使用你经常用的邮箱，而且时间比较长的。 
4、另一个问题就是mailInfo.setMailServerHost("smtp.163.com");与mailInfo.setFromAddress("han2000lei@163.com");这两句话。即如果你使用163smtp服务器，那么发送邮件地址就必须用163的邮箱，如果不的话，是不会发送成功的。 
5、关于javamail验证错误的问题，网上的解释有很多，但我看见的只有一个。就是我的第三个类。你只要复制全了代码，我想是不会有问题的。
另外一个示例：
http://haolloyin.blog.51cto.com/1177454/354320
 */
