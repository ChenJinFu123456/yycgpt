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
}
