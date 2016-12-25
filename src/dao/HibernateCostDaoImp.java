package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Cost;
import Util.HibernateUtil;

public class HibernateCostDaoImp implements HibernateCostDao {

	public boolean addCost(Cost cost) throws DAOException {
		Session s = HibernateUtil.getSession();
		try {
			Transaction tx = s.beginTransaction();
			s.save(cost);
			tx.commit();
			return true;
		} catch (Exception e) {
			throw new DAOException("数据库访问错误", e);
		} finally {
			s.close();
		}
	}

	public void deleteById(int id) throws DAOException {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			Cost c = (Cost) s.load(Cost.class, id);
			s.delete(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		} finally {
			s.close();
		}

	}

	public List<Cost> findAll(int page, int perpage) throws DAOException {
		String hql = "from Cost";
		try {
			Session s = HibernateUtil.getSession();
			Query q = s.createQuery(hql);
			q.setFirstResult((page - 1) * perpage);
			q.setMaxResults(perpage);
			return q.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public List<Cost> findAll() throws DAOException {
		String hql = "from Cost";
		Session s = null;
		try {
			 s= HibernateUtil.getSession();
			Query q = s.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		} finally {
			s.close();
		}
	}

	public Cost findById(Integer id) throws DAOException {
		Session s = null;
		try{
			s = HibernateUtil.getSession();
			Transaction tx =  s.beginTransaction();
			Cost c = (Cost) s.get(Cost.class, id);
			tx.commit();
			return c;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}finally{
			s.close();
		}
	}

	public Cost findByName(String name) throws DAOException {
		String hql = "from Cost where name=?";
		Session s = HibernateUtil.getSession();
		try {
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery(hql);
			q.setString(0, name);
			Cost c = (Cost) q.uniqueResult();
			tx.commit();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		} finally {
			s.close();
		}
	}

	public boolean startCost(int id) throws DAOException {
		Session s = null;
		try{
			s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			Cost c = (Cost) s.load(Cost.class, id);
			c.setStatus("0");
			s.update(c);
			tx.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}finally {
			s.close();
		}
	}

	public int totalPages(int perpage) throws DAOException {
		String hql = "select count(*) from Cost";
		Session s = null;
		try{
			s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			Query  q = s.createQuery(hql);
			int pages = (Integer) q.uniqueResult();
			tx.commit();
			return (pages%perpage==0)?pages/perpage:(pages/perpage)+1;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}finally {
			s.close();
		}
	}

	public boolean updateCost(Cost cost) throws DAOException {
		Session s = null;
		try{
			s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			s.update(cost);
			tx.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}finally {
			s.close();
		}
	}

}
