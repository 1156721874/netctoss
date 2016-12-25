package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.AdminDaoImpl;
import dao.DAOException;

public class testAdmin {
	@Test
	public void test1() throws DAOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminDaoImpl dao = (AdminDaoImpl)ac.getBean("adminDaoImpl");
		List list = (List) dao.findById(781);
		System.out.println(list.size());
	}
}
