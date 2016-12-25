package interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {
System.out.println("initinitinitinitinitinitinitinitinitinitinitinitinitinitinitinitinitinitinitinit");
	}

	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
		Map<String, Object> session = ai.getInvocationContext().getSession();
		if (session.get("urls") == null) {
			// 为空，没有登录，跳转到登录页面
			return "login";
		} else {
			// 继续检测是否你有URL权限
			// 获取能执行的权限
			Set<String> urls = (Set) session.get("urls");
			// 获取当前请求的URL
			HttpServletRequest req = ServletActionContext.getRequest();
			String requrl = req.getRequestURL().toString();
			// System.out.println(req.getRequestURI());
			String str = req.getServletPath();
			System.out.println(str.trim());
			String url = str.trim().substring(0, str.length() - 7);
			System.out.println(url + "222");
			if (urls.contains(url)) {
				return ai.invoke();
			} else {
				return "nopower";
			}
			// System.out.println(req.getRequestURL().toString());
		}
	
		//return ai.invoke();
	}
}
