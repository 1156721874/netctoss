package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pojo.Account;
import pojo.Cost;
import pojo.Service;
import vo.ServiceVo;

@Repository
public class ServiceDaoImp extends HibernateDaoSupport implements IService {

	@Resource
	public void setmySessionFactory(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	public List<ServiceVo> findByCondition(String osUserName, String unixHost,
			String idCardNo, String status, final int page,final int pagesize)
			throws DAOException {
			final List<Object> params = new ArrayList<Object>();
			final 	String hql = createFindSql(osUserName, unixHost, idCardNo, status,
			params, page, pagesize); // alt+shift+m 把己行代码封装为方法
			List<ServiceVo> listvo = new ArrayList<ServiceVo>();
		try {
				List<Object[]> list  = (List)	getHibernateTemplate().execute(
					new HibernateCallback(){
						public Object doInHibernate(Session s) throws HibernateException, SQLException {
							Query q = s.createQuery(hql);
							q.setFirstResult((page-1)*pagesize);
							q.setMaxResults(pagesize);
							for(int i=0;i<params.size();i++){
								q.setParameter(i, params.get(i));
							}
							return q.list();
						}
					}
			);
		for(Object[] objs:list){
			ServiceVo so = new ServiceVo();
			Service service = (Service)objs[0];
			BeanUtils.copyProperties(service, so);
			so.setIdCardNo(objs[1].toString());
			so.setRealName(objs[2].toString());
			so.setCostName(objs[3].toString());
			so.setDescr(objs[4].toString());
			listvo.add(so);
		}
		return listvo;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("查询全部的帐务信息时发生错误", e);
		}
	}

	private ServiceVo createServiceVo(ResultSet rs) throws SQLException {
		ServiceVo s = new ServiceVo();
		s.setIdCardNo(rs.getString("idcardno"));
		s.setRealName(rs.getString("realname"));
		s.setCostName(rs.getString("costname"));
		s.setDescr(rs.getString("costdescr"));
		s.setId(rs.getInt("id"));
		s.setAccount_id(rs.getInt("account_id"));
		s.setUnix_host(rs.getString("unix_host"));
		s.setOs_username(rs.getString("os_username"));
		s.setLogin_passwd(rs.getString("login_passwd"));
		s.setStatus(rs.getString("status"));
		s.setCreate_date(rs.getDate("create_date"));
		s.setPause_date(rs.getDate("pause_date"));
		s.setClose_date(rs.getDate("close_date"));
		s.setCost_id(rs.getInt("cost_id"));
		return s;
	}

	private String createFindSql(String osUserName, String unixHost,
			String idCardNo, String status, List<Object> params, int page,
			int pagesize) {// alt+shift+j添加注释
		/**
		 * select * from (select s.*,w.idcard_no idcardno,w.real_name
		 * realname,c.name costname,c.descr costdescr,rownum n from wservice s
		 * inner join waccount w on s.account_id=w.id inner join wcost c on c.id =
		 * s.cost_id where rownum<?)where n>?;
		 */
		StringBuffer sb = new StringBuffer();
		sb
				.append("select s,w.idcard_no ,w.real_name ,c.name ,c.descr  from Service s "
						+ " join s.account w  join s.cost c  where 1=1");
		if (osUserName != null && osUserName.length() > 0) {
			sb.append(" and s.os_username = ?");
			params.add(osUserName);
		}
		if (unixHost != null && unixHost.length() > 0) {
			sb.append(" and s.unix_host=? ");
			params.add(unixHost);
		}
		if (idCardNo != null && idCardNo.length() > 0) {
			sb.append(" and w.idcard_no=? ");
			params.add(idCardNo);
		}
		if (status != null && status.length() > 0 && !status.equals("-1")) {
			sb.append(" and s.status=? ");
			params.add(status);
		}
		return sb.toString();
	}

	public int totalPages(String osUserName, String unixHost, String idCardNo,
			String status, int pagesize) throws DAOException {
		int totalpage = 1;
		Session session = super.getSession();
		try {
			List<String> params = new ArrayList<String>();
			String sql = createFindTotalPagesSQL(osUserName, unixHost,
					idCardNo, status, params);
			Query q = session.createQuery(sql);
			for (int i = 0; i < params.size(); i++) {
				q.setParameter(i, params.get(i));
			}
			int  count = ((Long)q.uniqueResult()).intValue();
			totalpage = count % pagesize == 0 ? count / pagesize :(count / pagesize) + 1;
			return totalpage;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("读取总页数失败", e);
		} finally {
			session.close();
		}
	}

	private String createFindTotalPagesSQL(String osUserName, String unixHost,
			String idCardNo, String status, List<String> params) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select count(*) from  Service s ,"
						+ "Account w , Cost c  where s.account.id=w.id and s.cost.id=c.id and 1=1 ");
		if (osUserName != null && osUserName.length() > 0) {
			sb.append(" and s.os_username = ? ");
			params.add(osUserName);
		}
		if (unixHost != null && unixHost.length() > 0) {
			sb.append(" and s.unix_host=? ");
			params.add(unixHost);
		}
		if (idCardNo != null && idCardNo.length() > 0) {
			sb.append(" and w.idcard_no=? ");
			params.add(idCardNo);
		}
		if (status != null && status.length() > 0 && !status.equals("-1")) {
			sb.append(" and s.status=? ");
			params.add(status);
		}
		return sb.toString();
	}

	public boolean addService(Service s) throws DAOException {
		try {
			System.out.println("addService..."+s.getAccount_id());
			Account a = (Account)getHibernateTemplate().load(Account.class, s.getAccount_id());
			Cost c = (Cost)getHibernateTemplate().load(Cost.class, s.getCost_id());
			s.setAccount(a);
			s.setCost(c);
			s.setStatus("0");
			s.setCreate_date(new Date(System.currentTimeMillis()));
			getHibernateTemplate().save(s);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("添加服务失败", e);
		}
	}

	public boolean pauseService(int id) throws DAOException {
		//String hql = "update Service set status='1',create_date=? where id=?";
		try {
			Service s = (Service)getHibernateTemplate().load(Service.class, id);
			s.setStatus("1");
			s.setPause_date(new Date(System.currentTimeMillis()));
			getHibernateTemplate().update(s);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("更新状态为暂停失败", e);
		} 
	}

	public boolean startService(int id) throws DAOException {
		//String hql = "update Service set status='0',pause_date=null where id=?";
		try {
			System.out.println("startService...");
			Service s = (Service)getHibernateTemplate().load(Service.class, id);
			s.setStatus("0");
			s.setCreate_date(new Date(System.currentTimeMillis()));
			s.setPause_date(null);
			getHibernateTemplate().update(s);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("更新状态为开通失败", e);
		}
	}
public 	boolean deleteById(int id) throws DAOException{
	//String hql = "update Service set status='2',close_date=? where id=?";
	try {
	Service s = (Service)getHibernateTemplate().load(Service.class, id);
	s.setStatus("2");
	s.setClose_date(new Date(System.currentTimeMillis()));
	getHibernateTemplate().update(s);
		return true;
	} catch (DataAccessException e) {
		e.printStackTrace();
		throw new DAOException("更新状态为开通失败", e);
	} 
	}
	public String checkAccountStatusIsStart(int id) throws DAOException {
		String hql = "select w.status  from Account w,Service s where s.account.id = w.id and s.id=?";
		try {
			//System.out.println("checkAccountStatusIsStart++++++++");
			Object [] params = {id};
			List list = getHibernateTemplate().find(hql,params);
			return list.get(0).toString();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("核对帐务信息是否暂停时数据库失败", e);
		} 
	}


	public boolean updataService(Service s) throws DAOException {
	//	String hql = "update Service s set s.cost.id = ? where s.id=? ";
		try {
			Service service = (Service)getHibernateTemplate().load(Service.class, s.getId());
			Cost c = (Cost)getHibernateTemplate().load(Cost.class, s.getCost_id());
			service.setCost(c);
			getHibernateTemplate().update(service);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("更新业务信息时数据库失败", e);
		}
	}
	
public ServiceVo 	findServiceById(int id) throws DAOException{
	final String hql = "select s,w.idcard_no ,w.real_name ,c.name ,c.descr  from Service s "
			+ " join s.account w  join s.cost c  where s.id="+id;
	ServiceVo so = new ServiceVo();
	List<Object[]> objs = (List<Object[]>)getHibernateTemplate().execute(new  HibernateCallback() {
		
		@Override
		public Object doInHibernate(Session s) throws HibernateException,
				SQLException {
			Query q = s.createQuery(hql);
			return q.list();
		}
	});
	
	Object[] obj = objs.get(0);
	Service s = (Service)obj[0];
	BeanUtils.copyProperties(s, so);
	so.setIdCardNo(obj[1].toString());
	so.setRealName(obj[2].toString());
	so.setCostName(obj[3].toString());
	so.setDescr(obj[4].toString());
	return so;
} 
	
}
