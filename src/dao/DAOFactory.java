package dao;


public class DAOFactory {
private static ICostDao costdao = new CostDaoImpl();
private static IAdminDao admindao = new AdminDaoImpl();
private static IAccountDao accountdao = new AccountDaoImp();
private static IService servicedao = new ServiceDaoImp();
private static IRoleDao roledao = new RoleDaoImp();
	public static ICostDao getCostDAO(){
		return costdao;
	}
	public static IAdminDao getAdminDao(){
		return admindao;
	}
	public static IAccountDao getAccountDao(){
		return accountdao;
	}
	public static IService getServiceDao(){
		return servicedao;
	}
	public static IRoleDao getRoleDao(){
		return roledao;
	}
}
