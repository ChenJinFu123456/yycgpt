package yycgpt.base.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.utils.ResourcesUtil;

/**
 * * <p>Title: </p>
   * <p>Description: 用户身份校验</p>
   * <p>Company: </p> 
   * @author :zhuqiujie
   * @date 2017年12月11日 下午2:38:00
 */
public class LoginInterceptor implements HandlerInterceptor{

	//在进入action方法之前
	//使用场景：用户认证
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//检验用户的身份是否合法
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser)session.getAttribute(Config.ACTIVEUSER_KEY);
		//用户已经登录
		if(activeUser!=null){
			//用户登录,放行
			return true;
		}
		
		//用户返回的是否是公开资源地址
		
		//能够被公开访问的所有地址
		List<String> open_urls = ResourcesUtil.gekeyList(Config.ANONYMOUS_ACTIONS);
		//用户的访问地址
		String url = request.getRequestURI();
		for(String open_url:open_urls){
			//如果是公开地址
			if(url.indexOf(open_url)>=0){
				return true;
			}
		}
		//拦截，跳转到登录页面
		//request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request, response);
		ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,106 , null));
		//在全局异常处理器中判断异常的代码是否是106，如果是就要跳转到登录页面
		return false;
	}
	//在返回modelAndView之前
	//使用场景：在这里统一返回的数据进行处理，比如添加菜单的导航
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//action方法执行完成，已经返回modelAndView
	//使用场景：统一处理异常处理。统一处理系统处理，用于监视action方法的执行时间，
	//在preHandle记录开始时间，在afterCompletion记录结束时间
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
