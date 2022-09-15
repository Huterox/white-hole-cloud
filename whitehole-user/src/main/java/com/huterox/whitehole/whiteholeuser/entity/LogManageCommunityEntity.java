package com.huterox.whitehole.whiteholeuser.entity;

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
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_log_manage_community")
public class LogManageCommunityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 1-创建社区 2-删除社区
	 */
	private Integer action;
	/**
	 * 
	 */
	private Long communityid;
	/**
	 * 
	 */
	private String communityName;

}
