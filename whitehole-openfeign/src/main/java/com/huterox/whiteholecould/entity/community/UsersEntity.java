package com.huterox.whiteholecould.entity.community;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 加入到社区的用户
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@Data
@TableName("community_users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 加入社区的用户
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String userimg;
	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	private Long communityid;
	/**
	 * 
	 */
	private String communityName;

}
