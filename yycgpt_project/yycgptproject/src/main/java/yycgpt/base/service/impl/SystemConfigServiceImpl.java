package yycgpt.base.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycgpt.base.dao.mapper.BasicinfoMapper;
import yycgpt.base.dao.mapper.DictinfoMapper;
import yycgpt.base.pojo.po.Basicinfo;
import yycgpt.base.pojo.po.Dictinfo;
import yycgpt.base.pojo.po.DictinfoExample;
import yycgpt.base.service.SystemConfigService;


public class SystemConfigServiceImpl  implements SystemConfigService{

	@Autowired
	DictinfoMapper dictinfoMapper;
	@Autowired
	BasicinfoMapper basicinfoMapper;
	//根据数据字典typecode获取字典明细信息
	@Override
	public List findDictinfoByType(String typecode) throws Exception {

		DictinfoExample dictinfoExample = new DictinfoExample();
		DictinfoExample.Criteria criteria = dictinfoExample.createCriteria();
		
		criteria.andTypecodeEqualTo(typecode);
		
		return dictinfoMapper.selectByExample(dictinfoExample);
		
	}
	//根据typeocde和dictcode获取单个字典明细
	public Dictinfo  findDictinfoByDictcode(String typecode,String dictcode) throws Exception{
		DictinfoExample dictinfoExample = new DictinfoExample();
		DictinfoExample.Criteria criteria = dictinfoExample.createCriteria();
		criteria.andDictcodeEqualTo(dictcode);
		criteria.andTypecodeEqualTo(typecode);
		List<Dictinfo> list = dictinfoMapper.selectByExample(dictinfoExample);
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
		
	}
	/**
	 * 根据id获取系统配置信息
	 */
	@Override
	public Basicinfo findBasicinfoById(String id) throws Exception {
		return basicinfoMapper.selectByPrimaryKey(id);
	}

}
