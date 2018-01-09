package yycgpt.business.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import yycgpt.business.pojo.vo.GysypmlQueryVo;
import yycgpt.business.pojo.vo.YpxxCustom;
import yycgpt.business.pojo.vo.YpxxFail;
import yycgpt.business.pojo.vo.YpxxQueryVo;
import yycgpt.business.service.YpxxService;
import yycgpt.utils.ExcelExportSXXSSF;
import yycgpt.utils.HxlsRead;
import yycgpt.utils.UUIDBuild;
import yycgpt.utils.HxlsOptRowsInterface;

/**
 * 
 * *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 器械信息
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author :zhuqiujie
 * @date 2017年12月12日 上午9:04:48
 */
@Controller
@RequestMapping("/ypml")
public class YpxxAction {

	@Autowired
	private YpxxService ypxxService;
	@Autowired
	private SystemConfigService systemConfigService;

	// 注入目录导入的接口
	@Autowired
	private HxlsOptRowsInterface hxlsOptRowsInterface;

	// 导出页面展示
	@RequestMapping("/exportYpxx")
	public String exportYpxx(Model model) throws Exception {

		// 产品类别
		List<Dictinfo> yplblist = systemConfigService.findDictinfoByType("001");

		// 交易状态
		List<Dictinfo> jyztlist = systemConfigService.findDictinfoByType("003");

		model.addAttribute("yplblist", yplblist);
		model.addAttribute("jyztlist", jyztlist);

		return "/business/ypml/exportYpxx";
	}

	// 导出提交
	@RequestMapping("/exportYpxxSumbit")
	public @ResponseBody
	SubmitResultInfo exportYpxxSumbit(YpxxQueryVo ypxxQueryVo, int[] indexs)
			throws Exception {
		// 调用封装
		// 导出文件存放的路径，并且是虚拟目录指向的路径
		// String filePath = "d:/upload/linshi/";
		String filePath = systemConfigService.findBasicinfoById("00301")
				.getValue();

		// 导出文件的前缀
		String filePrefix = "ypxx";
		// -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows = 100;

		// 导出数据的title
		/**
		 * 
		 * 流水号 通用名 规格 中标价格 生产企业 交易状态 管理类别
		 * 
		 * 
		 */

		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("流水号");
		fieldNames.add("通用名");
		// fieldNames.add("剂型");
		fieldNames.add("规格");
		// fieldNames.add("转换系数");
		fieldNames.add("中标价格");
		fieldNames.add("生产企业");
		fieldNames.add("交易状态");
		fieldNames.add("管理类别");
		// 告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes = new ArrayList<String>();
		fieldCodes.add("bm");// 产品流水号
		fieldCodes.add("mc");// 通用名
		// fieldCodes.add("jx");
		fieldCodes.add("gg");// 规格
		fieldCodes.add("zbjg");// 中标价格
		// fieldCodes.add("zhxs");
		fieldCodes.add("scqymc");// 生产企业名称
		fieldCodes.add("jyztmc");// 交易状态
		fieldCodes.add("lbmc");// 类别名称
		// 注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应

		// 开始导出，执行一些workbook及sheet等对象的初始创建
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath,
				"/upload/", filePrefix, fieldNames, fieldCodes, flushRows);

		/**
		 * 导出多选的数据，如果indexs没有选中，直接导出查询的数据 如果有选中，则导出选中的数据
		 */
		List<YpxxCustom> list = new ArrayList<YpxxCustom>();
		if (indexs == null || indexs.length == 0) {
			list = ypxxService.findYpxxList(ypxxQueryVo);
		} else {
			// 获取当页所有的数据
			List<YpxxCustom> ypxxCustoms = ypxxQueryVo.getYpxxs();
			String ypxxId = null;
			List<YpxxCustom> _ypxxCustoms = null;
			YpxxQueryVo _ypxxQueryVo = new YpxxQueryVo();
			YpxxCustom _ypxxCustom = new YpxxCustom();
			for (int i = 0; i < indexs.length; i++) {
				// 获取ypxxId
				ypxxId = ypxxCustoms.get(indexs[i]).getId();
				// 设置查询条件
				_ypxxCustom.setId(ypxxId);
				_ypxxQueryVo.setYpxxCustom(_ypxxCustom);
				// 根据主键找出记录
				_ypxxCustoms = ypxxService.findYpxxList(_ypxxQueryVo);
				if (_ypxxCustoms == null || _ypxxCustoms.size() == 0) {
					// 不存在
					ResultUtil.throwExcepion(ResultUtil.createFail(
							Config.MESSAGE, 316, null));
				}
				list.add(_ypxxCustoms.get(0));
			}
		}

		// 执行导出
		excelExportSXXSSF.writeDatasByObject(list);
		// 输出文件，返回下载文件的http地址
		String webpath = excelExportSXXSSF.exportFile();

		// System.out.println(webpath);

		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 313, new Object[] { list.size(), webpath }));
	}

	// 目录的导入
	@RequestMapping("/importypxx")
	public String importYpxx(Model model) throws Exception {

		return "/business/ypml/importypxx";
	}

	@RequestMapping("/importypxxsubmit")
	public @ResponseBody
	SubmitResultInfo importYpxxSubmit(MultipartFile ypxximportfile)
			throws Exception {

		// 文件的原始名
		String originalFilename = ypxximportfile.getOriginalFilename();
		// 取文件扩张名xls,新命名文件，准备写入磁盘
		File file = new File("D:/upload/linshi2/" + UUIDBuild.getUUID()
				+ originalFilename.substring(originalFilename.lastIndexOf(".")));
		// 文件的目录不存在，创建
		if (!file.exists()) {
			file.mkdirs();
		}

		// 将文件写入磁盘
		ypxximportfile.transferTo(file);
		// 获取文件的绝对路径
		String absolutePath = file.getAbsolutePath();
		HxlsRead xls2csv = null;
		try {
			// 第一个参数就是导入的文件
			// 第二个参数就是导入文件中哪个sheet
			// 第三个参数导入接口的实现类对象
			xls2csv = new HxlsRead(absolutePath, 1, hxlsOptRowsInterface);
			xls2csv.process();
			// 需要提示导入成功
			long successRow = xls2csv.getOptRows_success();
			long failureRow = xls2csv.getOptRows_failure();
			/**
			 * 对导入失败的处理
			 */
			// 获取导入失败记录
			List<List<String>> lists_failure = xls2csv.getFailrows();
			// 存在导入失败记录
			if (lists_failure != null || lists_failure.size() > 0) {
				// 获取导入失败原因
				List<String> failmsgs = xls2csv.getFailmsgs();

				// 将List<List<String>> ==> List<Ypxxfail>
				List<YpxxFail> ypxxFails = new ArrayList<YpxxFail>();

				for (int i = 0; i < lists_failure.size(); i++) {
					List<String> listStr = lists_failure.get(i);
					YpxxFail ypxxFail = new YpxxFail();
					// List<String> ==> Ypxxfail
					ypxxFail.setMc(listStr.get(0));
					ypxxFail.setGg(listStr.get(1));
					ypxxFail.setZbjg(listStr.get(2));
					ypxxFail.setScqymc(listStr.get(3));
					ypxxFail.setJyzt(listStr.get(4));
					ypxxFail.setLb(listStr.get(5));
					// 错误提示信息
					ypxxFail.setMsg(failmsgs.get(i));
					ypxxFails.add(ypxxFail);
				}

				// 获取导入记录的title
				List<String> list_title = xls2csv.getRowtitle();
				// 增加一列错误的提示信息，用varchar1来提示错误信息
				list_title.add("导入错误提示信息");
				// 告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
				List<String> fieldCodes = new ArrayList<String>();
				fieldCodes.add("mc");// 通用名
				fieldCodes.add("gg");// 规格
				fieldCodes.add("zbjg");
				fieldCodes.add("scqymc");
				fieldCodes.add("jyzt");
				fieldCodes.add("lb");
				fieldCodes.add("msg");// 错误提示信息
				// -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
				int flushRows = 100;
				// 导出文件存放的路径，并且是虚拟目录指向的路径
				// String filePath = "d:/upload/linshi/";
				String filePath = systemConfigService
						.findBasicinfoById("00301").getValue();
				// 导出文件的前缀
				String filePrefix = "ypxxMsg";
				ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(
						filePath, "/upload/", filePrefix, list_title,
						fieldCodes, flushRows);
				// 执行导出
				excelExportSXXSSF.writeDatasByObject(ypxxFails);
				// 输出文件，返回下载文件的http地址
				String webpath = excelExportSXXSSF.exportFile();
				return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
						Config.MESSAGE, 908, new Object[] { successRow,
								failureRow, webpath }));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 需要提示导入成功的提示
		long successRow = xls2csv.getOptRows_success();
		long failureRow = xls2csv.getOptRows_failure();

		return ResultUtil.createSubmitResult(ResultUtil.createInfo(
				Config.MESSAGE, 314, new Object[] { successRow, failureRow }));
	}

	// 产品信息列表展示
	@RequestMapping("/queryypxxsubmit")
	public @ResponseBody
	DataGridResultInfo queryYpxxSubmit(YpxxQueryVo ypxxQueryVo, int page,
			int rows) throws Exception {
		// 产品总记录数
		int total = ypxxService.findYpxxListCount(ypxxQueryVo);
		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		// 设置分页参数
		pageQuery.setPageParams(total, rows, page);
		ypxxQueryVo.setPageQuery(pageQuery);
		// 得到总查询结果集
		List<YpxxCustom> list = ypxxService.findYpxxList(ypxxQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 修改产品信息的jsp
	@RequestMapping("/editYpxx")
	public String editYpxx(Model model, String id) throws Exception {
		YpxxCustom ypxxCustom = ypxxService.findYpxxById(id);

		model.addAttribute("ypxxCustom", ypxxCustom);
		return "/business/ypml/editYpxx";
	}

	// 修改产品信息提交
	@RequestMapping("/editYpxxSubmit")
	public @ResponseBody
	SubmitResultInfo editYpxxSubmit(String id, YpxxQueryVo ypxxQueryVo)
			throws Exception {
		ypxxService.updateYpxx(id, ypxxQueryVo.getYpxxCustom());
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

	//删除产品信息
	@RequestMapping("/delypxxsubmit")
	public @ResponseBody
	SubmitResultInfo delYpxxSubmit(String ypxxid) throws Exception {

		ypxxService.deleteYpxxById(ypxxid);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}

}
