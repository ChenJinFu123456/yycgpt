package yycgpt.base.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.process.context.Config;
import yycgpt.base.service.SystemConfigService;

@Controller
public class FirstAction {
	// 系统基本配置
	@Autowired
	private SystemConfigService systemConfigService;

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
		//model.addAttribute("groupId", groupId);
		return "/base/first";
	}

	@RequestMapping("/welcome")
	public String welcom() {
		return "/base/welcome";
	}

}
