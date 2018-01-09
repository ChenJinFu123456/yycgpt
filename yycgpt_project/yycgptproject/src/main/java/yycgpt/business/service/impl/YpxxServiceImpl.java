package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.business.dao.mapper.YpxxMapper;
import yycgpt.business.dao.mapper.YpxxMapperCustom;
import yycgpt.business.pojo.po.Ypxx;
import yycgpt.business.pojo.vo.YpxxCustom;
import yycgpt.business.pojo.vo.YpxxQueryVo;
import yycgpt.business.service.YpxxService;
import yycgpt.utils.CheckRegex;

public class YpxxServiceImpl implements YpxxService {
	
	@Autowired
	YpxxMapperCustom ypxxMapperCustom;
	@Autowired
	private YpxxMapper ypxxMapper;
	@Override
	public List<YpxxCustom> findYpxxList(YpxxQueryVo ypxxQueryVo)
			throws Exception {
		return ypxxMapperCustom.findYpxxList(ypxxQueryVo);
	}

	@Override
	public int findYpxxListCount(YpxxQueryVo ypxxQueryVo) throws Exception {
		return ypxxMapperCustom.findYpxxListCount(ypxxQueryVo);
	}

	@Override
	public YpxxCustom findYpxxById(String ypxxid) throws Exception {
		//数据库获得的记录
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxid);
		if(ypxx==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 316,
					null));
		}
		
		YpxxCustom ypxxCustom  = new YpxxCustom();
		//填充页面信息 流水号   通用名   规格   中标价格  生产企业  交易状态  管理类别
		BeanUtils.copyProperties(ypxx, ypxxCustom);
		return ypxxCustom;
	}

	@Override
	public void updateYpxx(String id,YpxxCustom ypxxCustom) throws Exception {
		//企业名称
		String scqymc = ypxxCustom.getScqymc();
		if(scqymc==null||scqymc.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 718,
					null));
		}
		//产品名称
		String mc = ypxxCustom.getMc();
		if(scqymc==null||scqymc.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 719,
					null));
		}
		//交易状态
		String jyzt = ypxxCustom.getJyzt();
		if(jyzt==null||jyzt.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 720,
					null));
		}
		String gg = ypxxCustom.getGg();
		//规格
		if(gg==null||gg.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 721,
					null));
		}
		//价格
		Float zbjg = ypxxCustom.getZbjg();
		if(zbjg==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 722,
					null));
		}else if(!CheckRegex.isNumeric_xs(String.valueOf(zbjg))){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 723,
					null));
		}
		
		//管理类别
		String lb = ypxxCustom.getLb();
		if(lb==null||lb.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 724,
					null));
		}
		
		
		Ypxx ypxx_update = ypxxMapper.selectByPrimaryKey(id);
		ypxx_update.setScqymc(scqymc);
		ypxx_update.setJyzt(jyzt);
		ypxx_update.setLb(lb);
		ypxx_update.setGg(gg);
		ypxx_update.setZbjg(zbjg);
		ypxx_update.setMc(mc);
		
		ypxxMapper.updateByPrimaryKeySelective(ypxx_update);
	}

	@Override
	public void deleteYpxxById(String ypxxid) throws Exception {
		//先查询
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxid);
		if(ypxx==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 316,
					null));
		}
		
		ypxxMapper.deleteByPrimaryKey(ypxxid);
	}
	
}