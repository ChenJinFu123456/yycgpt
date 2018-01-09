package yycgpt.business.service;

import java.util.List;

import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;

public interface BusinessService {
	/**
	 * 交易明细查询
	 * 
	 * @param cgdQueryVo
	 * @param year
	 * @param sysid
	 *            单位的id
	 * @param groupid
	 *            用户类型
	 * @return
	 * @throws Exception
	 */
	public List<YyCgdMxCustom> findYyBusinessList(CgdQueryVo cgdQueryVo,
			String year, String sysid, String groupid) throws Exception;

	public int findYyBusinessCount(CgdQueryVo cgdQueryVo, String year,
			String sysid, String groupid) throws Exception;

	// 按产品的分裂统计
	public List<YyCgdMxCustom> findYyBusinessGroupByYpxxList(
			CgdQueryVo cgdQueryVo, String year, String sysid, String groupid)
			throws Exception;

	public int findYyBusinessGroupByYpxxCount(CgdQueryVo cgdQueryVo,
			String year, String sysid, String groupid) throws Exception;

	// 按区域的分类统计
	public List<YyCgdMxCustom> findYyBusinessGroupByAreaList(
			CgdQueryVo cgdQueryVo, String year, String sysid, String groupid)
			throws Exception;

	public int findYyBusinessGroupByAreaCount(
			CgdQueryVo cgdQueryVo, String year, String sysid, String groupid)
			throws Exception;
	
	
	/*
	 * 医院交易明细产品的统计  
	 *统计参数： 入库量  入库金额   采购量   采购金额
	 *约束条件：年份  医院的id
	 * */  
	public List<YyCgdMxCustom> findYyBusinessListSum(CgdQueryVo cgdQueryVo, String year,
			String sysid,String groupid)
			throws Exception;
}
