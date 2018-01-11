package yycgpt.base.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.base.service.UserService;
import yycgpt.utils.MailUtils;

@Controller
public class FirstAction {
	// 系统基本配置
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private UserService userService;

	// 首页
	@RequestMapping("/first")
	public String first(Model model, HttpSession httpSession) throws Exception {

		// 获取用户的groupId
		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		String groupId = activeUser.getGroupid();
		String menus = null;
		/**
		 * //系统管理员：只能进行系统用户的增删改查，其他用户没有此权限 用户类别 0:系统管理员,1：卫生局 2:卫生院 3：卫生室 4:供货商
		 * 
		 */

		if (groupId.equals("0")) {// 系统管理员
			String username = activeUser.getUserid();
			if (username.equals("admin")) {// admin
				// 加载菜单
				menus = systemConfigService.findBasicinfoById("00405")
						.getValue();
			} else {
				menus = systemConfigService.findBasicinfoById("00404")
						.getValue();
			}
		} else if (groupId.equals("3")) {// 医院
			menus = systemConfigService.findBasicinfoById("00406").getValue();
		} else if (groupId.equals("1")) {// 监督单位
			menus = systemConfigService.findBasicinfoById("00407").getValue();
		} else if (groupId.equals("4")) {// 供应商
			menus = systemConfigService.findBasicinfoById("00408").getValue();
		}

		model.addAttribute("menus", menus);
		// model.addAttribute("groupId", groupId);
		return "/base/first";
	}

	// 注册页面跳转
	@RequestMapping("/register")
	public String register() throws Exception {
		return "/base/register";
	}

	// 注册页面提交
	/**
	 * 重写提交的方法，返回提交的信息
	 */
	@RequestMapping("/registersubmit")
	public @ResponseBody
	SubmitResultInfo registerSumbit(SysuserQueryVo sysuserQueryVo)
			throws Exception {

		SysuserCustom sysuserCustom = null;
		// 先添加用户
		sysuserCustom = userService.registerSysuser(sysuserQueryVo
				.getSysuserCustom());

		if (sysuserCustom != null) {
			// 获取激活码
			String activecode = sysuserCustom.getVchar1();
			// 邮件的内容
			String emailMsg = "注册成功，请点击<a href='http://localhost:8080/yycgptproject/registersuccess.action?activeCode="
					+ activecode + "'>激活</a>,激活码为:" + activecode;
			// 发送邮件
			MailUtils.sendMail(sysuserCustom.getEmail(), emailMsg);
		}
		return ResultUtil
				.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,
						732, new Object[] { sysuserCustom.getEmail() }));
	}

	// 注册成功页面跳转
	@RequestMapping("/registersuccess")
	public String registerSuccess(String activeCode) throws Exception {
		userService.registerSuccess(activeCode);
		return "/base/login";
	}

	@RequestMapping("/welcome")
	public String welcom() {
		return "/base/welcome";
	}

}
