package yycgpt.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.process.context.Config;
import yycgpt.base.process.result.ResultUtil;
import yycgpt.business.dao.mapper.GysypmlControlMapper;
import yycgpt.business.dao.mapper.GysypmlControlMapperCustom;
import yycgpt.business.pojo.po.GysypmlControl;
import yycgpt.business.pojo.vo.GysypmlControlCustom;
import yycgpt.business.pojo.vo.GysypmlControlQueryVo;
import yycgpt.business.service.GysypmlControlService;

public class GysypmlControlServiceImpl implements GysypmlControlService {
	@Autowired
	private GysypmlControlMapperCustom controlMapperCustom;
	@Autowired
	private GysypmlControlMapper gysypmlControlMapper;

	@Override
	public List<GysypmlControlCustom> findGysYpmlControlList(
			GysypmlControlQueryVo gysypmlControlQueryVo) throws Exception {
		return controlMapperCustom
				.findGysYpmlControlList(gysypmlControlQueryVo);
	}

	@Override
	public int findGysYpmlControlCount(
			GysypmlControlQueryVo gysypmlControlQueryVo) throws Exception {
		return controlMapperCustom
				.findGysYpmlControlCount(gysypmlControlQueryVo);
	}

	@Override
	public void updateGysYpmlControl(String control,String advice, String id)
			throws Exception {
		// 根据主键查询
		GysypmlControl gysypmlControl = gysypmlControlMapper
				.selectByPrimaryKey(id);
		if (gysypmlControl == null) {
			// 不存在
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 402,
					null));
		}
		if(advice!=null){
			gysypmlControl.setAdvice(advice);
		}
		
		if(control!=null&&!control.equals("")&&(control.equals("1")||control.equals("2"))){
			gysypmlControl.setControl(control);
		}
		gysypmlControlMapper.updateByPrimaryKeySelective(gysypmlControl);
	}

}
