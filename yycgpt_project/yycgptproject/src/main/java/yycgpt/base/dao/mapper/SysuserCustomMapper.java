package yycgpt.base.dao.mapper;

import yycgpt.base.pojo.po.Sysuser;

public interface SysuserCustomMapper {
	
	public Sysuser findSysuserById(String id) throws Exception;

}
