package yycgpt.base.service;

import java.util.List;

import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;

public interface UserService {
	
	//根据条件查询用户列表
	public  List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo)throws Exception;
	//根据条件查询列表的总数
	public  int findSysuserCount(SysuserQueryVo sysuserQueryVo)throws Exception;
}
