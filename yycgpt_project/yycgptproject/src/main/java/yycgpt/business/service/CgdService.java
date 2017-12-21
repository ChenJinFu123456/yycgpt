package yycgpt.business.service;

import java.util.List;

import yycgpt.business.pojo.po.Yycgdmx;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.pojo.vo.YycgdCustom;

/**
 * 
 * @author Administrator
 *采购单管理
 */
public interface CgdService {
	//创建采购单信息,并将采购单的基本信息放回
	public String insertYycgd(YycgdCustom yycgdCustom,String userYyId,String year)throws Exception;
	//根据采购单的id查询采购单的信息
	public YycgdCustom findYyCgdById(String cgdId)throws Exception;
	//修改采购单后的保存
	public void updateYyCgd(String id,YycgdCustom yycgdCustom)throws Exception;
	//采购单明细下产品目录的查询
	public List<YyCgdMxCustom> findYyCgdMxListByYyCgdId(String YyCgdId,CgdQueryVo cgdQueryVo)throws Exception;
	public int findYyCgdMxCountByYyCgdId(String YyCgdId,CgdQueryVo cgdQueryVo)throws Exception;
	//医院采购单添加产品目录
	/**
	 * 
	 * @param userYyId 医院的id，根据医院的id查询医院的区域dq
	 * @param yyCgdId 采购单的id
	 * @param cgdQueryVo 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<YyCgdMxCustom> findAddYpcgdmxList(String userYyId,String yyCgdId,CgdQueryVo cgdQueryVo)throws Exception;
	public int findAddYpcgdmxCount(String userYyId,String yyCgdId,CgdQueryVo cgdQueryVo)throws Exception;
	//采购产品的添加
	/**
	 * 
	 * @param yycgdid 采购单的id
	 * @param ypxxid 产品id
	 * @param usergysid 供应此产品供应商id
	 * @throws Exception
	 */
	public void insertYycgdmx(String yycgdid,String ypxxid,String usergysid)throws Exception;
	//根据采购单的id和产品的id查询采购单明细
	public  Yycgdmx findYyCgdMxByYyCgdIdAndYpxxId(String yycgdid, String ypxxid)throws Exception;
}
