package yycgpt.business.pojo.vo;

import java.util.List;

import yycgpt.base.pojo.po.Userjd;
import yycgpt.base.pojo.vo.PageQuery;

public class GysypmlControlQueryVo {
	private GysypmlControlCustom gysypmlControlCustom;

	private PageQuery pageQuery;
	//产品信息
	private YpxxCustom ypxxCustom;
	// 接受页面的批量提交
	private List<YpxxCustom> ypxxCustoms;
	//接受批量提交的供应商信息
	private List<GysypmlControlCustom> gysypmls;
	
	//监督单位
	private Userjd userjd;
	
	public GysypmlControlCustom getGysypmlControlCustom() {
		return gysypmlControlCustom;
	}

	public void setGysypmlControlCustom(
			GysypmlControlCustom gysypmlControlCustom) {
		this.gysypmlControlCustom = gysypmlControlCustom;
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
	public void setGysypmls(List<GysypmlControlCustom> gysypmls) {
		this.gysypmls = gysypmls;
	}
	public List<GysypmlControlCustom> getGysypmls() {
		return gysypmls;
	}

	public Userjd getUserjd() {
		return userjd;
	}

	public void setUserjd(Userjd userjd) {
		this.userjd = userjd;
	}
	
	
}
