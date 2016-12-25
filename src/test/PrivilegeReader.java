package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import pojo.Privilege;

public class PrivilegeReader {

	// 用于存储privileges.xml的权限操作信息
	private static List<Privilege> privileges = new ArrayList<Privilege>();

	static {
		InputStream xml = PrivilegeReader.class.getClassLoader()
				.getResourceAsStream("test/privileges.xml");
		privileges = toModuleList(xml);
	}

	/**
	 * 返回XML中所有权限数据
	 * 
	 * @return
	 */
	public static List<Privilege> getPrivileges() {
		return privileges;
	}

	/**
	 * 根据权限ID查询模块名称
	 * 
	 * @param id
	 * @return
	 */
	public static String getPrivilegeNameById(String id) {
		List<Privilege> privileges = getPrivileges();
		for (Privilege privilege : privileges) {
			if (privilege.getId().equals(id)) {
				return privilege.getName();
			}
		}
		return null;
	}

	public static List<String> getPrivilegeURlsById(String id) {
		List<Privilege> privileges = getPrivileges();
		for (Privilege privilege : privileges) {
			System.out.println(privilege.getId() + ":" + id);
			if (privilege.getId().equals(id)) {
				return privilege.getUrls();
			}
		}
		return null;
	}

	/**
	 * 解析privileges.xml文件
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static List<Privilege> toModuleList(InputStream xml) {
		List<Privilege> modules = new ArrayList<Privilege>();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(xml);
			Element root = doc.getRootElement();
			List<Element> moduleElements = root.elements("privilege");
			for (Element moduleElement : moduleElements) {
				Privilege module = new Privilege();
				module.setId(moduleElement.attributeValue("id"));
				module.setName(moduleElement.elementText("name"));
				Element urlElement = moduleElement.element("urls");
				List<Element> urlElements = urlElement.elements();
				List<String> urls = new ArrayList<String>();
				for (Element element : urlElements) {
					if (null != element && "" != element.getText()) {
						urls.add(element.getText());
					}
				}
				module.setUrls(urls);
				modules.add(module);
			}

			return modules;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("解析权限文件失败！", e);
		}
	}

	public static void addPrivilege(Privilege privilege)
			throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(
				"privileges.xml"));
		List list = document.selectNodes("//privileges");
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Element privileges = (Element) iter.next();
			Element newprivilege = privileges.addElement("privilege");
			newprivilege.addAttribute("id", privilege.getId());
			Element nameElm = newprivilege.addElement("name");
			nameElm.setText(privilege.getName());
			if (null != privilege.getUrls() && privilege.getUrls().size() > 0) {
				Element urls = newprivilege.addElement("urls");
				for (String obj : privilege.getUrls()) {
					if (!"".equals(obj) || null != obj) {
						Element url = urls.addElement("url");
						url.setText(obj);
					}
				}
			}else{
				Element urls = newprivilege.addElement("urls");
				Element url = urls.addElement("url");
				url.setText("");
			}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(
				new FileOutputStream(
						new File(
								"privileges.xml")),
				format);
		writer.write(document);
		writer.close();
	}

	public static void modPrivilege(Privilege privilege) throws Exception {
		delprivilege(privilege.getId());
		addPrivilege(privilege);
	}

	public static void delprivilege(String id)throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("E:\\Study\\tarenaspace\\netctoss\\src\\test\\privileges.xml"));
		List list = document.selectNodes("//privileges");
		Iterator<Element> oldprivilege = list.iterator();
		while(oldprivilege.hasNext()){
			Element oldprivilegesEle = oldprivilege.next();
			Iterator oldprivilegeElms = oldprivilegesEle.elementIterator("privilege"); 
			while(oldprivilegeElms.hasNext()){
				Element privilegeEle = (Element)oldprivilegeElms.next();
				String tid = privilegeEle.attributeValue("id");
				if(id.equals(tid)){
					oldprivilegesEle.remove(privilegeEle);
				}
			}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileOutputStream(new File("E:\\Study\\tarenaspace\\netctoss\\src\\test\\privileges.xml")),format);
		writer.write(document);
		writer.close();
	}
	
	private void parseXmlStr() throws DocumentException{
		String str = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                   " <privileges> "+
	                   "<privilege id=\"1\">"+ 
	                   "<name>角色管理</name>  "+
		                   "<urls> "+
			                   "<url>/role/toRole</url>  "+
			                   "<url>/role/toAddRole</url>  "+
			                   "<url>/role/addRole</url>  "+
			                   "<url>/role/toUpdateRole</url>  "+
			                   "<url>/role/updateAction</url>  "+
			                   "<url>/role/deleterole</url> "+
		                   "</urls>"+ 
	                   "</privilege> " +
                   "</privileges> ";
		
		Document doc = DocumentHelper.parseText(str);
		//List<Element> e = doc.selectNodes("//privileges/privilege/name");
		List<Element> e = doc.selectNodes("//privileges/privilege/name");
		System.out.println(e.get(0).getText());
		
	}
	
	public static void main(String[] args) {
		PrivilegeReader privilege = new PrivilegeReader();
		try {
			privilege.parseXmlStr();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List<Privilege> list = PrivilegeReader.getPrivileges();
		for (Privilege p : list) {
			System.out.println(p.getId() + " " + p.getName());
		}
	}
}
