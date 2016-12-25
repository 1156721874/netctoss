package Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

public class HibernateUtil {
	static SessionFactory sf;
	private static ThreadLocal<Session> tl = new ThreadLocal<Session>();
	static {
		Configuration		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static Session getSession() {
		Session s =  tl.get();
		if(s==null){
			s = sf.openSession();
			tl.set(s);
		}
		return s;
	}
	public static void  closeSession(){
		Session session = tl.get();
		tl.set(null);
		if(session != null && session.isOpen()){
			session.close();
		}
	}
}
