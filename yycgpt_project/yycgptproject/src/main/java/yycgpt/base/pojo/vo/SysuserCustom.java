package yycgpt.base.pojo.vo;

import yycgpt.base.pojo.po.Sysuser;

public class SysuserCustom extends Sysuser{
	//单位名称
	private String  sysmc;
	//确认密码
	private String repwd;
	//新密码
	private String newpwd;
	//旧密码
	private String oldpwd;
	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	//用户性别
	private String sexname;
	
	//用户类型所对应的名称
	private String groupname;
	//用户状态名称
	private String statename;
	
	public String getSysmc() {
		return sysmc;
	}

	public void setSysmc(String sysmc) {
		this.sysmc = sysmc;
	}

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	public String getGroupname() {
		return groupname;
	}
	
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}
}
