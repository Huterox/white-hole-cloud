package com.huterox.whitehole.whiteholeuser.entity.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户的一些日志信息
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_log_base")
public class LogBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 用户登录时间
	 */
	private Date loginTime;
	/**
	 * 用户退出时间
	 */
	private Date outTime;
	/**
	 * 用户的一些操作描述,由系统自动记录的一些信息
	 */
	private String systemAction;
	/**
	 * 系统对用户支付信息的记录
	 */
	private String rechargeAction;
	/**
	 * 
	 */
	private Date rechargeTime;
	/**
	 * 对用户的警告，用户的一些非法操作
	 */
	private String warnAction;
	/**
	 * 
	 */
	private Date warnTime;
	/**
	 * 系统错误的记录
	 */
	private String errorAction;
	/**
	 * 
	 */
	private Date errorTime;

}
