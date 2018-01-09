package yycgpt.business.action;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycgpt.base.pojo.po.Dictinfo;
import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.pojo.vo.PageQuery;
import yycgpt.base.process.result.DataGridResultInfo;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.service.BusinessService;
import yycgpt.utils.MyUtil;

@Controller
@RequestMapping("/jymx")
/**
 * 
 * @author Administrator
 *交易明细查询
 */
public class JymxAction {

	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private BusinessService businessService;
	@RequestMapping("/businesslist")
	public String businessList(Model model) throws Exception {
		// 采购状态
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		model.addAttribute("cgztlist", cgztlist);
		// 设置年份
		String year = MyUtil.get_YYYY(MyUtil.getDate());
		model.addAttribute("year", year);
		// 产品类别
		List<String> lblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("lblist", lblist);
		return "/business/Jymx/businesslist";
	}

	@RequestMapping("/businesslist_result")
	public @ResponseBody
	DataGridResultInfo businessListResult(String year, ActiveUser activeUser,
			CgdQueryVo cgdQueryVo, int rows, int page) throws Exception {
		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();
		// 列表的总数
		int total = businessService.findYyBusinessCount(cgdQueryVo, year,
				sysid, groupid);
		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);
		// 总的记录列表
		List<YyCgdMxCustom> list = businessService.findYyBusinessList(
				cgdQueryVo, year, sysid, groupid);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		
		//统计数据
		if (total > 0) {
			List<YyCgdMxCustom> sumList = businessService.findYyBusinessListSum(cgdQueryVo, year, sysid, groupid);
			dataGridResultInfo.setFooter(sumList);
		}
		
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	@RequestMapping("/groupbyypxx")
	public String groupByYpxxList(Model model) throws Exception {
		// 采购状态
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		model.addAttribute("cgztlist", cgztlist);
		// 设置年份
		String year = MyUtil.get_YYYY(MyUtil.getDate());
		model.addAttribute("year", year);
		return "/business/Jymx/groupbyypxx";
	}

	@RequestMapping("/groupbyypxx_result")
	public @ResponseBody
	DataGridResultInfo groupByYpxxListResult(String year,
			ActiveUser activeUser, CgdQueryVo cgdQueryVo, int rows, int page)
			throws Exception {
		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();
		// 列表的总数
		int total = businessService.findYyBusinessCount(cgdQueryVo, year,
				sysid, groupid);
		// 分页的参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		cgdQueryVo.setPageQuery(pageQuery);
		// 总的记录列表
		List<YyCgdMxCustom> list = businessService
				.findYyBusinessGroupByYpxxList(cgdQueryVo, year, sysid, groupid);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}

	// 按区域统计
	@RequestMapping("/groupbyarea")
	public String  groupByAreaList(Model model, ActiveUser activeUser,String year,
			CgdQueryVo cgdQueryVo,HttpSession session) throws Exception {
		// 采购状态
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		model.addAttribute("cgztlist", cgztlist);
		// 设置年份
		String year1 = MyUtil.get_YYYY(MyUtil.getDate());
		model.addAttribute("year", year1);

		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();
		
		//如果年份为空就是2017
		if(year==null||year.equals("")){
			year="2018";
		}
		
		List<YyCgdMxCustom> list = businessService
				.findYyBusinessGroupByAreaList(cgdQueryVo, year, sysid, groupid);
		
		//jfreechart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//1、统计的数值  2、统计指标的名称  3、统计分类
		/*dataset.addValue(1310, "产品采购金额", "崔庙镇");
		dataset.addValue(720.68, "产品采购金额", "汜水镇");
		dataset.addValue(675.3, "产品采购金额", "高山镇");
		dataset.addValue(560, "产品采购金额", "城关乡");
		dataset.addValue(680.88, "产品采购金额", "刘河镇");
		dataset.addValue(781, "产品采购金额", "环翠峪");*/
		
		for(YyCgdMxCustom yyCgdMxCustom:list){
			dataset.addValue(yyCgdMxCustom.getCgje(), "产品采购金额", yyCgdMxCustom.getAreaname());
		}
		JFreeChart chart = ChartFactory.createBarChart3D("产品采购金额汇总",// 图形名称
				"",// 分类名称，为横坐标名称
				"采购金额(元)",// 值名称，为纵坐标名称
				dataset,// 数据集合
				PlotOrientation.VERTICAL,// 垂直显示
				false,// 是否显示图例
				false,// 是否使用工具提示
				false);// 是否使用url

		// 在柱上显示数值
		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer3D renderer = new BarRenderer3D();

		// 设置柱的颜色
		// renderer.setSeriesPaint(0, Color.decode("#ff0000"));

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		// 默认的数字显示在柱子中，通过如下两句可调整数字的显示
		// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		// 设置每个地区所包含的平行柱的之间距离
		// renderer.setItemMargin(0.3);
		plot.setRenderer(renderer);

		// 配置字体
		Font xfont = new Font("宋体", Font.PLAIN, 12);// X轴
		Font yfont = new Font("宋体", Font.PLAIN, 12);// Y轴
		Font kfont = new Font("宋体", Font.PLAIN, 12);// 底部
		Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题
		// 图形的绘制结构对象,对于饼图不适用
		//CategoryPlot plot = chart.getCategoryPlot();

		// 图片标题
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));

		// 底部
		LegendTitle legendTitle = chart.getLegend();
		if (legendTitle != null) {
			legendTitle.setItemFont(kfont);
		}

		// X 轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(xfont);// 轴标题
		domainAxis.setTickLabelFont(xfont);// 轴数值
		domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		// Y 轴
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(yfont);
		rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
		rangeAxis.setTickLabelFont(yfont);

		//通过response输出图片
		/*response.setContentType("image/png");
		ChartUtilities.writeChartAsPNG(out, chart, 800, 500);*/
		
		//将图形放在session
		String jfreechart_filename = ServletUtilities.saveChartAsPNG(chart, 900, 500,null, session);
		model.addAttribute("jfreechart_filename",jfreechart_filename);

		return "/business/Jymx/groupbyarea";
	}
	
	/**
	 * 对医院交易明细的统计：采购量、采购金额、入库量、入库金额
	 */
	
	
	
	
}
