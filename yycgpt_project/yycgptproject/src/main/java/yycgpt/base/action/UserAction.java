package yycgpt.base.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycgpt.base.pojo.vo.PageQuery;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;
import yycgpt.base.process.result.DataGridResultInfo;
import yycgpt.base.service.UserService;

/**
 * 系统用户管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction {
	
	@Autowired
	private UserService userService;
	
	//用户查询页面
	@RequestMapping("/queryuser")
	public  String queryUser(Model model)throws Exception{
		//将页面所需要 的数据传递到页面
		
		
		return "/base/user/queryuser";
	}
	
	//用户查询数据的结果集
	//最终数据通过@ResponseBody将java对象转化为json
	@RequestMapping("/queryuser_result")
	public @ResponseBody DataGridResultInfo queryuser_result(
			SysuserQueryVo sysuserQueryVo,
			int page,//页码
			int rows//每页的记录数
			)throws Exception{
		//sysuserQueryVo的非空校验
		sysuserQueryVo = sysuserQueryVo!=null?sysuserQueryVo:new SysuserQueryVo();
		//查询总数
		int total = userService.findSysuserCount(sysuserQueryVo);
		/**
		 * 因为pageQuery被分装到sususerQueryVo中，所以要设置pageQuery中的属性值
		 * 通过分析知道dataGrid会向页面传递当前页码page和每页的记录数rows
		 */
		 PageQuery pageQuery = new PageQuery();
		 pageQuery.setPageParams(total, rows, page);
		 sysuserQueryVo.setPageQuery(pageQuery);
		List<SysuserCustom> list = userService.findSysuserList(sysuserQueryVo);
		
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		//填充total rows
		dataGridResultInfo.setRows(list);
		dataGridResultInfo.setTotal(total);		
		return dataGridResultInfo;
	}
	
}
