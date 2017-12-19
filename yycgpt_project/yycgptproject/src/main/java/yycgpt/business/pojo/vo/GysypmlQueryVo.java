package yycgpt.business.pojo.vo;

import java.util.List;

import yycgpt.base.pojo.vo.PageQuery;
public class GysypmlQueryVo {
	private GysypmlCustom gysypmlCustom;
	private PageQuery pageQuery;
	private YpxxCustom ypxxCustom;
	//接受页面的批量提交
	private List<YpxxCustom> ypxxCustoms;
	public GysypmlCustom getGysypmlCustom() {
		return gysypmlCustom;
	}
	public void setGysypmlCustom(GysypmlCustom gysypmlCustom) {
		this.gysypmlCustom = gysypmlCustom;
	}
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	public YpxxCustom getYpxxCustom() {
		return ypxxCustom;
	}
	public void setYpxxCustom(YpxxCustom ypxxCustom) {
		this.ypxxCustom = ypxxCustom;
	}
	public List<YpxxCustom> getYpxxCustoms() {
		return ypxxCustoms;
	}
	public void setYpxxCustoms(List<YpxxCustom> ypxxCustoms) {
		this.ypxxCustoms = ypxxCustoms;
	}
	
}
