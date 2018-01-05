package yycgpt.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.UserjdMapper;
import yycgpt.base.dao.mapper.UseryyMapper;
import yycgpt.base.pojo.po.Userjd;
import yycgpt.base.pojo.po.Useryy;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.SystemConfigService;
import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.dao.mapper.YybusinessMapper;
import yycgpt.business.dao.mapper.YycgdMapper;
import yycgpt.business.dao.mapper.YycgdMapperCustom;
import yycgpt.business.dao.mapper.YycgdmxMapper;
import yycgpt.business.dao.mapper.YycgdrkMapper;
import yycgpt.business.pojo.po.Ypxx;
import yycgpt.business.pojo.po.Yybusiness;
import yycgpt.business.pojo.po.Yycgd;
import yycgpt.business.pojo.po.YycgdExample;
import yycgpt.business.pojo.po.Yycgdmx;
import yycgpt.business.pojo.po.YycgdmxExample;
import yycgpt.business.pojo.po.Yycgdrk;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.pojo.vo.YycgdCustom;
import yycgpt.business.pojo.vo.YycgdrkCustom;
import yycgpt.business.service.CgdService;
import yycgpt.utils.MyUtil;
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
	@Autowired
	private UserjdMapper userjdMapper;
	@Autowired
	private YycgdrkMapper yycgdrkMapper;
	@Autowired
	private YybusinessMapper yybusinessMapper;

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

	// 根据产品信息获取产品的流水号
	public String findYpxxBmByYpxxId(String ypxxId) throws Exception {
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxId);
		if (ypxx == null) {
			return "";
		}
		return ypxx.getBm();
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

	// 根据采购单的id和产品的id查询采购单明细
	public Yycgdmx findYyCgdMxByYyCgdIdAndYpxxId(String yycgdid, String ypxxid)
			throws Exception {
		YycgdmxExample yycgdmxExample = new YycgdmxExample();
		YycgdmxExample.Criteria criteria = yycgdmxExample.createCriteria();
		criteria.andYycgdidEqualTo(yycgdid);
		criteria.andYpxxidEqualTo(ypxxid);
		// 设置年份
		String businessyear = yycgdid.substring(0, 4);
		yycgdmxExample.setBusinessyear(businessyear);
		List<Yycgdmx> yycgdmxs = yycgdmxMapper.selectByExample(yycgdmxExample);
		if (yycgdmxs != null && yycgdmxs.size() == 1) {
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
		// 校验采购单明细表的惟一性
		Yycgdmx yycgdmx_1 = this.findYyCgdMxByYyCgdIdAndYpxxId(yycgdid, ypxxid);
		if (yycgdmx_1 != null) {
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
		// 中标价格
		yycgdmx.setZbjg(ypxx.getZbjg());
		// 交易价格
		yycgdmx.setJyjg(ypxx.getZbjg());
		// 采购状态:默认为未确认送货
		yycgdmx.setCgzt("1");
		yycgdmxMapper.insert(yycgdmx);
	}

	@Override
	public void updateYyCgdMx(String yycgdid, String ypxxid, Integer cgl)
			throws Exception {
		// 根据采购单id和产品id获取采购明细
		Yycgdmx yycgdmx = this.findYyCgdMxByYyCgdIdAndYpxxId(yycgdid, ypxxid);
		if (yycgdmx == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 509,
					null));
		}
		// 校验采购量
		if (cgl == null||cgl <= 0) {
			// 请输入大于0的数值、且为整数
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 711,
					new Object[] { this.findYpxxBmByYpxxId(ypxxid) }));
		}
		Yycgdmx yycgdmx_update = new Yycgdmx();
		// 设置年份
		String businessyear = yycgdid.substring(0, 4);
		// 交易价格
		Float jyjg = yycgdmx.getJyjg();
		// 计算采购金额
		Float cgje = jyjg * cgl;
		yycgdmx_update.setCgl(cgl);
		yycgdmx_update.setCgje(cgje);
		yycgdmx_update.setId(yycgdmx.getId());
		yycgdmx_update.setBusinessyear(businessyear);
		// 根据主键更新不为空的字段
		yycgdmxMapper.updateByPrimaryKeySelective(yycgdmx_update);
	}

	@Override
	public void deleteYyCgdMx(String yycgdid, String ypxxid) throws Exception {
		// 根据采购单id和产品id获取采购明细
		Yycgdmx yycgdmx = this.findYyCgdMxByYyCgdIdAndYpxxId(yycgdid, ypxxid);
		if (yycgdmx == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 509,
					null));
		}
		// 获取记录主键
		String yycgdId = yycgdmx.getId();
		// 设置年份
		String businessyear = yycgdid.substring(0, 4);
		YycgdmxExample yycgdmxExample = new YycgdmxExample();
		YycgdmxExample.Criteria criteria = yycgdmxExample.createCriteria();
		criteria.andIdEqualTo(yycgdId);
		yycgdmxExample.setBusinessyear(businessyear);
		yycgdmxMapper.deleteByExample(yycgdmxExample);
	}

	public List<YycgdCustom> findYyCgdList(String useryyid, String year,
			CgdQueryVo cgdQueryVo) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		cgdQueryVo.setBusinessyear(year);
		Useryy useryy = cgdQueryVo.getUseryy();
		if (useryy == null) {
			useryy = new Useryy();
		}
		useryy.setId(useryyid);
		cgdQueryVo.setUseryy(useryy);
		return yycgdMapperCustom.findYyCgdList(cgdQueryVo);
	}

	@Override
	public int findYyCgdCount(String useryyid, String year,
			CgdQueryVo cgdQueryVo) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		cgdQueryVo.setBusinessyear(year);
		Useryy useryy = cgdQueryVo.getUseryy();
		if (useryy == null) {
			useryy = new Useryy();
		}
		useryy.setId(useryyid);
		cgdQueryVo.setUseryy(useryy);
		return yycgdMapperCustom.findYyCgdCount(cgdQueryVo);
	}

	@Override
	public void saveYyCgdSubmitStatus(String yycgdid) throws Exception {
		// 采购单为未审核或者不同过方可提交
		Yycgd yycgd = this.findYyCgdById(yycgdid);
		if (yycgd == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 501,
					null));
		}
		String zt = yycgd.getZt();
		if (!zt.equals("1") && !zt.equals("4")) {
			// 采购单只有在未提交和审核不通过时方可提交
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 502,
					null));
		}
		// 采购单必须包括采购产品明细方可提交
		List<YyCgdMxCustom> yycgdMxList = this.findYyCgdMxListByYyCgdId(
				yycgdid, null);
		if (yycgdMxList == null || yycgdMxList.size() <= 0) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 504,
					null));
		}

		// 遍历判断采购单下的产品信息是否完整
		for (YyCgdMxCustom yyCgdMxCustom : yycgdMxList) {
			// 采购量
			Integer cgl = yyCgdMxCustom.getCgl();
			// 采购金额
			Float cgje = yyCgdMxCustom.getCgje();
			if (cgl == null || cgje == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						505, new Object[] { yyCgdMxCustom.getBm() }));
			}
		}

		// 设置年份
		String businessyear = yycgdid.substring(0, 4);
		// 更新状态和提交时间
		Yycgd yycgd_update = new Yycgd();
		// 设置主键
		yycgd_update.setId(yycgd.getId());
		// 已经提交未审核
		yycgd_update.setZt("2");
		// 时间
		yycgd_update.setTjtime(MyUtil.getNowDate());
		yycgd_update.setBusinessyear(businessyear);

		yycgdMapper.updateByPrimaryKeySelective(yycgd_update);
	}

	// 采购单审核列表
	@Override
	public List<YycgdCustom> findCheckYyCgdList(CgdQueryVo cgdQueryVo,
			String year, String userjdid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置年份
		cgdQueryVo.setBusinessyear(year);

		// 采购单的状态（只查询状态为2的）
		String zt = "2";
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setZt(zt);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		// 根据监督单位id查询监督单位管理区域
		Userjd userjd = userjdMapper.selectByPrimaryKey(userjdid);
		String dq = userjd.getDq();

		// 通过医院传入监管单位的管理地区
		Useryy useryy = cgdQueryVo.getUseryy();
		if (useryy == null) {
			useryy = new Useryy();
		}
		useryy.setDq(dq);
		cgdQueryVo.setUseryy(useryy);

		return yycgdMapperCustom.findYyCgdList(cgdQueryVo);
	}

	@Override
	public int findCheckYyCgdCount(CgdQueryVo cgdQueryVo, String year,
			String userjdid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置年份
		cgdQueryVo.setBusinessyear(year);

		// 采购单的状态（只查询状态为2的）
		String zt = "2";
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setZt(zt);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		// 根据监督单位id查询监督单位管理区域
		Userjd userjd = userjdMapper.selectByPrimaryKey(userjdid);
		String dq = userjd.getDq();

		// 通过医院传入监管单位的管理地区
		Useryy useryy = cgdQueryVo.getUseryy();
		if (useryy == null) {
			useryy = new Useryy();
		}
		useryy.setDq(dq);
		cgdQueryVo.setUseryy(useryy);

		return yycgdMapperCustom.findYyCgdCount(cgdQueryVo);
	}

	// 采购单审核提交
	@Override
	public void saveYycgdCheckStatus(String yycgdid, YycgdCustom yycgdCustom)
			throws Exception {
		// 根据采购单id获取采购单信息
		Yycgd yycgd = this.findYyCgdById(yycgdid);
		if (yycgd == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 501,
					null));
		}
		// 采购单状态为2
		String zt = yycgd.getZt();
		if (!zt.equals("2")) {
			// 采购单只有在审核通过和审核不通过时方可提交审核结果
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 514,
					new Object[] { yycgdid }));
		}
		if (yycgdCustom == null
				|| yycgdCustom.getZt() == null
				|| (!yycgdCustom.getZt().equals("3") && !yycgdCustom.getZt()
						.equals("4"))) {
			// 审核结果选择不正确
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 513,
					new Object[] { yycgdCustom.getBm() }));
		}

		Yycgd yycgd_update = new Yycgd();
		yycgd_update.setId(yycgdid);
		// 审核状态
		yycgd_update.setZt(yycgdCustom.getZt());
		// 审核时间
		yycgd_update.setShtime(MyUtil.getNowDate());
		// 审核意见
		if (yycgdCustom != null && yycgdCustom.getShyj() != null) {
			yycgd_update.setShyj(yycgdCustom.getShyj());
		}
		// 设置年份
		String businessyear = yycgdid.substring(0, 4);
		yycgd_update.setBusinessyear(businessyear);

		yycgdMapper.updateByPrimaryKeySelective(yycgd_update);

		// 采购明细聚合
		if (yycgdCustom.getZt().equals("3")) {
			// 如果审核通过进行数据聚合
			List<YyCgdMxCustom> yyCgdMxList = this.findYyCgdMxListByYyCgdId(
					yycgdid, null);
			// 将明细记录插入交易明细表中
			for (YyCgdMxCustom yyCgdMxCustom : yyCgdMxList) {
				// 交易明细表
				Yybusiness yybusiness = new Yybusiness();
				// 主键
				yybusiness.setId(UUIDBuild.getUUID());
				// 采购单id
				yybusiness.setYycgdid(yycgdid);
				// 产品信息id
				yybusiness.setYpxxid(yyCgdMxCustom.getId());
				// 医院id
				yybusiness.setUseryyid(yyCgdMxCustom.getUseryyid());
				// 中标价格
				yybusiness.setZbjg(yyCgdMxCustom.getZbjg());
				// 交易价格
				yybusiness.setJyjg(yyCgdMxCustom.getJyjg());
				// 采购量
				yybusiness.setCgl(yyCgdMxCustom.getCgl());
				// 采购金额
				yybusiness.setCgje(yyCgdMxCustom.getCgje());
				// 采购状态
				yybusiness.setCgzt(yyCgdMxCustom.getCgzt());
				// 供应商id
				yybusiness.setUsergysid(yyCgdMxCustom.getUsergysid());
				// 年份
				yybusiness.setBusinessyear(businessyear);

				yybusinessMapper.insert(yybusiness);
			}

		}

	}

	@Override
	public List<YyCgdMxCustom> findDisposeYyCgdList(String usergysid,
			String year, CgdQueryVo cgdQueryVo) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置年份
		cgdQueryVo.setBusinessyear(year);

		// 供应商只可以查询自己供应的产品
		// 设置供应商的id
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setUsergysid(usergysid);
		// 采购单明细状态为为确认送货
		String cgzt = "1";
		yyCgdMxCustom.setCgzt(cgzt);
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);

		// 采购单状态为审核通过
		String zt = "3";
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setZt(zt);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		return yycgdMapperCustom.findYyCgdMxList(cgdQueryVo);
	}

	@Override
	public int findDisposeYyCgdCount(String usergysid, String year,
			CgdQueryVo cgdQueryVo) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置年份
		cgdQueryVo.setBusinessyear(year);

		// 供应商只可以查询自己供应的产品
		// 设置供应商的id
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setUsergysid(usergysid);
		// 采购单明细状态为为确认送货
		String cgzt = "1";
		yyCgdMxCustom.setCgzt(cgzt);
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);

		// 采购单状态为审核通过
		String zt = "3";
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setZt(zt);
		cgdQueryVo.setYycgdCustom(yycgdCustom);
		return yycgdMapperCustom.findYyCgdMxCount(cgdQueryVo);
	}

	@Override
	public void saveSendStatus(String yycgdid, String ypxxid) throws Exception {
		Yycgdmx yycgdmx = this.findYyCgdMxByYyCgdIdAndYpxxId(yycgdid, ypxxid);
		if (yycgdmx == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 509,
					null));
		}
		// 采购状态为为确认发货才能发货
		String cgzt = yycgdmx.getCgzt();
		if (!cgzt.equals("1")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 712,
					null));
		}
		// 设置为已经发货
		yycgdmx.setCgzt("2");
		String year = yycgdid.substring(0, 4);
		yycgdmx.setBusinessyear(year);
		yycgdmxMapper.updateByPrimaryKey(yycgdmx);
	}

	@Override
	public int findYyCgdReceiveCount(String year, String useryyid,
			CgdQueryVo cgdQueryVo) throws Exception {
		// 医院只查询自己的采购单信息
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		// 设置医院的id
		yycgdCustom.setUseryyid(useryyid);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		// 设置产品的采购状态为已经发货
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setCgzt("2");
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);

		// 设置年份
		cgdQueryVo.setBusinessyear(year);

		return yycgdMapperCustom.findYyCgdMxCount(cgdQueryVo);
	}

	@Override
	public List<YyCgdMxCustom> findYyCgdReceiveList(String year,
			String useryyid, CgdQueryVo cgdQueryVo) throws Exception {
		// 医院只查询自己的采购单信息
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		YycgdCustom yycgdCustom = cgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		// 设置医院的id
		yycgdCustom.setUseryyid(useryyid);
		cgdQueryVo.setYycgdCustom(yycgdCustom);

		// 设置产品的采购状态为已经发货
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setCgzt("2");
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);

		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yycgdMapperCustom.findYyCgdMxList(cgdQueryVo);
	}

	@Override
	public void saveYyCgdRk(String yycgdid, String ypxxid,
			YycgdrkCustom yycgdrkCustom) throws Exception {
		// 获取年份
		String year = yycgdid.substring(0, 4);
		// 根据采购单号和产品信息id找到采购单明细
		Yycgdmx yycgdmx = this.findYyCgdMxByYyCgdIdAndYpxxId(yycgdid, ypxxid);
		if (yycgdmx == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 509,
					null));
		}
		// 产品流水号
		String ypxxbm = this.findYpxxBmByYpxxId(ypxxid);

		// 采购单为已经发货方可以入库
		// 采购状态
		String cgzt = yycgdmx.getCgzt();
		if (!cgzt.equals("2")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 713,
					null));
		}

		/**
		 * 约束条件
		 */
		// 1、入库量不能大于采购量
		// 入库量
		Integer rkl = yycgdrkCustom.getRkl();

		// 采购量
		int cgl = yycgdmx.getCgl();
		if (rkl == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 714,
					new Object[] { ypxxbm }));
		} else if (rkl > cgl) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 519,
					new Object[] { ypxxbm }));
		}

		// 交易价格
		Float jyjg = yycgdmx.getJyjg();
		// 入库金额
		Float rkje = jyjg * rkl;

		// 发票号或入库单号
		String rkdh = yycgdrkCustom.getRkdh();
		if (rkdh == null || rkdh.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 715,
					new Object[] { ypxxbm }));
		}
		// 产品批号
		String ypph = yycgdrkCustom.getYpph();
		if (ypph.equals("") || ypph == null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 716,
					new Object[] { ypxxbm }));
		}

		// 有效期
		Float ypyxq = yycgdrkCustom.getYpyxq();
		if (ypyxq == null || ypyxq <= 0) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 717,
					new Object[] { ypxxbm }));
		}
		// 更新采购单明细中的采购状态为已经入库
		yycgdmx.setCgzt("3");
		// 设置年份
		yycgdmx.setBusinessyear(year);
		yycgdmxMapper.updateByPrimaryKey(yycgdmx);

		// 向医院入库信息表中插入一条数据
		Yycgdrk yycgdrk = new Yycgdrk();
		yycgdrk.setId(UUIDBuild.getUUID());// 主键
		// 将页面提交的数据复制
		// BeanUtils.copyProperties(yycgdrkCustom, yycgdrk);
		// 入库量
		yycgdrk.setRkl(rkl);
		// 入库金额
		yycgdrk.setRkje(rkje);
		// 发票号或入库单号
		yycgdrk.setRkdh(rkdh);
		// 产品批号
		yycgdrk.setYpph(ypph);
		// 产品有效期
		yycgdrk.setYpyxq(ypyxq);
		// 年份
		yycgdrk.setBusinessyear(year);
		// 采购单id
		yycgdrk.setYycgdid(yycgdid);
		// 产品id
		yycgdrk.setYpxxid(ypxxid);
		// 入库时间
		yycgdrk.setRktime(MyUtil.getNowDate());
		// 采购状态
		yycgdrk.setCgzt("3");

		yycgdrkMapper.insert(yycgdrk);
	}

	@Override
	public List<YyCgdMxCustom> findYyCgdMxListSum(String yycgdid,
			CgdQueryVo cgdQueryVo) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 设置采购单的id
		YyCgdMxCustom yyCgdMxCustom = cgdQueryVo.getYyCgdMxCustom();
		if (yyCgdMxCustom == null) {
			yyCgdMxCustom = new YyCgdMxCustom();
		}
		yyCgdMxCustom.setYycgdid(yycgdid);
		cgdQueryVo.setYyCgdMxCustom(yyCgdMxCustom);
		// 设置年份
		String year = yycgdid.substring(0, 4);
		cgdQueryVo.setBusinessyear(year);
		return yycgdMapperCustom.findYyCgdMxListSum(cgdQueryVo);
	}
}
