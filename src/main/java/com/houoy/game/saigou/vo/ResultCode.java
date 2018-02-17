package com.houoy.game.saigou.vo;

/**
 * 平台返回值的规范 <br/>
 */

public class ResultCode {

	/**
	 * 业务成功标识
	 */
	public static final Integer SUCCESS = 0;

	/**
	 * 数据校验错误
	 */
	public static final Integer ERROR_DATA = 100;

	/**
	 * 业务数据错误
	 */
	public static final Integer ERROR_SERVICE = 200;

	/**
	 * 数据并发处理错误
	 */
	public static final Integer ERROR_CONCURRENCY = 300;
	/**
	 * 权限管理错误
	 * 
	 */
	public static final Integer ERROR_PERMISSION = 400;

	/**
	 * 用户session错误
	 */
	public static final Integer ERROR_SESSIONINVALID = 500;


	/**
	 * 传入参数错误
	 */
	public static final Integer ERROR_PARAMETER = 600;
	
	/**
	 * 系统未知错误
	 */
	public static final Integer ERROR_SYSTEM = 999;
}
