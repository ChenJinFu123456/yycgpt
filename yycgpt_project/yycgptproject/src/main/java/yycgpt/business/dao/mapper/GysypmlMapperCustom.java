package yycgpt.business.dao.mapper;

import java.util.List;

import yycgpt.business.pojo.vo.GysypmlCustom;
import yycgpt.business.pojo.vo.GysypmlQueryVo;

public interface GysypmlMapperCustom {

	//供货商产品目录的查询列表
	public List<GysypmlCustom> findGysypmlList(GysypmlQueryVo gysypmlQueryVo)throws Exception;
	//供货商产品目录总数
	public int findGysypmlCount(GysypmlQueryVo gysypmlQueryVo)throws Exception;
	
	//供货商产品目录添加
	public List<GysypmlCustom> findAddGysypmlList(GysypmlQueryVo gysypmlQueryVo)throws Exception;
	public int findAddGysypmlCount(GysypmlQueryVo gysypmlQueryVo)throws Exception;
}
