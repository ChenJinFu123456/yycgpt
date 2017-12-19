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
import yycgpt.base.pojo.vo.PageQuery;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.DataGridResultInfo;
import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.pojo.vo.GysypmlControlCustom;
import yycgpt.business.pojo.vo.GysypmlControlQueryVo;
import yycgpt.business.pojo.vo.GysypmlQueryVo;
import yycgpt.business.service.GysypmlControlService;

/**
 * *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 供应商产品目录控制
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author :zhuqiujie
 * @date 2017年12月16日 下午7:27:40
 */
@Controller
@RequestMapping("/ypml")
public class GysypmlControlAction {

	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private GysypmlControlService gysypmlControlService;

	// 查询跳转页面
	@RequestMapping("/querygysypmlcontrol")
	public String queryGysypmlControl(Model model) throws Exception {
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
		return "/business/ypml/querygysypmlcontrol";
	}

	// 返回结果集
	@RequestMapping("/querygysypmlcontrol_result")
	public @ResponseBody
	DataGridResultInfo queryGhsYpmlControlResult(HttpSession httpSession,
			GysypmlControlQueryVo gysypmlControlQueryVo,// 查询条件
			int page, int rows) throws Exception {
		// 取列表的总数
		int total = gysypmlControlService
				.findGysYpmlControlCount(gysypmlControlQueryVo);

		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		gysypmlControlQueryVo.setPageQuery(pageQuery);

		// 分页查询列表
		List<GysypmlControlCustom> list = gysypmlControlService
				.findGysYpmlControlList(gysypmlControlQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 提交供货状态
	@RequestMapping("/gysypmlcontrolsubmit")
	public @ResponseBody
	SubmitResultInfo gysYpmlControlSubmit(int[] indexs,// 接收页面选中行的序号
			GysypmlControlQueryVo gysypmlControlQueryVo) throws Exception {

		// 需要处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_failure = 0;
		// 处理失败的原因
		List<ResultInfo> msgsList = new ArrayList<ResultInfo>();

		// 提交的所有业务数据
		List<GysypmlControlCustom> gysypmls = gysypmlControlQueryVo
				.getGysypmls();
		for (int i = 0; i < count; i++) {
			ResultInfo resultInfo = null;

			// 得到选中行的数据
			GysypmlControlCustom gysypmlControlCustom = gysypmls.get(indexs[i]);
			// 取出更改所需要的字段
			String id = gysypmlControlCustom.getGysypmlid();
			String advice = gysypmlControlCustom.getAdvice();
			String control = gysypmlControlCustom.getControl();

			try {
				gysypmlControlService.updateGysYpmlControl(control, advice, id);
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
