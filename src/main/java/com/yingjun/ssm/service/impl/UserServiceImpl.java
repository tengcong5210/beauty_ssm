package com.yingjun.ssm.service.impl;

import com.yingjun.ssm.cache.RedisCache;
import com.yingjun.ssm.dao.UserDao;
import com.yingjun.ssm.entity.User;
import com.yingjun.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisCache cache;
	
	
	@Override
	public List<User> getUserList(int offset, int limit) {
		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
		//先去缓存中取
		List<User> result_cache=cache.getListCache(cache_key, User.class);
		if(result_cache==null){
			//缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			result_cache=userDao.queryAll(offset, limit);
			cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			LOG.info("put cache with key:"+cache_key);
		}else{
			LOG.info("get cache with key:"+cache_key);
		}
		return result_cache;
	}

	/**
	 * 测试事务
	 * @return
	 */
	@Override
	@Transactional
	public int updateUser() {
		/*User user=userDao.queryByPhone(18768128888L);
		user.setScore(13);
		userDao.updateUser(user);
		int i=1/0;//此处设置有异常，此种情况会回滚
		*/

		/*this.testPrivateInnerMethodTrans();*/

		//updateUser 方法上加上注解，则会回滚，不加注解不会回滚
		this.testPublicInnerMethodTrans();
		return 0;
	}

	/**
	 * 测试私有方法事务
	 * @return
	 */
	@Transactional
	private int testPrivateInnerMethodTrans(){
		User user=userDao.queryByPhone(18768128888L);
		user.setScore(12);
		userDao.updateUser(user);
		//此处设置有异常，正常的情况下应该回滚，但是由于方法没有在接口中定义
		// spring在做bean解析的时候不能生成该方法的代理，故不能是事务起效果
		int i=1/0;
		return 0;
	}

	/**
	 * 测试公有内部方法事务
	 * @return
	 */
	@Transactional
	public int testPublicInnerMethodTrans(){
		User user=userDao.queryByPhone(18768128888L);
		user.setScore(14);
		userDao.updateUser(user);
		//此处设置有异常，正常的情况下应该回滚，但是由于方法没有在接口中定义
		// spring在做bean解析的时候不能生成该方法的代理，故不能是事务起效果
		int i=1/0;
		return 0;
	}

	@Override
	@Transactional
	public int updateUserOnExTest() {
		//1.该方式 不会回滚，因为异常被try catch了。
		try {
			User user=userDao.queryByPhone(18768128888L);
			user.setScore(14);
			userDao.updateUser(user);
			int i=1/0;
		}catch (Exception e){
			LOG.error("updateUserOnExTest 异常",e);
		}
		return 0;
	}

	/**
	 * 抛出检查异常，如果不做特殊处理（即不指定rollbackFor）,不会回滚，如果rollbackFor = {Exception.class},则会回滚
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int updateUserOnThrowExTest() throws Exception {
		try {
			User user=userDao.queryByPhone(18768128888L);
			user.setScore(16);
			userDao.updateUser(user);
			int i=1/0;
		}catch (Exception e){
			LOG.error("updateUserOnExTest 异常",e);
			throw new Exception("updateUserOnExTest 异常xxxxxxxxxx");
		}
		return 0;
	}

	@Override
	@Transactional
	public int updateUserOnThrowRuntimeExTest() {
		try {
			User user=userDao.queryByPhone(18768128888L);
			user.setScore(16);
			userDao.updateUser(user);
			int i=1/0;
		}catch (Exception e){
			LOG.error("抛出运行时异常",e);
			throw new RuntimeException("抛出运行时异常");
		}
		return 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
	public int updateUserOnRequiredTest() throws Exception{
		System.out.println("=============开始updateUserOnRequiredTest");
		User user=userDao.queryByPhone(18768128888L);
		user.setScore(19);
		userDao.updateUser(user);
		int i=1/0;
		return 0;
	}
	@Override
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Transactional(rollbackFor = {Exception.class})
	public int updateUserOnRequiredNewTest() throws Exception {
		System.out.println("============开始updateUserOnRequiredNewTest");

		this.updateUserOnRequiredTest();

		User user=userDao.queryByPhone(18768128888L);
		user.setScore(21);
		userDao.updateUser(user);
		//int i=1/0;
		return 0;
	}
}
