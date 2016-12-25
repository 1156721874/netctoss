package dao;

import java.util.List;

import pojo.Account;

public interface IAccountDao {
List<Account> findByCondition(String idcard_no,String login_name,String real_name,String status,int page,int pagesize ) throws DAOException;
int totalPages(String idcard_no, String login_name, String real_name, String status,int pagesize) throws DAOException;
boolean deleteById(int id)throws DAOException;
void startAccount(int id) throws DAOException;
void pauseAccount(int id) throws DAOException;
int findByIdCard(String idcard) throws DAOException;
boolean addAccount(Account a) throws DAOException;
Account findAccount(int id) throws DAOException;
String findIdCardNoById(int id)throws DAOException;
boolean updateAccount(Account a) throws DAOException;
String  findPwdById(int id) throws DAOException;
boolean updatePasswordById(String pwd ,int id)throws DAOException; 
boolean findAccountByRealName(String realname)throws DAOException; 
Account findAccountByIdCard(String idcard) throws DAOException; 
void  pauseService(int id) throws DAOException; 
void deleteServicesById(int accountid) throws DAOException;
}
