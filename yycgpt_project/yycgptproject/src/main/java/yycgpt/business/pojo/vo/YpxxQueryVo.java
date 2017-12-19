package yycgpt.business.pojo.vo;

import java.util.List;

import yycgpt.base.pojo.vo.PageQuery;


public class YpxxQueryVo {

	private YpxxCustom ypxxCustom;
	private PageQuery pageQuery;
	private List<YpxxCustom> ypxxs;
	public void setYpxxCustom(YpxxCustom ypxxCustom) {
		this.ypxxCustom = ypxxCustom;
	}
	public YpxxCustom getYpxxCustom() {
		return ypxxCustom;
	}
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	
	public void setYpxxs(List<YpxxCustom> ypxxs) {
		this.ypxxs = ypxxs;
	}
	public List<YpxxCustom> getYpxxs() {
		return ypxxs;
	}
}
