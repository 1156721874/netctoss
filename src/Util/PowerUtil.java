package Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import pojo.Admin;
import pojo.Role;
import pojo.RolePrivilege;
import dao.DAOException;
import dao.IAdminDao;
import dao.IRoleDao;

@Repository
public class PowerUtil {

@Resource	
private 	IAdminDao dao;
@Resource
private IRoleDao rdao;
	//根据用户登录帐号去Privileges.xml查找具有你的权限
	public Set<String> findPower(String adminCode,String password) throws DAOException {
		Set<String> urlset = new HashSet<String>();
		Admin admin = dao.findByCodeAndPassword(adminCode, password);
		if(admin==null){
			System.out.println("用户名密码错误");
			return null;
		}else{
			//权限检查
			//1根据迎和获取相关的角色
			Set<Role> roles = admin.getRoles();
			for (Role role : roles) {
				System.out.println(role.getId()+" "+role.getName() );
				//2根据角色Role寻找操作组ID
				List<RolePrivilege> rp =  rdao.findPrivilegeIdsByRoleId(role.getId());
/*				for (RolePrivilege privilege : rp) {
					System.out.println(privilege.getId().getPrivilegeId());
				}*/
				//3根据操作组PrivilegeID去XML获取USRLS集合
				for (RolePrivilege privilege : rp) {
					List<String> urls = PrivilegeReader.getPrivilegeURlsById(privilege.getId().getPrivilegeId().toString());
					if(urls!=null){
					urlset.addAll(urls);
					}
				}
			}
		}
		for (String str : urlset) {
			System.out.println(str+"AAA");
		}
		return urlset;
	}
}
