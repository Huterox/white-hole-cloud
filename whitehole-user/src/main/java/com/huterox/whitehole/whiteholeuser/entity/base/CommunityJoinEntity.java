package com.huterox.whitehole.whiteholeuser.entity.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_community_join")
public class CommunityJoinEntity implements Serializable {
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
	 * 创建该社区的是谁
	 */
	private Integer communityUserid;
	/**
	 * 社区的封面
	 */
	private String communityImg;
	/**
	 * 
	 */
	private String communityInfo;
	/**
	 * 
	 */
	private String communityName;
	/**
	 * 
	 */
	private String communityUserimg;
	/**
	 * 社区的创建者的姓名
	 */
	private String communityUserNickname;

}
