package com.huterox.whiteholecould.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这个是用户创建的社区
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_manage_community")
public class ManageCommunityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long communityid;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String communityName;
	/**
	 * 
	 */
	private String communityInfo;
	/**
	 * 
	 */
	private String communityImg;

}
