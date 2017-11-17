package yycgpt.base.sevice;

import yycgpt.base.pojo.po.Sysuser;

public interface UserService {
	
	//根据id查询用户
	
	public Sysuser findSysuserById(String id) throws Exception;

}
