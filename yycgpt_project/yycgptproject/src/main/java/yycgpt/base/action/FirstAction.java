package yycgpt.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.sevice.UserService;

@Controller
public class FirstAction {
	
	
	//首页
	@RequestMapping("/first")
	public String first(Model model )throws Exception{
		
		
		return "/base/first";
	}
	
	@RequestMapping("/welcome")
	public String welcom(){
		return "/base/welcome";
	}
}
