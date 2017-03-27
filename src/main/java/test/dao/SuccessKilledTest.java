package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.entity.SuccessKilled;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SuccessKilledTest {
	
	private SuccessKilledDao successKilledDao;
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-dao.xml");
		successKilledDao = ac.getBean("successKilledDao",SuccessKilledDao.class);
	}
	
	@Test
	public void testInsertSuccessKilled(){
		/*
		 * 1:
		 * 		JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@29a220e6] will not be managed by Spring
				Preparing: insert ignore into success_killed(seckill_id,user_phone) values(?,?) 
				Parameters: 1000(Long), 18817314957(Long)
				Updates: 1
		   2:
		   		JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@17a73d30] will not be managed by Spring
				Preparing: insert ignore into success_killed(seckill_id,user_phone) values(?,?) 
				Parameters: 1000(Long), 18817314957(Long)
				Updates: 0 -->多次插入无效，联合主键起作用
		   		
		 */
		long id=1001;
		long phone=18817314957l;
		int insertCount = successKilledDao.insertSuccessKilled(id, phone);
		System.out.println(insertCount);
	}
	
	@Test
	public void testQueryByIdWithSeckill(){
		/*
		 *JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@7619cc1b] will not be managed by Spring
		  Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id=s.seckill_id where sk.seckill_id=? and sk.user_phone=? 
		  Parameters: 1000(Long), 18817314957(Long)
		  Total: 1
		  Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@60c017e9]
		  SuccessKilled [seckillId=1000, userPhone=18817314957, state=-1]
		  Seckill [seckillId=1000, 
		  name=1000元秒杀大闸蟹, number=100, 
		  startTime=Wed Mar 22 00:00:00 CST 2017, 
		  endTime=Thu Mar 23 00:00:00 CST 2017, 
		  createTime=Tue Mar 21 14:09:30 CST 2017]
		  --------------------------------------------
		     修改默认插入state：0
		  --------------------------------------------   
		  SuccessKilled [seckillId=1001, userPhone=18817314957, state=0]
		  Seckill [seckillId=1001, 
		  name=500元秒杀大白菜, number=100, 
		  startTime=Thu Mar 23 00:00:00 CST 2017, 
		  endTime=Fri Mar 24 00:00:00 CST 2017, 
		  createTime=Tue Mar 21 14:09:30 CST 2017]
		 */
		long id=1001;
		long phone=18817314957l;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
	}
}
