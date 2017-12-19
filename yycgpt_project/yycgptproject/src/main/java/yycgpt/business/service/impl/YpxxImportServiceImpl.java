package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.pojo.po.Ypxx;
import yycgpt.business.pojo.po.YpxxExample;
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
			// 剂型
			String jx = rowlist.get(1);
			// 规格
			String gg = rowlist.get(2);
			// 转换系数
			String zhxs = rowlist.get(3);
			// 中标价格
			String zbjg = rowlist.get(4);
			// 生产企业名称
			String scqymc = rowlist.get(5);
			// 商品名称
			String spmc = rowlist.get(6);
			// 交易状态
			String jyzt = rowlist.get(7);

			/**
			 * 校验字段的合法性
			 */
			// 中标价格
			if (jyzt == null || (!jyzt.equals("1") && !jyzt.equals("2"))) {
				return "交易状态输入不正确！请输入1：正常，2：暂停";
			}
			/**
			 * 因为id和流水号一般都不会重复，所以不用校验 通用名、剂型、规格、转换系数、商品名、根据查询，如果查到，记录重复 自定义查询条件
			 */
			// SCQYMC, SPMC, MC, JX, GG, ZHXS联合主键一致
			YpxxExample ypxxExample = new YpxxExample();
			YpxxExample.Criteria criteria = ypxxExample.createCriteria();
			criteria.andScqymcEqualTo(scqymc);
			criteria.andSpmcEqualTo(spmc);
			criteria.andMcEqualTo(mc);
			criteria.andGgEqualTo(gg);
			criteria.andZhxsEqualTo(zhxs);
			List<Ypxx> ypxxs = ypxxMapper.selectByExample(ypxxExample);
			if (ypxxs != null && ypxxs.size() > 0) {
				return "产品的通用名、规格、商品名称、生产企业名称、转换系数不能同时与数据库中某一条记录重复";
			}

			Ypxx ypxx = new Ypxx();
			ypxx.setId(UUIDBuild.getUUID());
			ypxx.setMc(mc);
			ypxx.setJx(jx);
			ypxx.setGg(gg);
			ypxx.setZhxs(zhxs);
			ypxx.setZbjg(Float.parseFloat(zbjg));
			ypxx.setScqymc(scqymc);
			ypxx.setSpmc(spmc);
			ypxx.setJyzt(jyzt);

			// 校验调用mapper
			ypxxMapper.insert(ypxx);
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}
		return "success";
	}

}
