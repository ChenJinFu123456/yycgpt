package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.dao.mapper.GysypmlControlMapper;
import yycgpt.business.dao.mapper.GysypmlMapper;
import yycgpt.business.dao.mapper.GysypmlMapperCustom;
import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.pojo.po.Gysypml;
import yycgpt.business.pojo.po.GysypmlControl;
import yycgpt.business.pojo.po.GysypmlControlExample;
import yycgpt.business.pojo.po.GysypmlExample;
import yycgpt.business.pojo.po.Ypxx;
import yycgpt.business.pojo.vo.GysypmlCustom;
import yycgpt.business.pojo.vo.GysypmlQueryVo;
import yycgpt.business.service.YpmlService;
import yycgpt.utils.UUIDBuild;

public class YpmlServiceImpl implements YpmlService {

	@Autowired
	private GysypmlMapperCustom gysypmlMapperCustom;
	@Autowired
	private GysypmlMapper gysypmlMapper;
	@Autowired
	private YpxxMapper ypxxMapper;
	@Autowired
	private GysypmlControlMapper gysypmlControlMapper;
	@Autowired
	private SystemConfigService systemConfigService;

	@Override
	public List<GysypmlCustom> findGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();
		// 设置数据范围权限
		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		gysypmlCustom.setUsergysid(usergysId);
		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findGysypmlList(gysypmlQueryVo);
	}

	@Override
	public int findGysypmlCount(String usergysId, GysypmlQueryVo gysypmlQueryVo)
			throws Exception {
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();
		// 设置数据范围权限
		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		gysypmlCustom.setUsergysid(usergysId);
		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findGysypmlCount(gysypmlQueryVo);
	}

	@Override
	public List<GysypmlCustom> findAddGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();
		// 设置数据范围权限
		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		gysypmlCustom.setUsergysid(usergysId);
		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findAddGysypmlList(gysypmlQueryVo);
	}

	@Override
	public int findAddGysypmlCount(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();
		// 设置数据范围权限
		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		gysypmlCustom.setUsergysid(usergysId);
		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findAddGysypmlCount(gysypmlQueryVo);
	}

	// 抽取：根据供应商id和产品id查询供应商产品目录
	public Gysypml findGysypmlByGysIdAndYpxxId(String userGysId, String ypxxId)
			throws Exception {
		// 先查询供应商的id和产品id查询去重
		GysypmlExample gysypmlExample = new GysypmlExample();
		GysypmlExample.Criteria criteria = gysypmlExample.createCriteria();
		// 设置查询条件
		criteria.andUsergysidEqualTo(userGysId);
		criteria.andYpxxidEqualTo(ypxxId);
		List<Gysypml> gysypmls = gysypmlMapper.selectByExample(gysypmlExample);
		if (gysypmls != null && gysypmls.size() == 1) {
			return gysypmls.get(0);
		}
		return null;
	}

	// 抽取：根据供应商id和产品id查询供应商产品控制目录
	public GysypmlControl findGysypmlControlByGysIdAndYpxxId(String userGysId,
			String ypxxId) throws Exception {
		// 先查询供应商的id和产品id查询去重
		GysypmlControlExample gysypmlControlExample = new GysypmlControlExample();
		GysypmlControlExample.Criteria criteria = gysypmlControlExample
				.createCriteria();
		// 设置查询条件
		criteria.andUsergysidEqualTo(userGysId);
		criteria.andYpxxidEqualTo(ypxxId);
		List<GysypmlControl> gysypmlControls = gysypmlControlMapper
				.selectByExample(gysypmlControlExample);
		if (gysypmlControls != null && gysypmlControls.size() == 1) {
			return gysypmlControls.get(0);
		}
		return null;
	}
	
	@Override
	public void insertGysYpml(String userGysId, String ypxxId) throws Exception {
		// 约束条件
		Gysypml gysypml = this.findGysypmlByGysIdAndYpxxId(userGysId, ypxxId);
		if (gysypml != null) {
			// 说明已经存在
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 401,
					null));
		}
		// 根据产品信息id查询产品信息(交易状态)
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxId);
		// 产品信息不存在
		if (ypxx == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 316,
					null));
		}
		String jyzt = ypxx.getJyzt();
		if (jyzt.equals("0")) {
			// 产品的交易状态为暂停
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 403,
					new Object[] { ypxx.getBm(), ypxx.getMc() }));
		}

		// 向gysypml插入记录
		Gysypml gysypml_insert = new Gysypml();
		gysypml_insert.setId(UUIDBuild.getUUID());
		gysypml_insert.setUsergysid(userGysId);
		gysypml_insert.setYpxxid(ypxxId);
		gysypmlMapper.insert(gysypml_insert);

		// 向gysypml_control插入记录
		GysypmlControl gysypmlControl = this
				.findGysypmlControlByGysIdAndYpxxId(userGysId, ypxxId);
		// 如果表中没有数据，则要插入数据
		if (gysypmlControl == null) {

			// 从系统的参数配置表来取控制状态的参数配置值
			String control = systemConfigService.findBasicinfoById("00101")
					.getValue();

			GysypmlControl gysypmlControl_insert = new GysypmlControl();
			gysypmlControl_insert.setId(UUIDBuild.getUUID());
			gysypmlControl_insert.setYpxxid(ypxxId);
			gysypmlControl_insert.setUsergysid(userGysId);
			// 使用
			gysypmlControl_insert.setControl(control);
			gysypmlControlMapper.insert(gysypmlControl_insert);
		}
	}

	
	
	@Override
	public void deleteGysYpml(String userGysId, String ypxxId) throws Exception {
		//先查询，后根据主键删除
		Gysypml gysypml = this.findGysypmlByGysIdAndYpxxId(userGysId, ypxxId);
		//此产品在供货目录中不存在
		if(gysypml==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 402,
					null));
		}
		//获取产品主键
		String gysYpmlId = gysypml.getId();
		//根据主键删除记录
		gysypmlMapper.deleteByPrimaryKey(gysYpmlId);
	}

}
