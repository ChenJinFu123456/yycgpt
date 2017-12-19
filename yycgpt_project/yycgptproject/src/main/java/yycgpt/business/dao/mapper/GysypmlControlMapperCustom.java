package yycgpt.business.dao.mapper;

import java.util.List;

import yycgpt.business.pojo.vo.GysypmlControlCustom;
import yycgpt.business.pojo.vo.GysypmlControlQueryVo;

public interface GysypmlControlMapperCustom {
	
	//供应商产品目录控制列表
	public List<GysypmlControlCustom> findGysYpmlControlList(GysypmlControlQueryVo gysypmlControlQueryVo)throws Exception;
	//总数
	public int findGysYpmlControlCount(GysypmlControlQueryVo gysypmlControlQueryVo)throws Exception;
}
