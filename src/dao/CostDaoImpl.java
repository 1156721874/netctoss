package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pojo.Cost;

@Repository
public class CostDaoImpl extends HibernateDaoSupport implements ICostDao {
	@Resource
	public void setMysessionFactory(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	public boolean addCost(Cost cost) throws DAOException {
		try {
			cost.setStatus("1");
			cost.setCreateTime(new Date());
			getHibernateTemplate().save(cost);
			return true;
		} catch (DataAccessException e) {
			throw new DAOException("数据库访问错误", e);
		}
	}

	public void deleteById(int id) throws DAOException {
		try {
			Cost cost = new Cost();
			cost.setId(id);
			getHibernateTemplate().delete(cost);
		} catch (DataAccessException e) {
			throw new DAOException("数据库访问错误", e);
		}

	}

	public List<Cost> findAll(final int page, final int perpage) throws DAOException {
		try {
			List list = (List)getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(Session s) throws HibernateException, SQLException {
					Query q = s.createQuery("from Cost");
					q.setFirstResult((page-1)*perpage);
					q.setMaxResults(perpage);
					return q.list();
				}
			}		
			);
			return list;
		} catch (DataAccessException e) {
			throw new DAOException("数据库访问错误", e);
		}
	}

	public List<Cost> findAll() throws DAOException {
		try {
			return getHibernateTemplate().find("from Cost");
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		} 
	}

	public Cost findById(Integer id) throws DAOException {
		try{
			Cost c = (Cost)getHibernateTemplate().get(Cost.class, id);
			return c;
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public Cost findByName(String name) throws DAOException {
		try {
String hql = "from Cost where name=?";
Object []params = {name};
 List<Cost> list = (List)getHibernateTemplate().find(hql,params);
if(!list.isEmpty()){
	return list.get(0);
}
return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public boolean startCost(int id) throws DAOException {
		try{
			Cost c = (Cost) getHibernateTemplate().get(Cost.class, id);
			c.setStatus("0");
			getHibernateTemplate().update(c);
			return true;
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public int totalPages(int perpage) throws DAOException {
		String hql = "select count(*) from Cost";
		try{
			List list = getHibernateTemplate().find(hql);
			int pages = ((Long)list.get(0)).intValue();
			return (int)((pages%perpage==0)?pages/perpage:(pages/perpage)+1);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public boolean updateCost(Cost cost) throws DAOException {
		try{
			getHibernateTemplate().update(cost);
			return true;
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}
}
