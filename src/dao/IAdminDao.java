package dao;

import java.util.List;

import pojo.Admin;

public interface IAdminDao {
 Admin findByCodeAndPassword(String adminCode ,String password)throws DAOException;
 void addAdmin(Admin admin)throws DAOException;
 List<Admin> findAll(Integer privilegeid,Integer roleid) throws DAOException;
 boolean resetPassword(String [] pwd) throws DAOException;
 void updateAdmin(Admin admin) throws DAOException;
 Admin findById(int id) throws DAOException;
 boolean deleteAdminById(int id) throws DAOException;
//int findToatalPage(Integer privilegeId,Integer roleId,int pagesize) throws DAOException;
 Admin findAdminById(String username,String passward ) throws DAOException;
 void updateuser(Admin admin)throws DAOException;
 void moduserpassword(Admin admin)throws DAOException;
}
