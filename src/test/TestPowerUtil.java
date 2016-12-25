package test;

import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Util.PowerUtil;
import dao.DAOException;

public class TestPowerUtil {
	@Test
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		PowerUtil dao = (PowerUtil) ac.getBean("powerUtil");
		try {
			Set<String> set = dao.findPower("BOSS", "wang");
			for (String string : set) {
				System.out.println(string);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/*
 * 报错：
 * ERROR - failed to lazily initialize a collection of role:
 * pojo.Admin.roles, no session or session was closed
 *  在AdminInfo.hbm.xml 的set 标签里边加lazy="false"
 *  或者HQL语句修改为FETCH形式：
 *  from Admin a join fetch a.roles where a.adminCode=? and a.password=?
 */

