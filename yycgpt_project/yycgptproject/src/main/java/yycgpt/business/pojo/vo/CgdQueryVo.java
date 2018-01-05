package yycgpt.business.pojo.vo;

import java.util.List;

import yycgpt.base.pojo.po.Usergys;
import yycgpt.base.pojo.po.Useryy;
import yycgpt.base.pojo.vo.PageQuery;

public class CgdQueryVo {
	private String businessyear;
	// 分页
	private PageQuery pageQuery;
	// 采购单的基本信息
	private YycgdCustom yycgdCustom;
	// 采购单的明细
	private YyCgdMxCustom yyCgdMxCustom;
	// 产品信息
	private YpxxCustom ypxxCustom;
	// 供应商产品目录
	private GysypmlCustom gysypmlCustom;
	// 医院的区域id扩充到useryy中
	private Useryy useryy;
	//供应商
	private Usergys usergys;
	public Usergys getUsergys() {
		return usergys;
	}

	public void setUsergys(Usergys usergys) {
		this.usergys = usergys;
	}

	// 接受页面的批量参数
	private List<YyCgdMxCustom> yyCgdMxCustoms;
	// 批量提交采购单审核
	private List<YycgdCustom> yycgdCustoms;
	//批量入库信息
	private List<YycgdrkCustom> yycgdrkCustoms;
	public YycgdCustom getYycgdCustom() {
		return yycgdCustom;
	}

	public void setYycgdCustom(YycgdCustom yycgdCustom) {
		this.yycgdCustom = yycgdCustom;
	}

	public String getBusinessyear() {
		return businessyear;
	}

	public void setBusinessyear(String businessyear) {
		this.businessyear = businessyear;
	}

	public YyCgdMxCustom getYyCgdMxCustom() {
		return yyCgdMxCustom;
	}

	public void setYyCgdMxCustom(YyCgdMxCustom yyCgdMxCustom) {
		this.yyCgdMxCustom = yyCgdMxCustom;
	}

	public YpxxCustom getYpxxCustom() {
		return ypxxCustom;
	}

	public void setYpxxCustom(YpxxCustom ypxxCustom) {
		this.ypxxCustom = ypxxCustom;
	}

	public PageQuery getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}

	public Useryy getUseryy() {
		return useryy;
	}

	public void setUseryy(Useryy useryy) {
		this.useryy = useryy;
	}

	public GysypmlCustom getGysypmlCustom() {
		return gysypmlCustom;
	}

	public void setGysypmlCustom(GysypmlCustom gysypmlCustom) {
		this.gysypmlCustom = gysypmlCustom;
	}

	public List<YyCgdMxCustom> getYyCgdMxCustoms() {
		return yyCgdMxCustoms;
	}

	public void setYyCgdMxCustoms(List<YyCgdMxCustom> yyCgdMxCustoms) {
		this.yyCgdMxCustoms = yyCgdMxCustoms;
	}

	public List<YycgdCustom> getYycgdCustoms() {
		return yycgdCustoms;
	}

	public void setYycgdCustoms(List<YycgdCustom> yycgdCustoms) {
		this.yycgdCustoms = yycgdCustoms;
	}

	public List<YycgdrkCustom> getYycgdrkCustoms() {
		return yycgdrkCustoms;
	}

	public void setYycgdrkCustoms(List<YycgdrkCustom> yycgdrkCustoms) {
		this.yycgdrkCustoms = yycgdrkCustoms;
	}

}
