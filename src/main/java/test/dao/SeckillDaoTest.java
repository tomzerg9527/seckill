package test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 * @author Administrator
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
//@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	//注入Dao实现类依赖
		private SeckillDao seckillDao;
		
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-dao.xml");
		seckillDao = ac.getBean("seckillDao",SeckillDao.class);
	}
	
	/*
	 *
	 */
	@Test
	public void testQueryById(){
		long id=1000;
		Seckill seckill=seckillDao.queryById(id);
		System.out.println(seckill);
	}
	
	/*
	 * java.lang.AbstractMethodError: org.mybatis.spring.transaction.SpringManagedTransaction.getTimeout()Ljava/lang/Integer;
	 * mybaits版本问题：改成3.3.0
	 */
	@Test
	public void testQueryAll(){
		//List<Seckill> queryAll(int offet,int limit);
		//java没有保存形参的记录:queryAll(arg0,arg1);
		//@Param("offset") arg0,@Param("limit") arg1
		List<Seckill> seckills = seckillDao.queryAll(0,10);
		for(Seckill s:seckills)
			System.out.println(s);
	}
	
	@Test
	public void testReduceNumber(){
		/*
		 *  Closing non transactional SqlSession
		 *  
		 *  org.springframework.dao.TransientDataAccessResourceException: 
		 *	Error updating database.  Cause: java.sql.SQLException: Could not retrieve transation read-only status server
		 *	The error may involve defaultParameterMap
		 *	The error occurred while setting parameters
		 * 	SQL: update     seckill   set    number=number-1   where seckill_id = ?   and start_time  <=  ?   and end_time > ?   and number > 0;
		 * 	Cause: java.sql.SQLException: Could not retrieve transation read-only status server
		 *	SQL []; Could not retrieve transation read-only status server; nested exception is java.sql.SQLException: Could not retrieve transation read-only status server
		 */
		//mysql依赖版本问题:改为5.0.8
		Date killTime=new Date();
		long id=1004l;
		int updateCount = seckillDao.reduceNumber(id,killTime);
		System.out.println(updateCount);
	}
}
