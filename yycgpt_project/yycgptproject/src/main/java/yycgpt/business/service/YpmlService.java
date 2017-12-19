package yycgpt.business.service;

import java.util.List;

import yycgpt.business.pojo.po.Gysypml;
import yycgpt.business.pojo.po.GysypmlControl;
import yycgpt.business.pojo.vo.GysypmlCustom;
import yycgpt.business.pojo.vo.GysypmlQueryVo;

public interface YpmlService {
	/**
	 * @Title: findGysypmlList
	 * @Description: TODO
	 * @param usergysid
	 *            供货商id，因为每一个供货商只能看到自己的产品
	 * @param gysypmlQueryVo
	 *            查询条件
	 * @return
	 * @throws Exception
	 * @return List<GysypmlCustom>
	 * @user zhuqiujie
	 * @date 2017年12月14日下午8:16:27
	 */
	// 供货商产品目录的查询列表
	public List<GysypmlCustom> findGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;

	// 供货商产品目录总数
	public int findGysypmlCount(String usergysId, GysypmlQueryVo gysypmlQueryVo)
			throws Exception;

	public List<GysypmlCustom> findAddGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;

	public int findAddGysypmlCount(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception;

	// 供应商产品目录的添加
	public void insertGysYpml(String userGysId, String ypxxId) throws Exception;

	// 抽取：根据供应商id和产品id查询供应商产品目录
	public Gysypml findGysypmlByGysIdAndYpxxId(String userGysId, String ypxxId)throws Exception;
	public GysypmlControl findGysypmlControlByGysIdAndYpxxId(String userGysId, String ypxxId)throws Exception;
	/**
	* @Title: deleteGysYpml  
	* @Description: 只是删除gysypml，不删除控制表的目录 
	* @param userGysId
	* @param ypxxId
	* @throws Exception
	* @return void    
	* @user zhuqiujie
	* @date  2017年12月16日下午4:23:31
	 */
	public void deleteGysYpml(String userGysId, String ypxxId) throws Exception;
}
