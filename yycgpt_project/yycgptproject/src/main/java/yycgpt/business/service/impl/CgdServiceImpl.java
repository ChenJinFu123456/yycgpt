package yycgpt.business.service.impl;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.deser.std.FromStringDeserializer.UUIDDeserializer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.UseryyMapper;
import yycgpt.base.pojo.po.Useryy;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.dao.mapper.YycgdMapper;
import yycgpt.business.dao.mapper.YycgdMapperCustom;
import yycgpt.business.dao.mapper.YycgdmxMapper;
import yycgpt.business.pojo.po.Ypxx;
import yycgpt.business.pojo.po.Yycgd;
import yycgpt.business.pojo.po.YycgdExample;
import yycgpt.business.pojo.po.Yycgdmx;
import yycgpt.business.pojo.po.YycgdmxExample;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.pojo.vo.YycgdCustom;
import yycgpt.business.service.CgdService;
import yycgpt.utils.UUIDBuild;

public class CgdServiceImpl implements CgdService {
	@Autowired
	private YycgdMapper yycgdMapper;
	@Autowired
	private YycgdMapperCustom yycgdMapperCustom;
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private UseryyMapper useryyMapper;
	@Autowired
	private YpxxMapper ypxxMapper;
	@Autowired
	private YycgdmxMapper yycgdmxMapper;
	@Override
	public String insertYycgd(YycgdCustom yycgdCustom, String userYyId,
			String year) throws Exception {
		String bm = yycgdMapperCustom.getYycgdBm(year);
		// 创建主键（流水号）
		yycgdCustom.setId(bm);
		// 单号
		yycgdCustom.setBm(bm);
		// 创建采购单的医院
		yycgdCustom.setUseryyid(userYyId);
		// 创建时间
		yycgdCustom.setCjtime(new Date());
		// 状态默认为未提交
		yycgdCustom.setZt("1");
		// 设置year
		yycgdCustom.setBusinessyear(year);
		// 插入
		yycgdMapper.insert(yycgdCustom);
		return bm;
	}

	@Override
	public YycgdCustom findYyCgdById(String cgdId) throws Exception {
		// 从采购单中截取年份
		String year = cgdId.substring(0, 4);
		// 不能用主键id，因为方法中没有年份
		YycgdExample yycgdExample = new YycgdExample();
		YycgdExample.Criteria criteria = yycgdExample.createCriteria();
		criteria.andIdEqualTo(cgdId);
		// 通过yycgdExample传入年份
		yycgdExample.setBusinessyear(year);
		List<Yycgd> list = yycgdMapper.selectByExample(yycgdExample);
		Yycgd yycgd = null;
		YycgdCustom yycgdCustom = new YycgdCustom();
		if (list != null && list.size() == 1) {
			yycgd = list.get(0);
			// 将yycgd中的属性值拷贝到yycgdCustom
			BeanUtils.copyProperties(yycgd, yycgdCustom);
		} else {// 抛出异常
			ResultUtil.createFail(Config.MESSAGE, 501, null);
		}
		// 状态名称
		String zt = yycgd.getZt();
		// 根据状态的代码找出名称
		String yycgdztmc = systemConfigService
				.findDictinfoByDictcode("010", zt).getInfo();
		yycgdCustom.setYycgdztmc(yycgdztmc);
		return yycgdCustom;
	}

	@Override
	public void updateYyCgd(String id, YycgdCustom yycgdCustom)
			throws Exception {
		// 从采购单中截取年份
		String year = id.substring(0, 4);

		// 从数据库中查询采购单的信息
		YycgdCustom yycgdCustom_old = this.findYyCgdById(id);
		// 向对象中设置修改的值
		yycgdCustom_old.setLxr(yycgdCustom.getLxr());
		yycgdCustom_old.setLxdh(yycgdCustom.getLxdh());
		yycgdCustom_old.setMc(yycgdCustom.getMc());
		yycgdCustom_old.setBz(yycgdCustom.getBz());
		// 设置年份
		yycgdCustom_old.setBusinessyear(year);
		yycgdMapper.updateByPrimaryKey(yycgdCustom_old);
	}

	@Override
	public List<YyCgdMxCustom> findYyCgdMxListByYyCgdId(String YyCgdId,
			CgdQueryVo cgdQueryVo) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置固定的业务参数
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setYycgdid(YyCgdId);
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);
		// 设置年份
		String businessyear = YyCgdId.substring(0, 4);
		cgdQueryVo.setBusinessyear(businessyear);

		return yycgdMapperCustom.findYyCgdMxList(cgdQueryVo);
	}

	@Override
	public int findYyCgdMxCountByYyCgdId(String yyCgdId, CgdQueryVo cgdQueryVo)
			throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置固定的业务参数
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setYycgdid(yyCgdId);
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);
		// 设置年份
		String businessyear = yyCgdId.substring(0, 4);
		cgdQueryVo.setBusinessyear(businessyear);
		return yycgdMapperCustom.findYyCgdMxCount(cgdQueryVo);
	}

	@Override
	public List<YyCgdMxCustom> findAddYpcgdmxList(String userYyId,
			String yyCgdId, CgdQueryVo cgdQueryVo) throws Exception {
		// 根据医院的id得到区域id
		Useryy useryy = useryyMapper.selectByPrimaryKey(userYyId);
		String dq = useryy.getDq();
		// 向cgdQueryVo中设置业务参数
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 医院的地区
		Useryy useryy_1 = cgdQueryVo.getUseryy();
		if (useryy_1 == null) {
			useryy_1 = new Useryy();
		}
		useryy_1.setDq(dq);
		cgdQueryVo.setUseryy(useryy_1);

		// 采购单id
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setId(yyCgdId);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		// 设置年份
		String businessyear = yyCgdId.substring(0, 4);
		cgdQueryVo.setBusinessyear(businessyear);

		return yycgdMapperCustom.findAddYpcgdmxList(cgdQueryVo);
	}

	@Override
	public int findAddYpcgdmxCount(String userYyId, String yyCgdId,
			CgdQueryVo cgdQueryVo) throws Exception {
		// 根据医院的id得到区域id
		Useryy useryy = useryyMapper.selectByPrimaryKey(userYyId);
		String dq = useryy.getDq();
		// 向cgdQueryVo中设置业务参数
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 医院的地区
		Useryy useryy_1 = cgdQueryVo.getUseryy();
		if (useryy_1 == null) {
			useryy_1 = new Useryy();
		}
		useryy_1.setDq(dq);
		cgdQueryVo.setUseryy(useryy_1);

		// 采购单id
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setId(yyCgdId);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		// 设置年份
		String businessyear = yyCgdId.substring(0, 4);
		cgdQueryVo.setBusinessyear(businessyear);
		return yycgdMapperCustom.findAddYpcgdmxCount(cgdQueryVo);
	}

	//根据采购单的id和产品的id查询采购单明细
	public  Yycgdmx findYyCgdMxByYyCgdIdAndYpxxId(String yycgdid, String ypxxid)throws Exception{
		YycgdmxExample yycgdmxExample = new YycgdmxExample();
		YycgdmxExample.Criteria criteria = yycgdmxExample.createCriteria();
		criteria.andYycgdidEqualTo(yycgdid);
		criteria.andYpxxidEqualTo(ypxxid);
		//设置年份
		String businessyear = yycgdid.substring(0, 4);
		yycgdmxExample.setBusinessyear(businessyear);
		List<Yycgdmx> yycgdmxs = yycgdmxMapper.selectByExample(yycgdmxExample);
		if(yycgdmxs!=null&&yycgdmxs.size()==1){
			return yycgdmxs.get(0);
		}
		
		return null;
	}
	
	@Override
	public void insertYycgdmx(String yycgdid, String ypxxid, String usergysid)
			throws Exception {
		// 设置年份
		String businessyear = yycgdid.substring(0, 4);
		// 根据产品的id得到产品的相关信息
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxid);
		if (ypxx == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 316,
					null));
		}
		//校验采购单明细表的惟一性
		Yycgdmx yycgdmx_1 = this.findYyCgdMxByYyCgdIdAndYpxxId(yycgdid, ypxxid);
		if(yycgdmx_1!=null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 508,
					null));
		}
		
		// 对应采购产品明细表的字段进行添加纪录

		Yycgdmx yycgdmx = new Yycgdmx();
		// 主键
		yycgdmx.setId(UUIDBuild.getUUID());
		// 年份
		yycgdmx.setBusinessyear(businessyear);
		// 采购单的id
		yycgdmx.setYycgdid(yycgdid);
		// 产品信息id
		yycgdmx.setYpxxid(ypxxid);
		// 供应商id
		yycgdmx.setUsergysid(usergysid);
		//中标价格
		yycgdmx.setZbjg(ypxx.getZbjg());
		//交易价格
		yycgdmx.setJyjg(ypxx.getZbjg());
		//采购状态:默认为未确认送货
		yycgdmx.setCgzt("1");
		yycgdmxMapper.insert(yycgdmx);
	}

}
