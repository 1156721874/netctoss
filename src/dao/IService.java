package dao;

import java.util.List;

import pojo.Service;
import vo.ServiceVo;

public interface IService {
	List<ServiceVo> findByCondition(String osUserName,String unixHost,String idCardNo,String status,int page,int pagesize ) throws DAOException;
	int totalPages(String osUserName,String unixHost,String idCardNo,String status,int pagesize) throws DAOException;
	boolean addService(Service s)  throws DAOException;
	boolean pauseService(int id)  throws DAOException;
	boolean startService(int id) throws DAOException;
	String checkAccountStatusIsStart(int id) throws DAOException;
	ServiceVo findServiceById(int id)  throws DAOException;
	boolean updataService(Service s)   throws DAOException;
	boolean deleteById(int id)  throws DAOException;
}
