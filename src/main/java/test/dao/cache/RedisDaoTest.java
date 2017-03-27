package test.dao.cache;

import org.junit.Before;
import org.junit.Test;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.entity.Seckill;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisDaoTest {
	
	private long id = 1001;
	
	private RedisDao redisDao;
	
	private SeckillDao seckillDao;
	
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/spring-dao.xml");
		redisDao = ac.getBean("redisDao",RedisDao.class);
		seckillDao = ac.getBean("seckillDao",SeckillDao.class);
	}
	
	@Test
	public void seckillTest(){
		//get and put
		Seckill seckill = redisDao.getSeckill(id);
		if(seckill == null){
			seckill = seckillDao.queryById(id);
			if(seckill != null){
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				seckill = redisDao.getSeckill(id);
				System.out.println(seckill);
			}
			
		}
	}
	
}
