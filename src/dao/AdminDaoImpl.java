package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import Util.MD5Util;

import pojo.Admin;
import pojo.AdminRole;
import pojo.AdminRoleKey;

@Repository
public class AdminDaoImpl extends HibernateDaoSupport implements  IAdminDao {

	@Resource
	public void setMySessionFactory(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	public Admin findByCodeAndPassword(String adminCode, String password)
			throws DAOException {
		//ew0a8n3g92bb89dedb8ed6fb298f8e729c15@4
		
		System.out.println(adminCode +"+-+-+- "+password);
		Admin admin = null;
		password = MD5Util.parseMd5(password);//把带有明码的MD5码解析，返回明码
		String md5pwd = MD5Util.MD5(password);//将明码加密成md5码，向数据库查询
		String hql = "from Admin a join fetch a.roles where a.adminCode=? and a.password=?";
		Object [] params = {adminCode,md5pwd};
		List a = (List)getHibernateTemplate().find(hql,params);
		if(!a.isEmpty()){
			admin =(Admin) a.get(0);
			System.out.println(admin.getAdminCode()+"rrrrrrrrr"+admin.getPassword());
		}
		return admin;
	}
	
	public void moduserpassword(Admin admin)throws DAOException{
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_EAGER); 
			getHibernateTemplate().update(admin);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("更新用户密码数据库访问错误", e);
		}
	}
	
	public void updateuser(Admin admin)throws DAOException{
		try {
			Admin tempadmin = (Admin)getHibernateTemplate().load(Admin.class, admin.getId());
			tempadmin.setName(admin.getName());
			tempadmin.setTelephone(admin.getTelephone());
			tempadmin.setEmail(admin.getEmail());
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_EAGER);  
			getHibernateTemplate().update(tempadmin);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException("更新用户数据库访问错误", e);
		}
	}
	
	
	public Admin findAdminById(String username,String passward ) throws DAOException{
		System.out.println(username +" kkkkkkkkkkkkkkk  "+passward);
		String sql = "select ai from Admin ai,AdminRole ar,Role ri where ar.key.admin_id = ai.id  and ri.id = ar.key.role_id and" +
				" ai.adminCode=? and ai.password=?";
		System.out.println(sql);
		Object [] params = {username,passward};
		List<Admin> admins  = getHibernateTemplate().find(sql,params);
		Admin admin = new Admin();
		if(null!=admins.get(0)){
			admin = admins.get(0);
		}
		return admin;
	}
	
	

	public void addAdmin(final Admin admin) throws DAOException {
		admin.setEnrollDate(new Date(System.currentTimeMillis()))	;
		String password = admin.getPassword();
		System.out.println("dao "+password);
		password = MD5Util.parseMd5(password);//把带有明码的MD5码解析，返回明码
		String md5pwd = MD5Util.MD5(password);//将明码加密成md5码，向数据库查询
		admin.setPassword(md5pwd);
		getHibernateTemplate().save(admin);
		for(Integer roleid:admin.getRoleids()){
			AdminRole ar = new AdminRole();
			ar.setKey(new AdminRoleKey(admin.getId(),roleid));
			getHibernateTemplate().save(ar);
			}
	}
	public List<Admin> findAll(Integer privilegeId,Integer roleId) throws DAOException {
		System.out.println("privilegeId :"+privilegeId+" : "+" roleId "+roleId+"page :"/**+page+"pagesize :"+pagesize*/);
		StringBuffer sql = 
			new StringBuffer(
					" select distinct ai from Admin ai , AdminRole ar ,"+
					" Role ri , RolePrivilege rp "+
					" where " +
					" ar.key.admin_id = ai.id " +
					" and ri.id = ar.key.role_id " +
					" and rp.id.roleid=ri.id and  1=1 ");
		//List<Integer> params = new ArrayList<Integer>();
		if(privilegeId!=null&&privilegeId!=0&&privilegeId!=8){
			sql.append(" and rp.id.privilegeId="+privilegeId);
		//	params.add(privilegeId);
		}
		if(roleId!=null&&roleId!=0){
			sql.append(" and ri.id="+roleId);
		//	params.add(roleId);
		}
		System.out.println(sql);
		List<Admin> admins = getHibernateTemplate().find(sql.toString());
		return 	admins;
	}
	public boolean resetPassword(final String[] pwd) throws DAOException {
		getHibernateTemplate().execute(new HibernateCallback(){
	public Object doInHibernate(Session s) throws HibernateException, SQLException {
		String hql = "update Admin set password=? where id=?";
		for(int i=0;i<pwd.length;i++){
		Query q = s.createQuery(hql);
		q.setParameter(0, "wang");
		q.setParameter(1, Integer.parseInt(pwd[i]));
		q.executeUpdate();
		}
		return null;
	}
});
		return true;
	}

	public void updateAdmin(final Admin admin) throws DAOException {
		/*update wadmin_info set name=?,admin_code=?,telephone=?,email=? where id=?
		delete from wadmin_role where admin_id=?
		insert into wadmin_role values(?,?)*/
		
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "update Admin set name=?,adminCode=?,telephone=?,email=? where id=?";
				Query q = s.createQuery(hql);
				q.setParameter(0, admin.getName());
				q.setParameter(1, admin.getAdminCode());
				q.setParameter(2, admin.getTelephone());
				q.setParameter(3, admin.getEmail());
				q.setParameter(4, admin.getId());
				return q.executeUpdate();
			}
		});
		
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "delete from AdminRole where key.admin_id=?";
				Query q = s.createQuery(hql);
				q.setInteger(0, admin.getId());
				return q.executeUpdate();
			}
		});
		
		Integer []roleids = admin.getRoleids();
			if(roleids!=null){
				for(int i=0;i<roleids.length;i++){
					AdminRole adminrole = new AdminRole();
					adminrole.setKey(new AdminRoleKey(admin.getId(),roleids[i]));
					getHibernateTemplate().save(adminrole);
				}
			}
	}
	public static void main(String[] args) throws DAOException {
		/*		System.out.println(new AdminDaoImpl().findByCodeAndPassword("wang",
						"wang").getId());*/
/*				AdminDaoImpl dao = new AdminDaoImpl();
				List<Admin> admins = dao.findAll();
				System.out.println(admins);*/
		AdminDaoImpl dao = new AdminDaoImpl();
		Admin a = dao.findById(781);
		System.out.println(a);
			}
	public Admin findById(final int id) throws DAOException {
		
		Admin admin = (Admin)getHibernateTemplate().load(Admin.class, id);
		List<Integer> adminroles = (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "select key.role_id from AdminRole where key.admin_id=?";
				Query q = s.createQuery(hql);
				q.setParameter(0, id);
				return q.list();
			}
		});
		List<Integer> roleids = new ArrayList<Integer>();
		//System.out.println(adminroles.get(0));
		for(int i=0;i<adminroles.size();i++){
			roleids.add(adminroles.get(i));
		}
		admin.setRoleids(roleids.toArray(new Integer[0]));
		return admin;
		/*
		Connection con  = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			con = DaoUtil.getConnection();
			ps = con.prepareStatement("select * from wadmin_info where id=?");
			ps.setInt(1, id);
			rs= ps.executeQuery();
			while(rs.next()){
				admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setAdminCode(rs.getString("admin_code"));
				admin.setTelephone(rs.getString("telephone"));
				admin.setEmail(rs.getString("email"));
			}
			List<Integer> roleids = new ArrayList<Integer>();
			ps =con.prepareStatement("select role_id from wadmin_role where admin_id=?");
			ps.setInt(1, id);
			rs= ps.executeQuery();
			while(rs.next()){
				roleids.add(rs.getInt("role_id"));
			}
			Integer [] roleidsarr = roleids.toArray(new Integer [0]);
			admin.setRoleids(roleidsarr);
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DaoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}

	public boolean deleteAdminById(final int id) throws DAOException {
		
		/*delete from wadmin_role where admin_id=?
		delete from wadmin_info where id=?*/
		
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "delete from AdminRole where key.admin_id=?";
				Query q = s.createQuery(hql);
				q.setInteger(0, id);
				return q.executeUpdate();
			}
		});
		
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "delete from Admin where id=?";
				Query q = s.createQuery(hql);
				q.setInteger(0, id);
				return q.executeUpdate();
			}
		});
		return true;
		
/*		Connection con  = null;
		PreparedStatement ps = null;
		
		try {
			con=DaoUtil.getConnection();
			ps = con.prepareStatement("delete from wadmin_role where admin_id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps = con.prepareStatement("delete from wadmin_info where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				DaoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}


}
