package yycgpt.base.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycgpt.base.pojo.po.Dictinfo;
import yycgpt.base.pojo.vo.PageQuery;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.DataGridResultInfo;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
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
	//系统配置
	@Autowired
	private SystemConfigService systemConfigService;
	// 用户查询页面
	@RequestMapping("/queryuser")
	public String queryUser(Model model) throws Exception {
		// 将页面所需要 的数据传递到页面
		//用户类型
		List<Dictinfo> groupList = systemConfigService.findDictinfoByType("s01"); 
		model.addAttribute("groupList",groupList);
		
		//用户状态
		List<Dictinfo> stateList = systemConfigService.findDictinfoByType("yhzt");
		model.addAttribute("stateList",stateList);
		return "/base/user/queryuser";
	}

	// 用户查询数据的结果集
	// 最终数据通过@ResponseBody将java对象转化为json
	@RequestMapping("/queryuser_result")
	public @ResponseBody
	DataGridResultInfo queryuser_result(SysuserQueryVo sysuserQueryVo,
			int page,// 页码
			int rows// 每页的记录数
	) throws Exception {
		// sysuserQueryVo的非空校验
		sysuserQueryVo = sysuserQueryVo != null ? sysuserQueryVo
				: new SysuserQueryVo();
		// 查询总数
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
		// 填充total rows
		dataGridResultInfo.setRows(list);
		dataGridResultInfo.setTotal(total);
		return dataGridResultInfo;
	}

	// 跳转到添加用户提交页面
	@RequestMapping("/addsysuser")
	public String addsysuser(Model model) throws Exception {
		return "/base/user/addsysuser";
	}

	// 提交添加用户的数据
	/**
	 * 提交的表单数据统一使用包装类 提交的数据要转json输出到页面
	 */
	@RequestMapping("/addsysusersubmit")
	public @ResponseBody
	SubmitResultInfo addSysUserSumbit(SysuserQueryVo sysuserQueryVo)
			throws Exception {
		// 提示的用户信息
		// String message = "操作成功";
		// 1:成功,0:失败
		// int type = 1;
		// 默认成功
		/*
		 * ResultInfo resultInfo = new ResultInfo();
		 * resultInfo.setIndex(ResultInfo.TYPE_RESULT_SUCCESS);
		 * resultInfo.setMessage("操作成功");
		 */
		/*
		 * try {
		 * 
		 * //调用service来执行用户添加
		 * userService.insertSysuser(sysuserQueryVo.getSysuserCustom()); } catch
		 * (Exception e) { // 输出异常信息 e.printStackTrace();
		 * //对异常信息进行解析,也就是获取service的异常信息 message = e.getMessage(); type = 0;
		 * //解析 自定义异常 if(e instanceof ExceptionResultInfo){ resultInfo =
		 * ((ExceptionResultInfo)e).getResultInfo(); }else{ //未知错误异常 resultInfo
		 * = new ResultInfo(); resultInfo.setMessage("系统维护中");
		 * resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL); } }
		 */

		// 使用全局的异常处理器不用捕获
		userService.insertSysuser(sysuserQueryVo.getSysuserCustom());
		// 将执行的json结果返回页面
		/*
		 * Map<String,Object> result_map = new HashMap<String, Object>();
		 * result_map.put("type", type); result_map.put("message", message);
		 */

		// SubmitResultInfo submitResultInfo = new SubmitResultInfo(resultInfo);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

	@RequestMapping("/deleteSysuser")
	public @ResponseBody
	SubmitResultInfo deleteSysuser(String userId) throws Exception {

		userService.deleteSysuser(userId);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

	// 修改用户的jsp
	@RequestMapping("/editSysUser")
	public String editSysUser(Model model, String id) throws Exception {
		SysuserCustom sysuserCustom = userService.findSysuserById(id);
		model.addAttribute("sysuserCustom", sysuserCustom);
		return "/base/user/editSysUser";
	}

	// 修改提交
	@RequestMapping("/editSysUserSubmit")
	public  @ResponseBody SubmitResultInfo editSysUserSubmit(String id,SysuserQueryVo sysuserQueryVo) throws Exception {
		userService.updateSysuser(id, sysuserQueryVo.getSysuserCustom());
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

}
