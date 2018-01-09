package yycgpt.base.service.impl;

import java.util.List;

import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.springframework.beans.BeanUtils;
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
import yycgpt.base.pojo.vo.ActiveUser;
import yycgpt.base.pojo.vo.SysuserCustom;
import yycgpt.base.pojo.vo.SysuserQueryVo;
import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ExceptionResultInfo;
import yycgpt.base.process.result.ResultInfo;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.base.service.UserService;
import yycgpt.utils.MD5;
import yycgpt.utils.ResourcesUtil;
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

	// 校验用户的信息 userId：帐号
	public ActiveUser checkUserInfo(String userId, String pwd) throws Exception {
		// 用户是否存在
		Sysuser sysuser = this.findSysuserByUserId(userId);
		if (sysuser == null) {
			// 用户不存在
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 101,
					null));
		}
		// 用户的密码是否合法
		String pwd_db = sysuser.getPwd();
		String pwd_md5 = new MD5().getMD5ofStr(pwd);

		if (!pwd_db.equalsIgnoreCase(pwd_md5)) {

			// 密码错误
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 114,
					null));
		}

		// 构建用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(userId);
		activeUser.setGroupid(sysuser.getGroupid());// 单位的类型
		activeUser.setSysid(sysuser.getSysid());// 单位的id
		activeUser.setUsername(sysuser.getUsername());

		String groupId = sysuser.getGroupid();
		// 单位名称
		String sysmc = null;
		// 单位id/sysid
		String sysid = sysuser.getSysid();

		// 单位名称非空
		if (!userId.equals("admin") && (sysid == null || sysid.equals(""))) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 222,
					null));
		}

		if (groupId == null || groupId.equals("")) {
			// 用户类型非空
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 224,
					null));
		} else if (groupId.equals("1") || groupId.equals("2")) {
			// 监督单位
			// 根据单位id查询单位的名称
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			if (userjd == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			// 获取单位名称
			sysmc = userjd.getMc();
		} else if (groupId.equals("3")) {// 卫生室
			// 监督单位
			// 根据单位名称查询单位的信息
			Useryy useryy = useryyMapper.selectByPrimaryKey(sysid);
			if (useryy == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysmc = useryy.getMc();
		} else if (groupId.equals("4")) {// 供应商
			// 监督单位
			// 根据单位名称查询单位的信息
			Usergys usergys = usergysMapper.selectByPrimaryKey(sysid);
			if (usergys == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysmc = usergys.getMc();
		}

		activeUser.setSysmc(sysmc);

		return activeUser;
	}

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
		if (userid == null || userid.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 110,
					null));
		}

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
		if (sysuserCustom.getUsername() == null
				|| sysuserCustom.getUsername().equals("")) {
			// 抛出自定义异常
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 218,
					null));

		}
		// 用户密码
		String pwd = sysuserCustom.getPwd();
		String repwd = sysuserCustom.getRepwd();
		if (pwd == null || pwd.equals("")) {
			// 抛出自定义异常
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 219,
					null));

		} else if (!pwd.equals(repwd)) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 226,
					null));
		} else {// 对密码进行加密
			sysuserCustom.setPwd(new MD5().getMD5ofStr(pwd));
		}

		if (sysuser != null) {
			// 帐号重复，抛出异常，可预知的异常
			// throw new Exception("帐号重复");
			// 抛出自定义异常
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 213,
					null));

		} else if (sysuserCustom.getUserid() == null
				|| sysuserCustom.getUserid().equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 220,
					null));

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
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 221,
					null));

		}
		if (sysmc == null || sysmc.equals("")) {
			// throw new Exception("请输入单位名称");
			// 抛出自定义异常
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 222,
					null));

		}

		// 卫生局和卫生院都是监督单位
		// 单位id/sysid
		String sysId = null;
		if (groupId == null || groupId.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 224,
					null));
		} else if (groupId.equals("1") || groupId.equals("2")) {
			// 监督单位：卫生局，卫生院
			// 根据单位名称查询单位的信息
			Userjd userjd = this.findUserJdByMc(sysmc);
			if (userjd == null) {
				// throw new Exception("单位名称输入错误");
				// 抛出自定义异常
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			// 获取单位id
			sysId = userjd.getId();
		} else if (groupId.equals("3")) {
			// 卫生室
			// 根据单位名称查询单位的信息
			Useryy useryy = this.findUserYyByMc(sysmc);
			if (useryy == null) {
				// throw new Exception("单位名称输入错误");
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysId = useryy.getId();
		} else if (groupId.equals("4")) {// 供应商
			// 监督单位
			// 根据单位名称查询单位的信息
			Usergys usergys = this.findUserGysByMc(sysmc);
			if (usergys == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysId = usergys.getId();
		}

		// 性别
		String sex = sysuserCustom.getSex();
		if (sex == null || sex.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 115,
					null));
		}

		// 添加
		// 设置主键
		sysuserCustom.setId(UUIDBuild.getUUID());
		sysuserCustom.setSysid(sysId);
		sysuserMapper.insert(sysuserCustom);
	}

	public void deleteSysuser(String userId) throws Exception {
		// 校验用户是否存在

		// 先查询
		Sysuser sysuser = sysuserMapper.selectByPrimaryKey(userId);
		if (sysuser == null || sysuser.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 212,
					null));
		}
		// 执行删除
		sysuserMapper.deleteByPrimaryKey(userId);
	}

	public void updateSysuser(String id, SysuserCustom sysuserCustom)
			throws Exception {
		/**
		 * todo 字段的非空判断
		 */

		// 状态非空
		String userState = sysuserCustom.getUserstate();
		if (userState == null || userState.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 221,
					null));
		}
		// 用户名称非空
		if (sysuserCustom.getUsername() == null
				|| sysuserCustom.getUsername().equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 218,
					null));
		}

		/**
		 * 1.判断帐号是否被修改 2.如果帐号已经被修改，是否与系统已经存在的帐号重复
		 */
		// 页面提交的帐号
		String userId_page = sysuserCustom.getUserid();
		// 帐号非空
		if (userId_page == null || userId_page.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 220,
					null));
		}

		// 通过数据可查到的数据库的帐号
		Sysuser sysuser = sysuserMapper.selectByPrimaryKey(id);
		if (sysuser == null) {
			// 数据库中无此用户
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 225,
					null));
		}
		String userid = sysuser.getUserid();
		if (!userid.equals(userId_page)) {
			// 用户已经修改了帐号
			Sysuser sysuser1 = this.findSysuserByUserId(userId_page);
			if (sysuser1 != null) {
				// 占用别人的帐号
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						213, null));
			}
		}

		/**
		 * 单位名称
		 */

		// 获取用户类型
		String groupId = sysuserCustom.getGroupid();
		// 单位名称
		String sysmc = sysuserCustom.getSysmc();
		// 单位id/sysid
		String sysId = null;

		// 单位名称非空
		if (sysmc == null || sysmc.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 222,
					null));
		}

		if (groupId == null || groupId.equals("")) {
			// 用户类型非空
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 224,
					null));
		} else if (groupId.equals("1") || groupId.equals("2")) {
			// 监督单位
			// 根据单位名称查询单位的信息
			Userjd userjd = this.findUserJdByMc(sysmc);
			if (userjd == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			// 获取单位id
			sysId = userjd.getId();
		} else if (groupId.equals("3")) {// 卫生室
			// 监督单位
			// 根据单位名称查询单位的信息
			Useryy useryy = this.findUserYyByMc(sysmc);
			if (useryy == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysId = useryy.getId();
		} else if (groupId.equals("4")) {// 供应商
			// 监督单位
			// 根据单位名称查询单位的信息
			Usergys usergys = this.findUserGysByMc(sysmc);
			if (usergys == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysId = usergys.getId();
		}
		/**
		 * 密码修改 如果从页面提交的密码为空，就说明用户不修改密码
		 */

		// 用户密码
		String pwd = sysuserCustom.getPwd();
		String repwd = sysuserCustom.getRepwd();
		if (pwd == null || pwd.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 219,
					null));

		} else if (!pwd.equals(repwd)) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 226,
					null));
		} else {// 对密码进行加密
			sysuserCustom.setPwd(new MD5().getMD5ofStr(pwd));
		}

		// 性别
		String sex = sysuserCustom.getSex();
		if (sex == null || sex.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 115,
					null));
		}

		/*
		 * 先查后更新 设置更新的用户信息
		 */
		// 不管有没有值，全部更新（包括空字段）
		Sysuser sysuser_update = new Sysuser();
		sysuser_update = sysuserMapper.selectByPrimaryKey(id);

		// 设置需要修改的字段
		sysuser_update.setUserid(sysuserCustom.getUserid());// 帐号
		sysuser_update.setGroupid(sysuserCustom.getGroupid());// 类型
		sysuser_update.setUserstate(sysuserCustom.getUserstate());// 状态
		sysuser_update.setPwd(sysuserCustom.getPwd());// 密码
		sysuser_update.setUsername(sysuserCustom.getUsername());// 用户名
		
		sysuser_update.setPhone(sysuserCustom.getPhone());//电话
		sysuser_update.setSex(sex);//性别
		if (sysId != null) {// 用户类型
			sysuser_update.setSysid(sysId);
		} else {
			sysuser_update.setSysid(sysuserCustom.getSysid());// 单位id
		}
		sysuserMapper.updateByPrimaryKey(sysuser_update);

	}

	public SysuserCustom findSysuserById(String id) throws Exception {
		// 用户信息
		Sysuser sysuser = sysuserMapper.selectByPrimaryKey(id);

		// 由sysid查询单位名称
		// 获取用户类型
		String groupId = sysuser.getGroupid();
		// 单位名称
		String sysmc = null;
		// 单位id/sysid
		String sysid = sysuser.getSysid();

		// 单位名称非空
		if (sysid == null || sysid.equals("")) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 222,
					null));
		}

		if (groupId == null || groupId.equals("")) {
			// 用户类型非空
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 224,
					null));
		} else if (groupId.equals("1") || groupId.equals("2")) {
			// 监督单位
			// 根据单位id查询单位的名称
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			if (userjd == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			// 获取单位名称
			sysmc = userjd.getMc();
		} else if (groupId.equals("3")) {// 卫生室
			// 监督单位
			// 根据单位名称查询单位的信息
			Useryy useryy = useryyMapper.selectByPrimaryKey(sysid);
			if (useryy == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysmc = useryy.getMc();
		} else if (groupId.equals("4")) {// 供应商
			// 监督单位
			// 根据单位名称查询单位的信息
			Usergys usergys = usergysMapper.selectByPrimaryKey(sysid);
			if (usergys == null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,
						223, null));
			}
			sysmc = usergys.getMc();
		}

		SysuserCustom sysuserCustom = new SysuserCustom();
		BeanUtils.copyProperties(sysuser, sysuserCustom);
		sysuserCustom.setSysmc(sysmc);

		return sysuserCustom;
	}

}
