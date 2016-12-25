package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pojo.Role;
import pojo.RolePrivilege;
import pojo.RolePrivilegeId;

@Repository
public class RoleDaoImp extends HibernateDaoSupport  implements IRoleDao {

	@Resource
	public void setMySessionFactory(SessionFactory sf) {
		super.setSessionFactory(sf);
	} 
	
	public void addRole(Role role) throws DAOException {
		//插入Roleinfo
		getHibernateTemplate().save(role);
		//插入role_privilege
		for(String pid:role.getPrivilegesIds()){
		RolePrivilege rp = new RolePrivilege();
		rp.setId(new RolePrivilegeId(role.getId(),Integer.parseInt(pid)));
			getHibernateTemplate().save(rp);
		}
	}

	public boolean deleteRoleById( final int id) throws DAOException {
		//删除子表
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "delete from  RolePrivilege  where id.roleid=?";
				Query q = s.createQuery(hql);
				q.setInteger(0, id);
				return q.executeUpdate();
			}
		});
		//删除父表
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "delete from  Role  where id=?";
				Query q = s.createQuery(hql);
				q.setInteger(0, id);
				return q.executeUpdate();
			}
		});
		return true;
	}

	public List<Role> findAll() throws DAOException {
		System.out.println("RoleDaoImp  findAll.....");
		List<Role> roles=(List)getHibernateTemplate().find("from Role");
		for(int i=0;i<roles.size();i++){
			Role rrole = findById(roles.get(i).getId());
			roles.get(i).setPrivilegesIds(rrole.getPrivilegesIds());
		}
		return roles;
	}

	public Role findById(int id) throws DAOException {
		Role role = (Role)getHibernateTemplate().load(Role.class, id);
		List<RolePrivilege> list = findPrivilegeIdsByRoleId(role.getId());
		List<String> pids = new ArrayList<String>();
		for(RolePrivilege rp:list){
			Integer pid = rp.getId().getPrivilegeId();
			pids.add(pid.toString());
		}
		role.setPrivilegesIds(pids.toArray(new String[0]));
		return role;
	}
	
	public List<RolePrivilege> findPrivilegeIdsByRoleId(Integer roleid) throws DAOException {
		String hql = "from RolePrivilege  where id.roleid=?";
		Object [] params = {roleid};
		List<RolePrivilege> list = getHibernateTemplate().find(hql,params);
		return list;
	}

	public int findTotalPage(int pagesize) throws DAOException {
		Integer temppage = (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "select count(*) from wrole_info";
				Query q = s.createQuery(hql);
				Long pages = (Long)q.uniqueResult();
				return pages.intValue();
			}
		});
		 	return (temppage%pagesize==0)? temppage/pagesize:(temppage/pagesize)+1;
	}

	public void updateRole(final Role role) throws DAOException {
		//添加roel
		getHibernateTemplate().update(role);
		//删除原有的Privilege
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				String hql = "delete from RolePrivilege where id.roleid=?";
				Query q =s.createQuery(hql);
				q.setInteger(0, role.getId());
				return q.executeUpdate();
			}
		});
		//更新Privilege
		for(String pid:role.getPrivilegesIds()){
			RolePrivilege rp = new RolePrivilege();
			rp.setId(new RolePrivilegeId(role.getId(),Integer.parseInt(pid)));
				getHibernateTemplate().save(rp);
			}
	}


	
/*
	public void addRole(Role role) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("role name :"+role.getName());
		try {
			String sql = "insert into wrole_info values(windex.nextval,?)";
			String[] columns = { "id" };
			con = DaoUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql, columns);
			ps.setString(1, role.getName());
			// 1 add role
			ps.executeUpdate();
			// 2 取到新增角色信息生成的id
			rs = ps.getGeneratedKeys();
			rs.next();
			int roleid = rs.getInt(1);
			// 3根据生成的ID和参数里的一组权限ID
			sql = "insert into wrole_privilege values(?,?)";
			ps = con.prepareStatement(sql);
			String[] privileges = role.getPrivilegesIds();
			if (privileges != null && privileges.length > 0) {
				for (String privilegesid : privileges) {
					ps.setInt(1, roleid);
					ps.setInt(2, Integer.valueOf(privilegesid));
					ps.addBatch();
				}
				ps.executeBatch();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DaoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Role findById(int id) throws DAOException {
		//System.out.println("ID id :"+id);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Role role = null;
		try {
			con = DaoUtil.getConnection();
			ps = con.prepareStatement("select * from wrole_info where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
			}
			String sql = "select PRIVILEGE_ID from wrole_privilege where role_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List<String> privilegesids = new ArrayList<String>();
			while (rs.next()) {
				privilegesids.add(rs.getString("PRIVILEGE_ID"));
			}
			String[] pid =   privilegesids.toArray(new String[0]);
			//System.out.println(pid.length);
			role.setPrivilegesIds(pid);
			return role;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DaoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateRole(Role role) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 更新角色信息
			String sql = "update wrole_info set name=? where id=?";
			con = DaoUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, role.getName());
			System.out.println(role.getId() + "00000000");
			ps.setInt(2, role.getId());
			ps.executeUpdate();

			// 删除该角色对应的角色对应的角色权限数据
			sql = "delete from wrole_privilege where role_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, role.getId());
			ps.executeUpdate();
			// 重新插入
			sql = "insert into wrole_privilege values(?,?)";
			ps = con.prepareStatement(sql);
			String[] privileges = role.getPrivilegesIds();
			if (privileges != null && privileges.length > 0) {
				for (String privilegesid : privileges) {
					ps.setInt(1, role.getId());
					ps.setInt(2, Integer.valueOf(privilegesid));
					ps.addBatch();
				}
				ps.executeBatch();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<Role> findAll() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Role> roles = new ArrayList<Role>();
		try {
			con = DaoUtil.getConnection();
			ps = con.prepareStatement("select * from wrole_info i inner join"
					+ " wrole_privilege p on i.id = p.role_id");
			rs = ps.executeQuery();
			Map<Integer, Role> rolemap = new HashMap<Integer, Role>();
			Map<Integer, List<String>> privilegemap = new HashMap<Integer, List<String>>();
			while (rs.next()) {
				Role role = createRole(rs);
				if (!rolemap.containsKey(role.getId())) {
					rolemap.put(role.getId(), role);
				}
				String privilegeid = rs.getString("PRIVILEGE_ID");
				if (privilegemap.containsKey(role.getId())) {
					List<String> privilegeids = privilegemap.get(role.getId());
					privilegeids.add(privilegeid);
				} else {
					List<String> privilegeids = new ArrayList<String>();
					privilegeids.add(privilegeid);
					privilegemap.put(role.getId(), privilegeids);
				}
			}
			Iterator<Integer> i = rolemap.keySet().iterator();
			while (i.hasNext()) {
				Integer roleid = i.next();
				Role role = rolemap.get(roleid);
				List<String> privilegesListId = privilegemap.get(roleid);
				String[] privileges = privilegesListId.toArray(new String[0]);
				role.setPrivilegesIds(privileges);
				roles.add	return (temppage%pagesize==0)? temppage/pagesize:(temppage/pagesize)+1;(role);
			}
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
public int findTotalPage(int pagesize){
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		con = DaoUtil.getConnection();
		ps = con.prepareStatement("select count(*) from wrole_info");
		rs = ps.executeQuery();
		if(rs.next()){
			int temppage = rs.getInt(1);
			return (temppage%pagesize==0)? temppage/pagesize:(temppage/pagesize)+1;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			DaoUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return pagesize;
	
}
	private Role createRole(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		return role;
	}

	public boolean deleteRoleById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DaoUtil.getConnection();
			con.setAutoCommit(false);
			ps = con
					.prepareStatement("delete from wrole_privilege where role_id=? ");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps = con.prepareStatement("delete from wrole_info where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DaoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}*/
	
}
