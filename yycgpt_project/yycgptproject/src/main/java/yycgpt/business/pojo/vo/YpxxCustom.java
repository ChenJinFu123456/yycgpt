package yycgpt.business.pojo.vo;

import yycgpt.business.pojo.po.Ypxx;

public class YpxxCustom extends Ypxx{
	//价格范围
	private Float price_start;
	private Float price_end;
	//交易状态的名称
	private String jyztmc;

	public String getJyztmc() {
		return jyztmc;
	}

	public void setJyztmc(String jyztmc) {
		this.jyztmc = jyztmc;
	}

	public Float getPrice_start() {
		return price_start;
	}

	public void setPrice_start(Float price_start) {
		this.price_start = price_start;
	}

	public Float getPrice_end() {
		return price_end;
	}

	public void setPrice_end(Float price_end) {
		this.price_end = price_end;
	}
	
}
