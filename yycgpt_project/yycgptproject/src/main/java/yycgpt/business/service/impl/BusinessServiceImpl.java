package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.UsergysMapper;
import yycgpt.base.dao.mapper.UserjdMapper;
import yycgpt.base.dao.mapper.UseryyMapper;
import yycgpt.base.pojo.po.Usergys;
import yycgpt.base.pojo.po.Userjd;
import yycgpt.base.pojo.po.Useryy;
import yycgpt.business.dao.mapper.YybusinessMapperCustom;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	@Autowired
	private UserjdMapper userjdMapper;
	@Autowired
	private UseryyMapper useryyMapper;
	@Autowired
	private UsergysMapper usergysMapper;
	@Autowired
	private YybusinessMapperCustom yybusinessMapperCustom;

	@Override
	public List<YyCgdMxCustom> findYyBusinessList(CgdQueryVo cgdQueryVo,
			String year, String sysid, String groupid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yybusinessMapperCustom.findYyBusinessList(cgdQueryVo);
	}

	@Override
	public int findYyBusinessCount(CgdQueryVo cgdQueryVo, String year,
			String sysid, String groupid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yybusinessMapperCustom.findYyBusinessCount(cgdQueryVo);
	}

	@Override
	public int findYyBusinessGroupByYpxxCount(CgdQueryVo cgdQueryVo,
			String year, String sysid, String groupid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yybusinessMapperCustom
				.findYyBusinessGroupByYpxxCount(cgdQueryVo);
	}

	@Override
	public List<YyCgdMxCustom> findYyBusinessGroupByYpxxList(
			CgdQueryVo cgdQueryVo, String year, String sysid, String groupid)
			throws Exception {

		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yybusinessMapperCustom.findYyBusinessGroupByYpxxList(cgdQueryVo);

	}

	@Override
	public List<YyCgdMxCustom> findYyBusinessGroupByAreaList(
			CgdQueryVo cgdQueryVo, String year, String sysid, String groupid)
			throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yybusinessMapperCustom.findYyBusinessGroupByAreaList(cgdQueryVo);
	}

	@Override
	public int findYyBusinessGroupByAreaCount(CgdQueryVo cgdQueryVo,
			String year, String sysid, String groupid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);
		return yybusinessMapperCustom
				.findYyBusinessGroupByAreaCount(cgdQueryVo);
	}

	@Override
	public List<YyCgdMxCustom> findYyBusinessListSum(CgdQueryVo cgdQueryVo,
			String year, String sysid, String groupid) throws Exception {
		cgdQueryVo = cgdQueryVo != null ? cgdQueryVo : new CgdQueryVo();
		// 监管单位(获得地区)
		if (groupid.equals("2") || groupid.equals("1")) {
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			// 将监督单位的区域通过医院传入查询条件
			String dq = userjd.getDq();
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setDq(dq);
			// 设置管理地区，根据地区查询交易明细
			cgdQueryVo.setUseryy(useryy);
		}

		// 医院(获得医院的id)
		else if (groupid.equals("3")) {
			Useryy useryy = cgdQueryVo.getUseryy();
			useryy = useryy != null ? useryy : new Useryy();
			useryy.setId(sysid);
			cgdQueryVo.setUseryy(useryy);
		}
		// 供货商(获得供应商id)
		else if (groupid.equals("4")) {
			Usergys usergys = cgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			usergys.setId(sysid);
			cgdQueryVo.setUsergys(usergys);
		}
		// 设置年份
		cgdQueryVo.setBusinessyear(year);

		return yybusinessMapperCustom.findYyBusinessListSum(cgdQueryVo);
	}
}
