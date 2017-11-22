package yycgpt.base.service;

import java.util.List;

import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.pojo.po.Usergys;
import yycgpt.base.pojo.po.Useryy;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;

public interface UserService {
	
	//根据条件查询用户列表
	public  List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo)throws Exception;
	//根据条件查询列表的总数
	public  int findSysuserCount(SysuserQueryVo sysuserQueryVo)throws Exception;
	//添加用户
	public void insertSysuser(SysuserCustom sysuserCustom)throws Exception;
	//根据用户帐号查询用户
	public Sysuser findSysuserByUserId(String userid)throws Exception;
	//卫生室、医院
	public Useryy findUserYyByMc(String mc)throws Exception;
	//供应商
	public Usergys findUserGysByMc(String mc)throws Exception;
}
