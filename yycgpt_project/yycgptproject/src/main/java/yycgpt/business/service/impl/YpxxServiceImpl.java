package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.dao.mapper.YpxxMapperCustom;
import yycgpt.business.pojo.vo.YpxxCustom;
import yycgpt.business.pojo.vo.YpxxQueryVo;
import yycgpt.business.service.YpxxService;

public class YpxxServiceImpl implements YpxxService {
	
	@Autowired
	YpxxMapperCustom ypxxMapperCustom;
	@Autowired
	private YpxxMapper ypxxMapper;
	@Override
	public List<YpxxCustom> findYpxxList(YpxxQueryVo ypxxQueryVo)
			throws Exception {
		return ypxxMapperCustom.findYpxxList(ypxxQueryVo);
	}

	@Override
	public int findYpxxListCount(YpxxQueryVo ypxxQueryVo) throws Exception {
		return ypxxMapperCustom.findYpxxListCount(ypxxQueryVo);
	}
	
}