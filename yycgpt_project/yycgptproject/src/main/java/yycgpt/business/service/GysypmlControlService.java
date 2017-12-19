package yycgpt.business.service;

import java.util.List;

import yycgpt.business.pojo.vo.GysypmlControlCustom;
import yycgpt.business.pojo.vo.GysypmlControlQueryVo;

public interface GysypmlControlService {
	// 供应商产品目录控制列表
	public List<GysypmlControlCustom> findGysYpmlControlList(
			GysypmlControlQueryVo gysypmlControlQueryVo) throws Exception;

	// 总数
	public int findGysYpmlControlCount(
			GysypmlControlQueryVo gysypmlControlQueryVo) throws Exception;
	/**
	 * 
	* @Title: updateGysYpmlControl  
	* @Description: TODO 
	* @param gysypmlControlQueryVo 页面传过来的数据
	* @param id 结果集的id
	* @param userGysId 供应商的id 两个id将会作为联合主键对数据进行操作
	* @throws Exception
	* @return void    
	* @user zhuqiujie
	* @date  2017年12月16日下午10:10:16
	 */
	public void updateGysYpmlControl(String control,String advice,String id)throws Exception;
}
