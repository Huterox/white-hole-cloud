package com.huterox.whitehole.whiteholeactivity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@Data
@TableName("activity_log")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String actorid;
	/**
	 * 1-修改 2-提交
	 */
	private Integer action;
	/**
	 * 
	 */
	private Long activityid;
	/**
	 * 
	 */
	private Date createTime;

}
