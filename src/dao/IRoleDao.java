package dao;

import java.util.List;

import pojo.Role;
import pojo.RolePrivilege;

public interface IRoleDao {
void addRole(Role role) throws DAOException;
Role findById(int id)  throws DAOException;
void updateRole(Role role) throws DAOException;
List<Role> findAll()  throws DAOException;
boolean deleteRoleById(int id)  throws DAOException;
int findTotalPage(int pagesize) throws DAOException;
List<RolePrivilege> findPrivilegeIdsByRoleId(Integer roleid)  throws DAOException;
}
