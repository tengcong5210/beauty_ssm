package com.yingjun.ssm.service;

import com.yingjun.ssm.entity.User;

import java.util.List;

public interface UserService {

	List<User> getUserList(int offset, int limit);

	int updateUser();

	/**
	 * 测试有异常时 事务的回滚
	 * @return
	 */
	int updateUserOnExTest();
	/**
	 * 测试抛出检查异常
	 * @return
	 */
	int updateUserOnThrowExTest() throws Exception;
	/**
	 * 测试抛出运行时异常
	 * @return
	 */
	int updateUserOnThrowRuntimeExTest();

	/**
	 * 事务传播属性 PROPAGATION_REQUIRES
	 * @return
	 */
	int updateUserOnRequiredTest();
	/**
	 * 事务传播属性 PROPAGATION_REQUIRES_NEW
	 * @return
	 */
	int updateUserOnRequiredNewTest();




}
