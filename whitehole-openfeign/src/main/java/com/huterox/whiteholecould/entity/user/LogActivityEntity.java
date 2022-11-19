package com.huterox.whiteholecould.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_log_activity")
public class LogActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 用户参加的活动的id
	 */
	private Long activityid;
	/**
	 * 
	 */
	private Date creatTime;
	/**
	 * 参加的活动的名称
	 */
	private String activityTitle;
	/**
	 * 活动的大致情况
	 */
	private String activityInfo;
	/**
	 * 活动创建的时间
	 */
	private Date activityStartTime;
	/**
	 * 活动的结束时间
	 */
	private Date activityEndTime;
	/**
	 * 用户在活动当中的情况
	 */
	private String activityAction;

}
