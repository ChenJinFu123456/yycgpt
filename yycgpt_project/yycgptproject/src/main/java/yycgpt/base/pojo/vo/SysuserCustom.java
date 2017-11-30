package yycgpt.base.pojo.vo;

import yycgpt.base.pojo.po.Sysuser;

public class SysuserCustom extends Sysuser{
	//单位名称
	private String  sysmc;
	//确认密码
	private String repwd;
	
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
}
