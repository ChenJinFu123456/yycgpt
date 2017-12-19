package yycgpt.business.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycgpt.base.action.SubmitResultInfo;
import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.pojo.vo.PageQuery;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.DataGridResultInfo;
import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.pojo.vo.GysypmlCustom;
import yycgpt.business.pojo.vo.GysypmlQueryVo;
import yycgpt.business.pojo.vo.YpxxCustom;
import yycgpt.business.service.YpmlService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 供货商的产品目录
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author :zhuqiujie
 * @date 2017年12月14日 下午8:31:51
 */
@Controller
@RequestMapping("/ypml")
public class YpmlAction {

	@Autowired
	private YpmlService ypmlService;
	@Autowired
	private SystemConfigService systemConfigService;

	// 页面跳转
	@RequestMapping("/querygysypml")
	public String queryGhsYpml(Model model) throws Exception {

		// 产品类别
		List<String> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);
		// 交易状态
		List<String> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		/*
		 * //质量层次(不要了) List<String> zlcclist =
		 * systemConfigService.findDictinfoByType("004");
		 * model.addAttribute("zlcclist",zlcclist);
		 */
		// 供货状态
		List<String> controllist = systemConfigService
				.findDictinfoByType("008");
		model.addAttribute("controllist", controllist);
		return "/business/ypml/querygysypml";
	}

	// 返回结果
	@RequestMapping("/querygysypml_result")
	public @ResponseBody
	DataGridResultInfo queryGhsYpmlResult(HttpSession httpSession,
			GysypmlQueryVo gysypmlQueryVo,// 查询条件
			int page, int rows) throws Exception {
		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 得到用户所属的单位
		String usergysId = activeUser.getSysid();
		// 取列表的总数
		int total = ypmlService.findGysypmlCount(usergysId, gysypmlQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		gysypmlQueryVo.setPageQuery(pageQuery);

		// 分页查询列表
		List<GysypmlCustom> list = ypmlService.findGysypmlList(usergysId,
				gysypmlQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 供应商产品目录添加页面跳转
	@RequestMapping("/queryaddgysypml")
	public String queryAddGhsYpml(Model model) throws Exception {

		// 产品类别
		List<String> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);
		// 交易状态
		List<String> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		/*
		 * //质量层次(不要了) List<String> zlcclist =
		 * systemConfigService.findDictinfoByType("004");
		 * model.addAttribute("zlcclist",zlcclist);
		 */
		// 供货状态
		List<String> controllist = systemConfigService
				.findDictinfoByType("008");
		model.addAttribute("controllist", controllist);
		return "/business/ypml/queryaddgysypml";
	}

	// 返回结果
	@RequestMapping("/queryaddgysypml_result")
	public @ResponseBody
	DataGridResultInfo queryAddGhsYpmlResult(HttpSession httpSession,
			GysypmlQueryVo gysypmlQueryVo,// 查询条件
			int page, int rows) throws Exception {
		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 得到用户所属的单位
		String usergysId = activeUser.getSysid();
		// 取列表的总数
		int total = ypmlService.findAddGysypmlCount(usergysId, gysypmlQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		gysypmlQueryVo.setPageQuery(pageQuery);

		// 分页查询列表
		List<GysypmlCustom> list = ypmlService.findAddGysypmlList(usergysId,
				gysypmlQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 供货商产品目录的添加提交方法
	@RequestMapping("addgysypmlsubmit")
	public @ResponseBody
	SubmitResultInfo addGysYpmlSubmit(HttpSession httpSession, int[] indexs,// 接收页面选中行的序号
			GysypmlQueryVo gysypmlQueryVo) throws Exception {

		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 单位的id(供货商的id)
		String userGysId = activeUser.getSysid();
		// System.out.println(indexs);
		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YpxxCustom> list = gysypmlQueryVo.getYpxxCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 根据选中行的数据
			YpxxCustom ypxxCustom = list.get(indexs[i]);
			String ypxxId = ypxxCustom.getId();

			try {
				ypmlService.insertGysYpml(userGysId, ypxxId);
			} catch (Exception e) {
				e.printStackTrace();
				// 进行异常解析
				if (e instanceof ExceptionResultInfo) {
					resultInfo = ((ExceptionResultInfo) e).getResultInfo();
				} else {
					// 构造未知错误的异常
					resultInfo = ResultUtil.createFail(Config.MESSAGE, 900,
							null);
				}
			}
			if (resultInfo == null) {
				// 成功
				count_success++;
			} else {
				count_failure++;
				// 记录失败原因
				msgsList.add(resultInfo);
			}
		}
		/*
		 * return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
		 * Config.MESSAGE, 907, new Object[] { count_success, count_failure }));
		 */
		// 返回详细信息
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
						count_success, count_failure }), msgsList);
	}

	// 供货商产品目录删除
	@RequestMapping("/deletegysypmlsubmit")
	public @ResponseBody
	SubmitResultInfo deleteGysYpmlSubmit(HttpSession httpSession, int[] indexs,// 接收页面选中行的序号
			GysypmlQueryVo gysypmlQueryVo) throws Exception {

		// 获取供货商的gysUserId
		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		String gysUserId = activeUser.getSysid();
		// 需要删除（选中）的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();
		// 一个页面所有的业务数据
		List<YpxxCustom> list = gysypmlQueryVo.getYpxxCustoms();

		/**
		 * 所有的gysUserId都是一样的
		 * 
		 * list(i)==>indexs[i]
		 */
		// 循环遍历寻妖处理的记录，逐条删除
		for (int i = 0; i < count; i++) {

			ResultInfo resultInfo = null;
			// 根据选中行的数据
			YpxxCustom ypxxCustom = list.get(indexs[i]);
			String ypxxId = ypxxCustom.getId();

			try {
				ypmlService.deleteGysYpml(gysUserId, ypxxId);

			} catch (Exception e) {
				e.printStackTrace();
				// 进行异常解析
				if (e instanceof ExceptionResultInfo) {
					resultInfo = ((ExceptionResultInfo) e).getResultInfo();
				} else {
					// 构造未知错误的异常
					resultInfo = ResultUtil.createFail(Config.MESSAGE, 900,
							null);
				}
			}
			if (resultInfo == null) {
				// 成功
				count_success++;
			} else {
				count_failure++;
				// 记录失败原因
				msgsList.add(resultInfo);
			}
		}
		// 返回详细信息
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
						count_success, count_failure }), msgsList);
	}
}
