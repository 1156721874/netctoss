package interceptor;

import Util.HibernateUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInViewInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("开始进入SessionInViewInterceptor");
		ai.invoke();
		HibernateUtil.closeSession();
		return null;
	}

}
