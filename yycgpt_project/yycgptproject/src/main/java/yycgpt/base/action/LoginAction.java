package yycgpt.base.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.UserService;

/**
 * *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:用户认证
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author :zhuqiujie
 * @date 2017年11月30日 下午3:23:37
 */
@Controller
public class LoginAction {
	@Autowired
	private UserService userService;

	// 用户认证页面跳转
	@RequestMapping("/login")
	public String login(Model model) throws Exception {

		return "/base/login";
	}

	// 用户登录提交
	@RequestMapping("/loginSubmit")
	public @ResponseBody
	SubmitResultInfo loginSubmit(HttpSession session, String userid, String pwd,
			String validateCode) throws Exception {
		// 验证码
		String validateCode_session = (String) session
				.getAttribute("validateCode");
		if (validateCode_session == null
				|| !validateCode_session.equalsIgnoreCase(validateCode)) {
			// 验证码错误
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 113,
					null));
		}
		// 用户认证
		ActiveUser activeUser = userService.checkUserInfo(userid, pwd);
		// 将用户信息存入session                                                                                                                                                                                                                                                                                                                                             
		session.setAttribute(Config.ACTIVEUSER_KEY, activeUser);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 107, new Object[] { "" }));
	}
	
	//退出系统
	@RequestMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:login.action";
	}

}
