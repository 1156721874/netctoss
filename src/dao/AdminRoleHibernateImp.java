package dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pojo.AdminRole;

@Repository
public class AdminRoleHibernateImp extends HibernateDaoSupport implements AdminRoleHibernateDao {

	@Resource
	public void setsessionFactory(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	public void save(AdminRole adminrole) throws DAOException {
		try{
			System.out.println("AdminRoleHibernateImp...........");
		getHibernateTemplate().save(adminrole);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误",e);
		}
	}

}
