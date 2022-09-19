package com.huterox.whiteholecould.entity.blog;

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
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_community_belong")
public class CommunityBelongEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long blogid;
	/**
	 * 
	 */
	private Long communityid;
	/**
	 * 
	 */
	private Date createTime;

}
