package yycgpt.base.dao.mapper;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import yycgpt.base.pojo.po.Sysuser;
import junit.framework.TestCase;

public class SysuserCustomMapperTest extends TestCase {

	private ApplicationContext applicationContext;
	
	
	protected void setUp() throws Exception {
		//获取spring容器
		applicationContext = new ClassPathXmlApplicationContext(new String[]{
				"spring/applicationContext.xml",
				"spring/applicationContext-dao.xml"
		});
	}

	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testFindSysuserById() throws Exception {
		
		//SysuserCustomMapper customMapper = (SysuserCustomMapper) applicationContext.getBean("sysuserCustomMapper");
		
		 //Sysuser sysuser = customMapper.findSysuserById("33");
		//System.out.println(sysuser);
	}

}
