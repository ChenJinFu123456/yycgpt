package yycgpt.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.SysuserMapper;
import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.sevice.UserService;

public class UserServiceImpl implements UserService {

	//注入mapper
	@Autowired
	private SysuserMapper sysuserMapper;
	
	@Override
	public Sysuser findSysuserById(String id) throws Exception {
		//调用mapper
		
		return sysuserMapper.selectByPrimaryKey(id);
	}

}
