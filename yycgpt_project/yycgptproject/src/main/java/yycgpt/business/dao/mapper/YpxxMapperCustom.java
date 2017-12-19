package yycgpt.business.dao.mapper;

import java.util.List;

import yycgpt.business.pojo.vo.YpxxCustom;
import yycgpt.business.pojo.vo.YpxxQueryVo;

public interface YpxxMapperCustom {
    //药品目录 查询
	public List<YpxxCustom> findYpxxList(YpxxQueryVo ypxxQueryVo) throws Exception;
	//产品目录总条数
	public int findYpxxListCount(YpxxQueryVo ypxxQueryVo) throws Exception;
}
