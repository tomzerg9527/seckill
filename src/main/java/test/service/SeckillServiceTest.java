package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SeckillService seckillService;

	@Before
	public void before() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:conf/spring-dao.xml",
				"classpath:conf/spring-service.xml");
		seckillService = ac.getBean("seckillService", SeckillService.class);
	}

	@Test
	public void testGetSeckillList() {
		// Closing non transactional SqlSession
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void testGetById() {
		// Closing non transactional SqlSession
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

	/*@Test
	// 集成测试代码完整逻辑,注意可重复执行。
	public void testExportSeckillUrl() {
		long id = 1001;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if (exposer.isExposed()) {
			logger.info("exposre={}", exposer);
			long phone = 18817314958l;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService
						.executeSeckill(id, phone, md5);
				logger.info("seckillExecution={}", seckillExecution);
			} catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.warn("exposer={}", exposer);
		}*/
		// Exposer [exposed=true, md5=375cde0039f682008877ab750a942e08, seckillId=1001, now=0, start=0, end=0]
		// ERROR test.service.SeckillServiceTest - seckill repeated
		//-----------------------------------------------------------------------------------------------------
		/*
		 * Exposer [exposed=true, md5=375cde0039f682008877ab750a942e08, seckillId=1001, now=0, start=0, end=0]
		 * seckillExecution=SeckillExecution [seckillId=1001, 
		 * state=1, 
		 * stateInfo=秒杀成功, 
		 * successKilled=SuccessKilled 
		 * [seckillId=1001, userPhone=18817314958, state=0]]
		 */
	//}

	/*@Test
	public void testExecuteSeckill() {
		long id = 1005;
		String md5 = "239516920276c004bbb2af68e765877d";
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(
					id, 18817314957l, md5);
			logger.info("seckillExecution={}", seckillExecution);
		} catch (SeckillCloseException e) {
			logger.error(e.getMessage());
		} catch (RepeatKillException e) {
			logger.error(e.getMessage());
		}*/
		/*
		 * 1: SeckillExecution [seckillId=1005, state=1, stateInfo=秒杀成功,
		 * successKilled=SuccessKilled [seckillId=1005, userPhone=18817314957,
		 * state=0]]
		 * 
		 * 2: org.seckill.exception.RepeatKillException: seckill repeated
		 */
	//}
	
	@Test
	public void executeSeckillProcedure(){
		long seckillId = 1007;
		long phone = 13529771101l;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5 = exposer.getMd5();
			SeckillExecution execetion = seckillService.executeSeckillProcedure(seckillId, phone, md5);
			logger.info(execetion.getStateInfo());
		}
	}
}
