package dao;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pojo.Admin;


@Repository
public class AdminHibernateImp extends HibernateDaoSupport implements AdminHibernateDao {

	@Resource
	public void setsessionFactory(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	public void save(Admin a) throws DAOException {
		try{
			a.setEnrollDate(new Date());
			System.out.println("AdminHibernateImp...........");
		getHibernateTemplate().save(a);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误",e);
		}
	}

}
