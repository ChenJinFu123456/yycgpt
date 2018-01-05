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
import yycgpt.base.pojo.po.Dictinfo;
import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.pojo.vo.PageQuery;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.DataGridResultInfo;
import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.pojo.vo.YycgdCustom;
import yycgpt.business.pojo.vo.YycgdrkCustom;
import yycgpt.business.service.CgdService;
import yycgpt.utils.MyUtil;

@Controller
@RequestMapping("/cgd")
public class CgdAction {

	@Autowired
	private CgdService cgdService;
	@Autowired
	private SystemConfigService systemConfigService;

	// 创建采购单的基本信息也页面
	@RequestMapping("/addcgd")
	public String addCgd(HttpSession httpSession, Model model) throws Exception {
		// 医院的名称
		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		String sysMc = activeUser.getSysmc();
		// 采购单的名称
		String yycgdmc = sysMc + MyUtil.getDate() + "采购单";
		model.addAttribute("yycgdmc", yycgdmc);
		// 年份
		String year = MyUtil.get_YYYY(MyUtil.getDate());
		model.addAttribute("year", year);
		return "/business/cgd/addcgd";
	}

	// 创建采购单的基本信息保存方法
	@RequestMapping("/addcgdsubmit")
	public @ResponseBody
	SubmitResultInfo addCgdSubmit(HttpSession httpSession, String year,
			CgdQueryVo cgdQueryVo) throws Exception {

		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 单位id
		String userYyId = activeUser.getSysid();
		// 获取采购单的yycgdid
		String yycgdid = cgdService.insertYycgd(cgdQueryVo.getYycgdCustom(),
				userYyId, year);

		ResultInfo resultInfo = ResultUtil.createSuccess(Config.MESSAGE, 906,
				null);
		// 将yycgdid传到页面
		resultInfo.getSysdata().put("yycgdid", yycgdid);
		return ResultUtil.createSubmitResult(resultInfo);
	}

	// 采购单的修改页面
	@RequestMapping("/editcgd")
	public String editCgd(Model model, String id) throws Exception {
		// 采购状态
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		model.addAttribute("cgztlist", cgztlist);
		// 交易状态
		List<String> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		// 产品类别
		List<String> lblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("lblist", lblist);
		// 获取采购单的信息
		YycgdCustom yycgdCustom = cgdService.findYyCgdById(id);
		model.addAttribute("yycgd", yycgdCustom);
		return "/business/cgd/editcgd";
	}

	// 采购单的修改提交方法
	@RequestMapping("/editcgdsubmit")
	public @ResponseBody
	SubmitResultInfo editCgdSubmit(CgdQueryVo cgdQueryVo, String id)
			throws Exception {
		// 修改采购单
		cgdService.updateYyCgd(id, cgdQueryVo.getYycgdCustom());

		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

	// 采购单产品明细查询的结果集
	@RequestMapping("/queryYycgdmx_result")
	public @ResponseBody
	DataGridResultInfo queryYycgdmx_result(String id,// 采购单的id
			int page, int rows, CgdQueryVo cgdQueryVo) throws Exception {
		// 查询数据的总数
		int total = cgdService.findYyCgdMxCountByYyCgdId(id, cgdQueryVo);
		
		
		// 分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);
		// 总结果集
		List<YyCgdMxCustom> list = cgdService.findYyCgdMxListByYyCgdId(id,
				cgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		//求总计
		if(total>0){
			List<YyCgdMxCustom> sumList =cgdService.findYyCgdMxListSum(id, cgdQueryVo);
			dataGridResultInfo.setFooter(sumList);
		}
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 供应商采购单产品目录添加页面跳转
	@RequestMapping("/queryaddyycgdmx")
	public String queryAddYyCgdMx(Model model, String yycgdid) throws Exception {

		// 产品类别
		List<String> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);
		// 交易状态
		List<String> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		// 供货状态
		List<String> controllist = systemConfigService
				.findDictinfoByType("008");
		model.addAttribute("controllist", controllist);
		// 将采购单的id传到页面
		model.addAttribute("yycgdid", yycgdid);
		return "/business/cgd/queryaddyycgdmx";
	}

	// 供应商采购单产品目录添加 返回结果
	@RequestMapping("/queryaddyycgdmx_result")
	public @ResponseBody
	DataGridResultInfo queryAddYyMxCgdResult(HttpSession httpSession,
			CgdQueryVo cgdQueryVo,// 查询条件
			String yycgdid, int page, int rows) throws Exception {
		ActiveUser activeUser = (ActiveUser) httpSession
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 得到用户所属的单位(医院单位)
		String userYyId = activeUser.getSysid();
		// 取列表的总数
		int total = cgdService.findAddYpcgdmxCount(userYyId, yycgdid,
				cgdQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);

		// 分页查询列表
		List<YyCgdMxCustom> list = cgdService.findAddYpcgdmxList(userYyId,
				yycgdid, cgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 采购单产品添加提交
	@RequestMapping("/addyycgdmxsubmit")
	public @ResponseBody
	SubmitResultInfo addYyCgdMxSubmit(String yycgdid,// 采购单id
			CgdQueryVo cgdQueryVo, int[] indexs// 页面选中的序号
	) throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YyCgdMxCustom> list = cgdQueryVo.getYyCgdMxCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 获取选中单行纪录
			YyCgdMxCustom yyCgdMxCustom = list.get(indexs[i]);
			// 产品信息id
			String ypxxid = yyCgdMxCustom.getYpxxid();
			// 供应商id
			String usergysid = yyCgdMxCustom.getUsergysid();
			try {
				cgdService.insertYycgdmx(yycgdid, ypxxid, usergysid);
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

	// 更新采购单提交
	@RequestMapping("/updateyycgdmxsubmit")
	public @ResponseBody
	SubmitResultInfo updateYyCgdMxSubmit(String id,// 采购单id
			CgdQueryVo cgdQueryVo, int[] indexs// 页面选中的序号
	) throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YyCgdMxCustom> list = cgdQueryVo.getYyCgdMxCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 获取选中单行纪录
			YyCgdMxCustom yyCgdMxCustom = list.get(indexs[i]);
			// 产品信息id
			String ypxxid = yyCgdMxCustom.getYpxxid();
			// 采购量
			Integer cgl = yyCgdMxCustom.getCgl();
			try {
				cgdService.updateYyCgdMx(id, ypxxid, cgl);
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

	// 删除采购单下产品提交
	@RequestMapping("/deleteyycgdmxsubmit")
	public @ResponseBody
	SubmitResultInfo deleteYyCgdMxSubmit(String id,// 采购单id
			CgdQueryVo cgdQueryVo, int[] indexs// 页面选中的序号
	) throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YyCgdMxCustom> list = cgdQueryVo.getYyCgdMxCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 获取选中单行纪录
			YyCgdMxCustom yyCgdMxCustom = list.get(indexs[i]);
			// 产品信息id
			String ypxxid = yyCgdMxCustom.getYpxxid();
			try {
				cgdService.deleteYyCgdMx(id, ypxxid);
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

	// 医院采购单产品目录查询页面跳转
	@RequestMapping("/yycgdlist")
	public String yyCgdList(Model model) throws Exception {

		// 采购单状态
		List<String> cgdztlist = systemConfigService.findDictinfoByType("010");
		model.addAttribute("cgdztlist", cgdztlist);
		// 当前年份
		// model.addAttribute("year",MyUtil.get_YYYY(MyUtil.getDate()));
		model.addAttribute("year", "2017");
		return "/business/cgd/yycgdlist";
	}

	// 医院采购单列表结果集
	@RequestMapping("/yycgdlist_result")
	public @ResponseBody
	DataGridResultInfo yyCgdListResult(ActiveUser activeUser, String year,
			int page, int rows, CgdQueryVo cgdQueryVo) throws Exception {
		/*
		 * ActiveUser activeUser = (ActiveUser) httpSession
		 * .getAttribute(Config.ACTIVEUSER_KEY);
		 */
		// 医院单位id
		String useryyid = activeUser.getSysid();

		List<YycgdCustom> list = cgdService.findYyCgdList(useryyid, year,
				cgdQueryVo);

		// 取列表的总数
		int total = cgdService.findYyCgdCount(useryyid, year, cgdQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	/**
	 * 
	 * @param yycgdid
	 *            采购单的id
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("/submityycgd")
	public @ResponseBody
	SubmitResultInfo submitYyCgd(String id) throws Exception {
		cgdService.saveYyCgdSubmitStatus(id);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

	// 采购单审核页面跳转
	@RequestMapping("/checkyycgdlist")
	public String checkYyCgdList(Model model) throws Exception {

		// 采购单状态
		List<String> cgdztlist = systemConfigService.findDictinfoByType("010");
		model.addAttribute("cgdztlist", cgdztlist);
		// 当前年份
		// model.addAttribute("year",MyUtil.get_YYYY(MyUtil.getDate()));
		model.addAttribute("year", "2017");
		return "/business/cgd/checkyycgdlist";
	}

	// 医院采购单审核列表结果集
	@RequestMapping("/checkyycgdlist_result")
	public @ResponseBody
	DataGridResultInfo checkYyCgdListResult(ActiveUser activeUser, String year,
			int page, int rows, CgdQueryVo cgdQueryVo) throws Exception {
		// 监督单位id
		String userjdid = activeUser.getSysid();

		List<YycgdCustom> list = cgdService.findCheckYyCgdList(cgdQueryVo,
				year, userjdid);

		// 取列表的总数
		int total = cgdService.findCheckYyCgdCount(cgdQueryVo, year, userjdid);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 批量提交审核结果
	@RequestMapping("/checkyycgdsubmit")
	public @ResponseBody
	SubmitResultInfo checkYyCgdSubmit(CgdQueryVo cgdQueryVo, int[] indexs)
			throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YycgdCustom> list = cgdQueryVo.getYycgdCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 获取选中单行纪录
			YycgdCustom yycgdCustom = list.get(indexs[i]);
			// 采购单id
			String yycgdid = yycgdCustom.getId();
			try {
				cgdService.saveYycgdCheckStatus(yycgdid, yycgdCustom);
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
			// 返回详细信息
		}
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
						count_success, count_failure }), msgsList);
	}

	// 供应商采购单受理页面跳转
	@RequestMapping("/disposeyycgd")
	public String disposeYyCgdList(Model model) throws Exception {

		// 采购单状态
		/*
		 * List<String> cgdztlist =
		 * systemConfigService.findDictinfoByType("010");
		 * model.addAttribute("cgdztlist", cgdztlist);
		 */
		// 当前年份
		// model.addAttribute("year",MyUtil.get_YYYY(MyUtil.getDate()));
		model.addAttribute("year", "2017");
		return "/business/cgd/disposeyycgd";
	}

	// 医院采购单审核列表结果集
	@RequestMapping("/disposeyycgd_result")
	public @ResponseBody
	DataGridResultInfo disposeYyCgdListResult(ActiveUser activeUser,
			String year, int page, int rows, CgdQueryVo cgdQueryVo)
			throws Exception {
		// 供应商id
		String usergysid = activeUser.getSysid();

		List<YyCgdMxCustom> list = cgdService.findDisposeYyCgdList(usergysid,
				year, cgdQueryVo);

		// 取列表的总数
		int total = cgdService.findDisposeYyCgdCount(usergysid, year,
				cgdQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 批量提交供应商采购单下产品确认发货
	@RequestMapping("/disposesubmit")
	public @ResponseBody
	SubmitResultInfo disposeYyCgdStatusSubmit(CgdQueryVo cgdQueryVo,
			int[] indexs// 页面选中的序号
	) throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YyCgdMxCustom> list = cgdQueryVo.getYyCgdMxCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 获取选中单行纪录
			YyCgdMxCustom yyCgdMxCustom = list.get(indexs[i]);
			// 产品信息id
			String ypxxid = yyCgdMxCustom.getYpxxid();
			// 采购单di（采购单编号）
			String yycgdid = yyCgdMxCustom.getYycgdid();
			try {
				cgdService.saveSendStatus(yycgdid, ypxxid);
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

	// 医院采购单产品入库页面跳转
	@RequestMapping("/receivecgd")
	public String receiveCgdList(Model model) throws Exception {

		// 采购单状态
		List<String> cgdztlist = systemConfigService.findDictinfoByType("010");
		model.addAttribute("cgdztlist", cgdztlist);
		// 当前年份
		// model.addAttribute("year",MyUtil.get_YYYY(MyUtil.getDate()));
		model.addAttribute("year", "2017");
		return "/business/cgd/receiveyycgd";
	}

	// 医院采购单产品入库结果集
	@RequestMapping("/receiveyycgd_result")
	public @ResponseBody
	DataGridResultInfo receiveCgdListResult(ActiveUser activeUser, String year,
			int page, int rows, CgdQueryVo cgdQueryVo) throws Exception {
		// 医院id
		String useryyid = activeUser.getSysid();

		List<YyCgdMxCustom> list = cgdService.findYyCgdReceiveList(year,
				useryyid, cgdQueryVo);

		// 取列表的总数
		int total = cgdService
				.findYyCgdReceiveCount(year, useryyid, cgdQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 批量入库医院采购单下产品
	@RequestMapping("/receivesubmit")
	public @ResponseBody
	SubmitResultInfo receiveYyCgdRkSubmit(CgdQueryVo cgdQueryVo,
			int[] indexs// 页面选中的序号
	) throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<YycgdrkCustom> list = cgdQueryVo.getYycgdrkCustoms();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 获取选中单行纪录
			YycgdrkCustom yycgdrkCustom = list.get(indexs[i]);
			//采购单的id
			String yycgdid = yycgdrkCustom.getYycgdid();
			//产品id
			String ypxxid = yycgdrkCustom.getYpxxid();
			try {
				cgdService.saveYyCgdRk(yycgdid, ypxxid, yycgdrkCustom);
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
