package yycgpt.base.dao.mapper;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import yycgpt.base.pojo.po.Sysuser;
import yycgpt.base.pojo.po.SysuserExample;
import yycgpt.base.pojo.po.SysuserExample.Criteria;
import yycgpt.utils.UUIDBuild;

public class SysuserMapperTest extends TestCase{
	
	private  SysuserMapper sysuserMapper;
	private ApplicationContext applicationContext;
	
	
	protected void setUp() throws Exception {
		//获取spring容器
		applicationContext = new ClassPathXmlApplicationContext(new String[]{
				"spring/applicationContext.xml",
				"spring/applicationContext-dao.xml"
		});
		sysuserMapper = (SysuserMapper)applicationContext.getBean("sysuserMapper");
	}

	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}
	//插入
	public void testInsert() {
		Sysuser sysuser = new Sysuser();
		sysuser.setId(UUIDBuild.getUUID());
		sysuser.setUsername("dsfsdfsd");
		sysuserMapper.insert(sysuser);
	}
	//自定义查询条件
	public void testSelectByExample() {
		
		SysuserExample sysuserExample = new SysuserExample();
		SysuserExample.Criteria criteria =  sysuserExample.createCriteria();
		//自定义查询条件
		criteria.andUsernameEqualTo("王村镇梁庄村卫生室");
		//criteria.andGroupidEqualTo("3");
		List<Sysuser> list = sysuserMapper.selectByExample(sysuserExample);
		System.out.println(list.size());
	}

	
	//根据主键查询
	public void testSelectByPrimaryKey() {
		Sysuser sysuser = (Sysuser)sysuserMapper.selectByPrimaryKey("138");
		System.out.println(sysuser);
	}

	public void testUpdateByPrimaryKeySelective() {
		/**
		 * 属性不为空才会更新
		 * 所以先设置id，再设置需要更新的对象属性，
		 * 效率高，只是更新需要更改的字段
		 */
		Sysuser sysuser = new Sysuser();
		sysuser.setId("41");
		sysuser.setUsername("test9999999");
		sysuserMapper.updateByPrimaryKeySelective(sysuser);
	}

	public void testUpdateByPrimaryKey() {
		/**
		 * 不管属性手机否为空，都会根据主键更新
		 * 所以使用这个方法时一般是先查询出来记录，
		 * 再更改查询出来记录中字段的属性，再次更新
		 */
		Sysuser sysuser = sysuserMapper.selectByPrimaryKey("41");
		sysuser.setUsername("test666");
		sysuserMapper.updateByPrimaryKey(sysuser);
	}

}
