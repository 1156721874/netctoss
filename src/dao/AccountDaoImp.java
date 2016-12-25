package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

import pojo.Account;

@Repository
public class AccountDaoImp extends HibernateDaoSupport implements IAccountDao {

	@Resource
	public void setMySessionFactory(SessionFactory sf) {
		super.setSessionFactory(sf);
	}

	public boolean addAccount(Account a) throws DAOException {
		try {
			System.out.println("addAccount  " + a.getRecommender_id());
			if (a.getRecommender_id() == 0 || a.getRecommender_id() == null) {
				a.setRecommender_id(null);
			}
			a.setStatus("0");
			a.setCreate_date(new Date(System.currentTimeMillis()));
			getHibernateTemplate().save(a);
			return true;
		} catch (DataAccessException e) {
			throw new DAOException("数据库访问错误", e);
		}
	}

	public boolean deleteById(int id) throws DAOException {
		try {
			Account a = (Account) getHibernateTemplate()
					.load(Account.class, id);
			getHibernateTemplate().delete(a);
			return true;
		} catch (DataAccessException e) {
			throw new DAOException("数据库访问错误", e);
		}
	}

	public void deleteServicesById(int accountid) throws DAOException {
		try {
			String hql = "delete from Service s where s.account.id=?";
			Object[] params = { accountid };
			getHibernateTemplate().delete(hql, params);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public Account findAccount(int id) throws DAOException {
		try {
			Account account = (Account) getHibernateTemplate().load(
					Account.class, id);
			return account;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public Account findAccountByIdCard(String idcard) throws DAOException {
		try {
			String hql = "from Account where idcard_no=?";
			Object[] params = { idcard };
			List<Account> list = getHibernateTemplate().find(hql, params);
			if (!list.isEmpty()) {
				System.out.println(list.size());
				return list.get(0);
			}
			return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public boolean findAccountByRealName(String realname) throws DAOException {
		try {
			String hql = "from Account where real_name=?";
			Object[] params = { realname };
			List list = getHibernateTemplate().find(hql, params);
			if (list.isEmpty()) {
				return true;
			}
			return false;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public List<Account> findByCondition(String idcard_no, String login_name,
			String real_name, String status, final int page, final int pagesize)
			throws DAOException {
		System.out.println(idcard_no + " " + login_name + " " + real_name + " "
				+ status + " " + page + " " + pagesize);
		final List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from Account where 1=1 ");
		if (idcard_no != null && idcard_no.length() > 0) {
			sb.append("and idcard_no = ? ");
			params.add(idcard_no);
		}
		if (real_name != null && real_name.length() > 0) {
			sb.append("and real_name=? ");
			params.add(real_name);
		}
		if (login_name != null && login_name.length() > 0) {
			sb.append("and login_name=? ");
			params.add(login_name);
		}
		if (status != null && status.length() > 0 && !status.equals("-1")) {
			sb.append("and status=? ");
			params.add(status);
		}
		/*
		 * Query q = s.createQuery(hql); q.setFirstResult((page-1)*pagesize);
		 * q.setMaxResults(pagesize); for(int i=0;i<params.size();i++){
		 * q.setParameter(i, params.get(i)); } return (List<Account>)q.list();
		 */
		final String hql = sb.toString();
		try {
			List<Account> list =(List)getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					Query q = s.createQuery(hql);
					q.setFirstResult((page - 1) * pagesize);
					q.setMaxResults(pagesize);
					for (int i = 0; i < params.size(); i++) {
						q.setParameter(i, params.get(i));
					}
					return (List<Account>) q.list();
				}
			});
			if(!list.isEmpty()){
				return list;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int findByIdCard(String idcard) throws DAOException {
		try {
			String hql = "from Account where idcard_no=?";
			Object[] params = { idcard };
			List<Account> list = getHibernateTemplate().find(hql, params);
			return list.get(0).getId();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public String findIdCardNoById(int id) throws DAOException {
		String hql = "from Account where id=?";
		try {
			Object[] params = { id };
			List<Account> list = getHibernateTemplate().find(hql, params);
			return list.get(0).getIdcard_no();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public String findPwdById(int id) throws DAOException {
		try {
			Account a = (Account) getHibernateTemplate()
					.load(Account.class, id);
			return a.getLogin_passwd();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public void pauseAccount(int id) throws DAOException {
		try {
			Account a = (Account) getHibernateTemplate()
					.load(Account.class, id);
			a.setStatus("1");
			a.setPause_date(new Date(System.currentTimeMillis()));
			getHibernateTemplate().update(a);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public void pauseService(int id) throws DAOException {
		String hql = "update Service set status=1,create_date=? where status<>2 and  account_id=?";
		try {
			Object[] params = { new Date(System.currentTimeMillis()), id };
			getHibernateTemplate().update(hql, params);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}

	}

	public void startAccount(int id) throws DAOException {
		try {
			Account a = (Account) getHibernateTemplate()
					.load(Account.class, id);
			a.setStatus("0");
			a.setPause_date(null);
			getHibernateTemplate().update(a);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public int totalPages(String idcard_no, String login_name,
			String real_name, String status, int pagesize) throws DAOException {
		List<String> params = new ArrayList<String>();
		String hql = createFindTotalPagesSQL(idcard_no, login_name, real_name,
				status, params);
		Long total = null;
		try {
			List list = getHibernateTemplate().find(hql,params.toArray());
			if(!list.isEmpty()){
				total=(Long) list.get(0);
			}
			return (int) ((long) total % pagesize == 0 ? total / pagesize
					: (total / pagesize) + 1);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return (int) ((long) total % pagesize == 0 ? total / pagesize
				: (total / pagesize) + 1);
	}

	public boolean updateAccount(Account a) throws DAOException {
		try {
			System.out.println("update action is :" + a.getRecommender_id());
			if (a.getRecommender_id() == 0) {
				a.setRecommender_id(null);
			}
			getHibernateTemplate().update(a);
			System.out.println("updateAccount ...");
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	public boolean updatePasswordById(String pwd, int id) throws DAOException {
		try {
			Account a = (Account) getHibernateTemplate()
					.load(Account.class, id);
			a.setLogin_passwd(pwd);
			getHibernateTemplate().update(a);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("数据库访问错误", e);
		}
	}

	private String createFindTotalPagesSQL(String idcard_no, String login_name,
			String real_name, String status, List<String> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from Account  where 1=1 ");
		if (idcard_no != null && idcard_no.length() > 0) {
			sb.append("and idcard_no = ? ");
			params.add(idcard_no);
		}
		if (real_name != null && real_name.length() > 0) {
			sb.append("and real_name=? ");
			params.add(real_name);
		}
		if (login_name != null && login_name.length() > 0) {
			sb.append("and login_name=? ");
			params.add(login_name);
		}
		if (status != null && status.length() > 0 && !status.equals("-1")) {
			sb.append("and status=? ");
			params.add(status);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		AccountDaoImp dao = new AccountDaoImp();
		try {
			List l = dao.findByCondition(null, null, null, "0", 1, 3);
			System.out.println(l.toString());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * public List<Account> findByCondition(String idcard_no, String
	 * login_name, String real_name, String status, int page, int pagesize)
	 * throws DAOException { List<Object> params = new ArrayList<Object>();
	 * String sql = createFindSql(idcard_no, login_name, real_name, status,
	 * params, page, pagesize); // alt+shift+m 把己行代码封装为方法 Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null; List<Account> list =
	 * new ArrayList<Account>(); try { con = DaoUtil.getConnection(); ps =
	 * con.prepareStatement(sql); for (int i = 0; i < params.size(); i++) {
	 * ps.setObject(i + 1, params.get(i)); } rs = ps.executeQuery(); while
	 * (rs.next()) { Account a = createAccount(rs); list.add(a); } } catch
	 * (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("查询全部的帐务信息时发生错误", e); } finally { try { DaoUtil.close(); }
	 * catch (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("查询全部的帐务信息时关闭数据库发生错误", e); } } return list; }
	 * 
	 * private Account createAccount(ResultSet rs) throws SQLException { Account
	 * a = new Account(); a.setId(rs.getInt("id"));
	 * a.setRecommender_id(rs.getInt("recommender_id"));
	 * a.setLogin_name(rs.getString("login_name"));
	 * a.setLogin_passwd(rs.getString("login_passwd"));
	 * a.setStatus(rs.getString("status"));
	 * a.setCreate_date(rs.getDate("create_date"));
	 * a.setPause_date(rs.getDate("pause_date"));
	 * a.setClose_date(rs.getDate("close_date"));
	 * a.setReal_name(rs.getString("real_name"));
	 * a.setIdcard_no(rs.getString("idcard_no"));
	 * a.setBirthdate(rs.getDate("birthdate"));
	 * a.setGender(rs.getString("gender"));
	 * a.setOccupation(rs.getString("occupation"));
	 * a.setTelephone(rs.getString("telephone"));
	 * a.setEmail(rs.getString("email"));
	 * a.setMailaddress(rs.getString("mailaddress"));
	 * a.setZipcode(rs.getString("zipcode")); a.setQq(rs.getString("qq"));
	 * a.setLast_login_time(rs.getDate("last_login_time"));
	 * a.setLast_login_ip(rs.getString("last_login_ip")); return a; }
	 * 
	 * private String createFindSql(String idcard_no, String login_name, String
	 * real_name, String status, List<Object> params, int page, int pagesize)
	 * {// alt+shift+j添加注释
	 * 
	 * StringBuffer sb = new StringBuffer(); sb.append("select a.*,rownum n from
	 * waccount a where 1=1 "); if (idcard_no != null && idcard_no.length() > 0) {
	 * sb.append("and idcard_no = ? "); params.add(idcard_no); } if (real_name !=
	 * null && real_name.length() > 0) { sb.append("and real_name=? ");
	 * params.add(real_name); } if (login_name != null && login_name.length() >
	 * 0) { sb.append("and login_name=? "); params.add(login_name); } if (status !=
	 * null && status.length() > 0 && !status.equals("-1")) { sb.append("and
	 * status=? "); params.add(status); } sb.append("and rownum <?"); int min =
	 * page * pagesize + 1; params.add(min); String sql = "select * from (" +
	 * sb.toString() + ") where n>?"; int max = (page - 1) * pagesize;
	 * params.add(max); return sql; }
	 * 
	 * public int totalPages(String idcard_no, String login_name, String
	 * real_name, String status, int pagesize) throws DAOException { Connection
	 * con = null; PreparedStatement ps = null; ResultSet rs = null; int
	 * totalpage = 1; try { con = DaoUtil.getConnection(); List<String> params =
	 * new ArrayList<String>(); String sql = createFindTotalPagesSQL(idcard_no,
	 * login_name, real_name, status, params); ps = con.prepareStatement(sql);
	 * for (int i = 0; i < params.size(); i++) { ps.setObject(i + 1,
	 * params.get(i)); } rs = ps.executeQuery(); if (rs.next()) { int count =
	 * rs.getInt(1); totalpage = count % pagesize == 0 ? count / pagesize :
	 * (count / pagesize) + 1; } } catch (SQLException e) { e.printStackTrace();
	 * throw new DAOException("读取总页数失败", e); } finally { try { DaoUtil.close(); }
	 * catch (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("关闭数据库失败", e); } } return totalpage; }
	 */

	/*
	 * public boolean deleteById(int id) throws DAOException { Connection con =
	 * null; PreparedStatement ps = null; System.out.println("delete id: " +
	 * id); try { con = DaoUtil.getConnection(); ps = con
	 * .prepareStatement("update waccount set status=2 where id=?");
	 * ps.setInt(1, id); int a = ps.executeUpdate(); System.out.println(a +
	 * "hhhh"); if (a > 0) { return true; } } catch (SQLException e) {
	 * e.printStackTrace(); } finally { try { DaoUtil.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } return false; }
	 * 
	 * public void deleteServicesById(int accountid) { Connection con = null;
	 * PreparedStatement ps = null; try { con = DaoUtil.getConnection(); ps =
	 * con .prepareStatement("update wservice set status=2 ,close_date = sysdate
	 * where account_id=?"); ps.setInt(1, accountid); ps.executeUpdate(); }
	 * catch (SQLException e) { e.printStackTrace();
	 *  } finally { try { DaoUtil.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } } }
	 *  // 根据ID将此数据的状态为0 public void startAccount(int id) throws DAOException {
	 * Connection con = null; PreparedStatement ps = null; String sql = "update
	 * waccount set status=0,pause_date=null where id=?"; try { con =
	 * DaoUtil.getConnection(); ps = con.prepareStatement(sql); ps.setInt(1,
	 * id); ps.executeUpdate(); } catch (SQLException e) { e.printStackTrace();
	 * throw new DAOException("更新状态为开通失败", e); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * public void pauseAccount(int id) throws DAOException { Connection con =
	 * null; PreparedStatement ps = null; String sql = "update waccount set
	 * status=1,create_date=sysdate where id=?"; try { con =
	 * DaoUtil.getConnection(); ps = con.prepareStatement(sql); ps.setInt(1,
	 * id); ps.executeUpdate(); } catch (SQLException e) { e.printStackTrace();
	 * throw new DAOException("更新状态为暂停失败", e); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * public int findByIdCard(String idcard) throws DAOException { Connection
	 * con = null; PreparedStatement ps = null; ResultSet rs = null; try { con =
	 * DaoUtil.getConnection(); ps = con .prepareStatement("select * from
	 * waccount where idcard_no=?"); ps.setString(1, idcard); rs =
	 * ps.executeQuery(); if (rs.next()) { return rs.getInt("id"); } return 0; }
	 * catch (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("验证身份证号码失败", e); } finally { try { DaoUtil.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * public boolean addAccount(Account a) throws DAOException { if (a == null) {
	 * return false; } Connection con = null; PreparedStatement ps = null; try {
	 * con = DaoUtil.getConnection(); ps = con .prepareStatement("insert into
	 * waccount
	 * values(windex.nextval+7,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	 * System.out.println("a.getRecommender_id() :" + a.getRecommender_id()); if
	 * (a.getRecommender_id() == 0) { ps.setObject(1, null); } else {
	 * ps.setInt(1, a.getRecommender_id()); } ps.setString(2,
	 * a.getLogin_name()); ps.setString(3, a.getLogin_passwd()); ps.setString(4,
	 * "0"); ps.setDate(5, new Date(System.currentTimeMillis())); ps.setDate(6,
	 * (Date) a.getPause_date()); ps.setDate(7, (Date) a.getClose_date());
	 * ps.setString(8, a.getReal_name()); ps.setString(9, a.getIdcard_no());
	 * ps.setDate(10, a.getBirthdate()); ps.setString(11, a.getGender());
	 * ps.setString(12, a.getOccupation()); ps.setString(13, a.getTelephone());
	 * ps.setString(14, a.getEmail()); ps.setString(15, a.getMailaddress());
	 * ps.setString(16, a.getZipcode()); ps.setString(17, a.getQq());
	 * ps.setDate(18, (Date) a.getLast_login_time()); ps.setString(19,
	 * a.getLast_login_ip()); if (ps.executeUpdate() > 0) { return true; } else {
	 * return false; } } catch (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("插入Account数据时发生错误", e); } finally { try { DaoUtil.close(); }
	 * catch (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("关闭数据库时失败", e); } } }
	 * 
	 * public Account findAccount(int id) throws DAOException { Connection con =
	 * null; PreparedStatement ps = null; ResultSet rs = null; Account a = null;
	 * try { con = DaoUtil.getConnection(); ps = con.prepareStatement("select *
	 * from waccount where id=?"); ps.setInt(1, id); rs = ps.executeQuery();
	 * while (rs.next()) { a = createAccount(rs); } return a; } catch
	 * (SQLException e) { e.printStackTrace(); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); throw
	 * new DAOException("关闭数据库时失败", e); } } return a; }
	 * 
	 * public String findIdCardNoById(int id) throws DAOException { Connection
	 * con = null; PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * try { con = DaoUtil.getConnection(); ps = con .prepareStatement("select
	 * idcard_no from waccount where id=?"); ps.setInt(1, id); rs =
	 * ps.executeQuery(); if (rs.next()) { return rs.getString("idcard_no"); } }
	 * catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); throw
	 * new DAOException("关闭数据库时失败", e); } } return null; }
	 * 
	 * public boolean updateAccount(Account a) throws DAOException { if (a ==
	 * null) { return false; } Connection con = null; PreparedStatement ps =
	 * null;
	 * 
	 * try { con = DaoUtil.getConnection(); ps = con .prepareStatement("update
	 * waccount set recommender_id=?, real_name=?,telephone=?, " +
	 * "email=?,occupation=?,gender=?,mailaddress=?,zipcode=?,qq=? where id=?");
	 * System.out.println("update ing ..."); System.out.println("account id :" +
	 * a.getId()); if (a.getRecommender_id() == 0) { ps.setObject(1, null); }
	 * else { ps.setInt(1, a.getRecommender_id()); } ps.setString(2,
	 * a.getReal_name()); ps.setString(3, a.getTelephone()); ps.setString(4,
	 * a.getEmail()); ps.setString(5, a.getOccupation()); ps.setString(6,
	 * a.getGender()); ps.setString(7, a.getMailaddress()); ps.setString(8,
	 * a.getZipcode()); ps.setString(9, a.getQq()); ps.setInt(10, a.getId()); if
	 * (ps.executeUpdate() > 0) { return true; } else { return false; } } catch
	 * (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("插入Account数据时发生错误", e); } finally { try { DaoUtil.close(); }
	 * catch (SQLException e) { e.printStackTrace(); throw new
	 * DAOException("关闭数据库时失败", e); } } }
	 * 
	 * public String findPwdById(int id) throws DAOException { Connection con =
	 * null; PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * try { con = DaoUtil.getConnection(); ps = con .prepareStatement("select
	 * login_passwd from waccount where id=?"); ps.setInt(1, id); rs =
	 * ps.executeQuery(); if (rs.next()) { return rs.getString("login_passwd"); } }
	 * catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); throw
	 * new DAOException("关闭数据库时失败", e); } } return null; }
	 * 
	 * public boolean updatePasswordById(String pwd, int id) throws DAOException {
	 * 
	 * Connection con = null; PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * try { con = DaoUtil.getConnection(); ps = con .prepareStatement("update
	 * waccount set login_passwd=? where id=?"); ps.setString(1, pwd);
	 * ps.setInt(2, id); if (ps.executeUpdate() > 0) { return true; } } catch
	 * (SQLException e) { e.printStackTrace(); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); throw
	 * new DAOException("关闭数据库时失败", e); } } return false; }
	 * 
	 * public boolean findAccountByRealName(String realname) throws DAOException {
	 * Connection con = null; PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * try { con = DaoUtil.getConnection(); ps = con .prepareStatement("select
	 * real_name from waccount where real_name=?"); ps.setString(1, realname);
	 * rs = ps.executeQuery(); if (rs.next() &&
	 * rs.getString("real_name").equals(realname)) { return true; } } catch
	 * (SQLException e) { e.printStackTrace(); } finally { try {
	 * DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); throw
	 * new DAOException("关闭数据库时失败", e); } } return false; }
	 * 
	 * public Account findAccountByIdCard(String idcard) throws DAOException {
	 * Connection con = null; PreparedStatement ps = null; ResultSet rs = null;
	 * Account a = null; try { con = DaoUtil.getConnection(); ps = con
	 * .prepareStatement("select * from waccount where idcard_no=?");
	 * ps.setString(1, idcard); rs = ps.executeQuery(); while (rs.next()) { a =
	 * this.createAccount(rs); } return a; } catch (SQLException e) {
	 * e.printStackTrace(); } return null; }
	 * 
	 * public void pauseService(int id) throws DAOException { Connection con =
	 * null; PreparedStatement ps = null; String sql = "update wservice set
	 * status=1,create_date=sysdate where status<>2 and account_id=?"; try {
	 * con = DaoUtil.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setInt(1, id); ps.executeUpdate(); } catch (SQLException e) {
	 * e.printStackTrace(); throw new DAOException("更新业务状态为暂停失败", e); } finally {
	 * try { DaoUtil.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 */

}
