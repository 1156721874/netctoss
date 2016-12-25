package dao;

import java.util.List;

import pojo.Cost;

public interface ICostDao {
List<Cost> findAll(int page,int perpage) throws DAOException;
int totalPages(int perpage) throws DAOException;
void deleteById(int id)throws DAOException;
Cost findByName(String name)throws DAOException;
boolean addCost(Cost cost) throws DAOException;
Cost findById(Integer id)  throws DAOException;
boolean updateCost( Cost cost) throws DAOException;
List<Cost> findAll()  throws DAOException;
boolean startCost(int id) throws DAOException;
}
