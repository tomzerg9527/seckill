package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	/**
	 * 插入购买明细,可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return 插入的结果集数量
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
	
	/**
	 * 根据id查询successKilled并携带秒杀产品对象实体
	 * @param seckillId
	 * @return
	 * 一个seckillId对应的是多个SuccessKilled，要查一个SuccessKilled还需userPhone
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
