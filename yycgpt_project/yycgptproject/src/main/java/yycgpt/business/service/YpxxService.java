package yycgpt.business.service;

import java.util.List;

import yycgpt.business.pojo.vo.YpxxCustom;
import yycgpt.business.pojo.vo.YpxxQueryVo;

public interface YpxxService {

	// 药品目录查询
	public List<YpxxCustom> findYpxxList(YpxxQueryVo ypxxQueryVo)
			throws Exception;

	// 产品目录总条数
	public int findYpxxListCount(YpxxQueryVo ypxxQueryVo) throws Exception;
	
	//根据产品信息的id获取产品的信息
	public  YpxxCustom findYpxxById(String ypxxid) throws Exception;
	//修改产品信息提交
	public void updateYpxx(String id, YpxxCustom ypxxCustom)throws Exception;
	//根据产品id删除
	public void deleteYpxxById(String ypxxid)throws Exception;
}
