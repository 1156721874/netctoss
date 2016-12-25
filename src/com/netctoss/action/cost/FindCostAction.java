package com.netctoss.action.cost;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pojo.Cost;

import com.netctoss.action.BaseAction;

import dao.DAOException;
import dao.ICostDao;
//plate object java object
@Repository("FindCostAction")
@Scope("prototype")
public class FindCostAction extends BaseAction{
private List<Cost> costlist;
private int page=1;
private int totalpage;
private int pagesize;
@Resource
private ICostDao costdao;
	public String execute(){
		//查询全部数据，把数据发送到页面，返回result的值通过result找到对应的jsp
/*		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session1 = req.getSession();
		ActionContext ctx = ActionContext.getContext();
		Map<String,Object> session2 = ctx.getSession();
		session.put("name", "Tarena");
		System.out.println(session.get("name"));
		System.out.println(session1.getAttribute("name"));
		System.out.println(session2.get("name"));*/
		try {
			costlist = costdao.findAll(page,pagesize);
			totalpage = costdao.totalPages(pagesize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public List<Cost> getCostlist() {
		return costlist;
	}
	public void setCostlist(List<Cost> costlist) {
		this.costlist = costlist;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public ICostDao getCostdao() {
		return costdao;
	}

	public void setCostdao(ICostDao costdao) {
		this.costdao = costdao;
	}
}
