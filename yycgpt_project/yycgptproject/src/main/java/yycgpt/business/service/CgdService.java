package yycgpt.business.service;

import java.util.List;

import yycgpt.business.pojo.po.Yycgdmx;
import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;
import yycgpt.business.pojo.vo.YycgdCustom;
import yycgpt.business.pojo.vo.YycgdrkCustom;

/**
 * 
 * @author Administrator
 *采购单管理
 */
public interface CgdService {
	//根据产品信息获取产品的流水号
	public String findYpxxBmByYpxxId(String ypxxId)throws Exception;
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
	
	//根据采购单id和产品id更新采购量和采购金额、采购金额
	public void updateYyCgdMx(String yycgdid,String ypxxid,Integer cgl)throws Exception;
	//根据采购单id和产品id删除采购单下的产品
	public void deleteYyCgdMx(String yycgdid,String ypxxid)throws Exception;
	
	//医院采购单目录查询
	public List<YycgdCustom> findYyCgdList(String useryyid,String year,CgdQueryVo cgdQueryVo)throws Exception;
	public int findYyCgdCount(String useryyid,String year,CgdQueryVo cgdQueryVo)throws Exception;
	
	
	//采购单的提交
	public void saveYyCgdSubmitStatus(String yycgdid)throws Exception;
	/**
	 * 
	 * @param year 
	 * @param userjdid 监督单位的id
	 * @return
	 * @throws Exception
	 */
	//采购单的审核列表
	public List<YycgdCustom> findCheckYyCgdList(CgdQueryVo cgdQueryVo,String year,String userjdid)throws Exception;
	public int findCheckYyCgdCount(CgdQueryVo cgdQueryVo,String year,String userjdid)throws Exception;
	/**
	 * 
	 * @param yycgdid 采购单的id
	 * @param yycgdCustom 封装采购单审核意见和审核结果信息对象
	 * @throws Exception
	 */
	//采购单的审核结果提交
	public  void saveYycgdCheckStatus(String yycgdid,YycgdCustom yycgdCustom)throws Exception;
	
	//采购单受理列表查询
	/**
	 * 
	 * @param usergysid 供应商id
	 * @param year 年份
	 * @param cgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YyCgdMxCustom> findDisposeYyCgdList(String usergysid,String year,CgdQueryVo cgdQueryVo)throws Exception;
	public int findDisposeYyCgdCount(String usergysid,String year,CgdQueryVo cgdQueryVo)throws Exception;
	//供应商确认发货
	public  void saveSendStatus(String yycgdid,String ypxxid)throws Exception;
	
	//查询医院待入库的列表
	public  List<YyCgdMxCustom> findYyCgdReceiveList(String year,String useryyid,CgdQueryVo cgdQueryVo)throws Exception;
	public  int findYyCgdReceiveCount(String year,String useryyid,CgdQueryVo cgdQueryVo)throws Exception;
	
	//医院提交入库信息
	public void saveYyCgdRk(String yycgdid,String ypxxid,YycgdrkCustom yycgdrkCustom)throws Exception;
	//采购单明细统计
	public List<YyCgdMxCustom> findYyCgdMxListSum(String yycgdid,CgdQueryVo cgdQueryVo)throws Exception;
}
