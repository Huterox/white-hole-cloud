package com.huterox.whitehole.whiteholeactivity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 这个是我们创建的活动的一些内容，这个是在运营阶段由管理人员创建的，当运营确定好活动之后，
我们这边就会进行创建新的活动，之后在技术层面实现，注意：activity 和 admin 都是管理人使用的模块。

注意的是，我们的这个记录的是我们这个由管理人员创建的玩意来的，实际上我们还有系统自动的一些活动，这些活动是系统级别的。




 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@Data
@TableName("activity_activity")
public class ActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long activityid;
	/**
	 * 
	 */
	private String activityTitle;
	/**
	 * 
	 */
	private String activityInfo;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * 
	 */
	private Integer viewNumber;
	/**
	 * 我们这边统计一些活动的受欢迎程度
	 */
	private Integer likeNumber;
	/**
	 * 负责人1
	 */
	private String actorid1;
	/**
	 * 活动的负责人1
	 */
	private String actor1Nickname;
	/**
	 * 
	 */
	private String actor2;
	/**
	 * 
	 */
	private String actor2Nickname;
	/**
	 * 
	 */
	private String actor3;
	/**
	 * 
	 */
	private String actor3Nickname;
	/**
	 * 
	 */
	private String actor4;
	/**
	 * 
	 */
	private String actorNickname;

}
