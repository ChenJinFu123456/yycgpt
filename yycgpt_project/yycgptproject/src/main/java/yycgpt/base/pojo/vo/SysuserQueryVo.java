package yycgpt.base.pojo.vo;
/**
 * 包装类
 * 页面向action传递参数
 * @author Administrator
 *
 */
public class SysuserQueryVo {
	
	//分页
	private PageQuery pageQuery;
	
	private SysuserCustom sysuserCustom;

	public SysuserCustom getSysuserCustom() {
		return sysuserCustom;
	}

	public void setSysuserCustom(SysuserCustom sysuserCustom) {
		this.sysuserCustom = sysuserCustom;
	}

	public PageQuery getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	
}
