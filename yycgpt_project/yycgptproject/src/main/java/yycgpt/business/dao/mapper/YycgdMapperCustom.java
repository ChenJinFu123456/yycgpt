package yycgpt.business.dao.mapper;

import java.util.List;

import yycgpt.business.pojo.vo.CgdQueryVo;
import yycgpt.business.pojo.vo.YyCgdMxCustom;

public interface YycgdMapperCustom {
	//采购单编号生成
	public String getYycgdBm(String year)throws Exception;
	//采购单明细下产品目录的查询
	public List<YyCgdMxCustom> findYyCgdMxList(CgdQueryVo cgdQueryVo)throws Exception;
	public int findYyCgdMxCount(CgdQueryVo cgdQueryVo)throws Exception;
	
	//医院采购单添加产品
	public List<YyCgdMxCustom> findAddYpcgdmxList(CgdQueryVo cgdQueryVo)throws Exception;
	public int findAddYpcgdmxCount(CgdQueryVo cgdQueryVo)throws Exception;
}
