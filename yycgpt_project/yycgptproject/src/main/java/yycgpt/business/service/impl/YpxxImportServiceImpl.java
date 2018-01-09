package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.pojo.po.Ypxx;
import yycgpt.business.pojo.po.YpxxExample;
import yycgpt.utils.CheckRegex;
import yycgpt.utils.HxlsOptRowsInterface;
import yycgpt.utils.UUIDBuild;

/**
 * *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:实现HxlsOptRowsInterface，得到需要导入的数据
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author :zhuqiujie
 * @date 2017年12月12日 下午9:56:26
 */
public class YpxxImportServiceImpl implements HxlsOptRowsInterface {

	@Autowired
	private YpxxMapper ypxxMapper;

	@Override
	public String optRows(int sheetIndex, int curRow, List<String> rowlist)
			throws Exception {

		try {

			// 得到数据

			// rowlist是一行数据
			// 通用名
			String mc = rowlist.get(0);
			// 规格
			String gg = rowlist.get(1);
			// 中标价格
			String zbjg = rowlist.get(2);
			// 生产企业名称
			String scqymc = rowlist.get(3);
			// 交易状态
			String jyzt = rowlist.get(4);
			//管理类别
			String lb = rowlist.get(5);

			/**
			 * 校验字段的合法性
			 */
			if(mc==null||mc.equals("")){
				return "产品名称不能为空";
			}
			
			if(gg==null||gg.equals("")){
				return "产品的规格不能为空";
			}
			
			//中标价格
			if(zbjg==null||!CheckRegex.isNumeric_xs(zbjg)){
				return "中标价格输入不正确！中标价格必须为正数";
			}
			
			if(scqymc==null||scqymc.equals("")){
				return "生产企业名称不能为空";
			}
			
			// 交易状态
			if (jyzt == null || (!jyzt.equals("1") && !jyzt.equals("2"))) {
				return "交易状态输入不正确！请输入1：正常，2：暂停";
			}
			
			if(lb==null||(!lb.equals("00101")&&!lb.equals("00102")&&!lb.equals("00103"))){
				return "管理类输入错误！请输入(00101：I类,00102：II类，00103：III类)";
			}
			
			/**
			 * 因为id和流水号一般都不会重复，所以不用校验 通用名、剂型、规格、转换系数、商品名、根据查询，如果查到，记录重复 自定义查询条件
			 */
			// SCQYMC,  MC, GG, 联合主键一致,唯一性
			YpxxExample ypxxExample = new YpxxExample();
			YpxxExample.Criteria criteria = ypxxExample.createCriteria();
			criteria.andScqymcEqualTo(scqymc);
			//criteria.andSpmcEqualTo(spmc);
			criteria.andMcEqualTo(mc);
			criteria.andGgEqualTo(gg);
			//criteria.andZhxsEqualTo(zhxs);
			List<Ypxx> ypxxs = ypxxMapper.selectByExample(ypxxExample);
			if (ypxxs != null && ypxxs.size() > 0) {
				return "产品的名称、规格、生产企业名称不能同时与数据库中某一条记录重复";
			}

			Ypxx ypxx = new Ypxx();
			ypxx.setId(UUIDBuild.getUUID());
			ypxx.setMc(mc);
			ypxx.setGg(gg);
			ypxx.setZbjg(Float.parseFloat(zbjg));
			ypxx.setScqymc(scqymc);
			ypxx.setJyzt(jyzt);
			ypxx.setLb(lb);
			// 校验调用mapper
			ypxxMapper.insert(ypxx);
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}
		return "success";
	}

}
