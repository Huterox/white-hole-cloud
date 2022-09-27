package com.huterox.whiteholecould.entity.community;

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
 * @date 2022-09-15 10:34:12
 */
@Data
@TableName("community_like")
public class LikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long communityid;
	/**
	 * 
	 */
	private String userid;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String communityName;

}
