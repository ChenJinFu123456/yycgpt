package yycgpt.base.service.impl;

import java.util.List;

import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.SysuserMapper;
import yycgpt.base.dao.mapper.SysuserMapperCustom;
import yycgpt.base.dao.mapper.UsergysMapper;
import yycgpt.base.dao.mapper.UserjdMapper;
import yycgpt.base.dao.mapper.UseryyMapper;
import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.pojo.po.SysuserExample;
import yycgpt.base.pojo.po.Usergys;
import yycgpt.base.pojo.po.UsergysExample;
import yycgpt.base.pojo.po.Userjd;
import yycgpt.base.pojo.po.UserjdExample;
import yycgpt.base.pojo.po.Useryy;
import yycgpt.base.pojo.po.UseryyExample;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;
import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;
import yycgpt.base.service.UserService;
import yycgpt.utils.MD5;
import yycgpt.utils.UUIDBuild;

public class UserServiceImpl implements UserService {

	// 注入mapper
	@Autowired
	private SysuserMapper sysuserMapper;

	@Autowired
	private SysuserMapperCustom sysuserMapperCustom;

	// 监督单位
	@Autowired
	private UserjdMapper userjdMapper;

	// 医院、卫生室
	@Autowired
	private UseryyMapper useryyMapper;

	// 供应商
	@Autowired
	private UsergysMapper usergysMapper;

	public List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo)
			throws Exception {
		return sysuserMapperCustom.findSysuserList(sysuserQueryVo);
	}

	@Override
	public int findSysuserCount(SysuserQueryVo sysuserQueryVo) throws Exception {
		return sysuserMapperCustom.findSysuserCount(sysuserQueryVo);
	}

	// 根据用户帐号查询用户
	public Sysuser findSysuserByUserId(String userid) throws Exception {
		SysuserExample sysuserExample = new SysuserExample();
		SysuserExample.Criteria criteria = sysuserExample.createCriteria();
		// 根据用户的帐号查询,添加查询条件
		criteria.andUseridEqualTo(userid);
		List<Sysuser> list = sysuserMapper.selectByExample(sysuserExample);

		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	// 根据单位名称查询单位的信息
	public Userjd findUserJdByMc(String mc) throws Exception {
		UserjdExample userjdExample = new UserjdExample();
		UserjdExample.Criteria criteria = userjdExample.createCriteria();
		criteria.andMcEqualTo(mc);
		List<Userjd> list = userjdMapper.selectByExample(userjdExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	public Useryy findUserYyByMc(String mc) throws Exception {
		UseryyExample useryyExample = new UseryyExample();
		UseryyExample.Criteria criteria = useryyExample.createCriteria();
		criteria.andMcEqualTo(mc);
		List<Useryy> list = useryyMapper.selectByExample(useryyExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	public Usergys findUserGysByMc(String mc) throws Exception {
		UsergysExample usergysExample = new UsergysExample();
		UsergysExample.Criteria criteria = usergysExample.createCriteria();
		criteria.andMcEqualTo(mc);
		List<Usergys> list = usergysMapper.selectByExample(usergysExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertSysuser(SysuserCustom sysuserCustom) throws Exception {
		// 数据的校验
		// 通用的合法性的校验，非空校验，长度校验
		// 业务合法性校验，帐号的唯一性
		// 判断帐号的唯一性
		Sysuser sysuser = this.findSysuserByUserId(sysuserCustom.getUserid());
		// 用户名称
		if (sysuserCustom.getUsername().equals("")
				|| sysuserCustom.getUsername() == null) {
			// 抛出自定义异常
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("请输入用户名称");
			throw new ExceptionResultInfo(resultInfo);
		}
		// 用户密码
		String pwd = sysuserCustom.getPwd();
		if (pwd.equals("") || pwd == null) {
			// 抛出自定义异常
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("用户密码不能为空");
			throw new ExceptionResultInfo(resultInfo);
		}else{//对密码进行加密
			sysuserCustom.setPwd(new MD5().getMD5ofStr(pwd));
		}

		if (sysuser != null) {
			// 帐号重复，抛出异常，可预知的异常
			// throw new Exception("帐号重复");
			// 抛出自定义异常
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("帐号重复");
			throw new ExceptionResultInfo(resultInfo);
		} else if (sysuserCustom.getUserid().equals("")
				|| sysuserCustom.getUserid() == null) {
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("请输入帐号");
			throw new ExceptionResultInfo(resultInfo);
		}
		// 根据用户类型，输入的单位名称必须是存在的单位
		// 用户状态
		String userState = sysuserCustom.getUserstate();
		// 获取用户类型
		String groupId = sysuserCustom.getGroupid();
		// 单位名称
		String sysmc = sysuserCustom.getSysmc();
		// 用户状态
		if (userState == null || userState.equals("")) {
			// throw new Exception("请选择用户状态");
			// 抛出自定义异常
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("请选择用户状态");
			throw new ExceptionResultInfo(resultInfo);
		}
		if (sysmc.equals("") || sysmc == null) {
			// throw new Exception("请输入单位名称");
			// 抛出自定义异常
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("请输入单位名称");
			throw new ExceptionResultInfo(resultInfo);
		}
		// 单位id/sysid
		String sysId = null;
		if (groupId.equals("1") || groupId.equals("2")) {
			// 监督单位
			// 根据单位名称查询单位的信息
			Userjd userjd = this.findUserJdByMc(sysmc);
			if (userjd == null) {
				// throw new Exception("单位名称输入错误");
				// 抛出自定义异常
				ResultInfo resultInfo = new ResultInfo();
				resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("单位名称输入错误");
				throw new ExceptionResultInfo(resultInfo);
			}
			// 获取单位id
			sysId = userjd.getId();
		} else if (groupId.equals("3")) {// 卫生室
			// 监督单位
			// 根据单位名称查询单位的信息
			Useryy useryy = this.findUserYyByMc(sysmc);
			if (useryy == null) {
				// throw new Exception("单位名称输入错误");
				// 抛出自定义异常
				ResultInfo resultInfo = new ResultInfo();
				resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("单位名称输入错误");
				throw new ExceptionResultInfo(resultInfo);
			}
			sysId = useryy.getId();
		} else if (groupId.equals("4")) {// 供应商
			// 监督单位
			// 根据单位名称查询单位的信息
			Usergys usergys = this.findUserGysByMc(sysmc);
			if (usergys == null) {
				// throw new Exception("单位名称输入错误");
				// 抛出自定义异常
				ResultInfo resultInfo = new ResultInfo();
				resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("单位名称输入错误");
				throw new ExceptionResultInfo(resultInfo);
			}
		} else if (groupId.equals("") || groupId == null) {
			// throw new Exception("请选择单位");
			// 抛出自定义异常
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setIndex(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("请选择单位");
			throw new ExceptionResultInfo(resultInfo);
		}

		// 添加
		// 设置主键
		sysuserCustom.setId(UUIDBuild.getUUID());
		sysuserCustom.setSysid(sysId);
		sysuserMapper.insert(sysuserCustom);
	}
}
