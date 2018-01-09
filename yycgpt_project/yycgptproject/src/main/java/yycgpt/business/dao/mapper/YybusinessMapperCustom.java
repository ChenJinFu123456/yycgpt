package yycgpt.business.dao.mapper;

import java.util.List;

import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;

/**
 * 
 * @author Administrator 交易明细查询
 */
public interface YybusinessMapperCustom {
	public List<YyCgdMxCustom> findYyBusinessList(CgdQueryVo cgdQueryVo)
			throws Exception;

	public int findYyBusinessCount(CgdQueryVo cgdQueryVo) throws Exception;

	// 按产品的分类统计
	public List<YyCgdMxCustom> findYyBusinessGroupByYpxxList(
			CgdQueryVo cgdQueryVo) throws Exception;

	public int findYyBusinessGroupByYpxxCount(CgdQueryVo cgdQueryVo)
			throws Exception;

	// 按区域的分类统计
	public List<YyCgdMxCustom> findYyBusinessGroupByAreaList(
			CgdQueryVo cgdQueryVo) throws Exception;
	public int findYyBusinessGroupByAreaCount(
			CgdQueryVo cgdQueryVo) throws Exception;
	
	/*
	 * 医院交易明细产品的统计  
	 *统计参数： 入库量  入库金额   采购量   采购金额
	 *约束条件：年份  医院的id
	 * */  
	public List<YyCgdMxCustom> findYyBusinessListSum(CgdQueryVo cgdQueryVo)
			throws Exception;
}
