package yycgpt.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.SysuserMapper;
import yycgpt.base.dao.mapper.SysuserMapperCustom;
import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;
import yycgpt.base.sevice.UserService;

public class UserServiceImpl implements UserService {

	//注入mapper
	@Autowired
	private SysuserMapper sysuserMapper;
	
	@Autowired
	private SysuserMapperCustom sysuserMapperCustom;
	

	public List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo)
			throws Exception {
		return sysuserMapperCustom.findSysuserList(sysuserQueryVo);
	}


	@Override
	public int findSysuserCount(SysuserQueryVo sysuserQueryVo) throws Exception {
		return sysuserMapperCustom.findSysuserCount(sysuserQueryVo);
	}
	


}
