package com.huterox.whiteholecould.entity.community;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("community_community")
public class CommunityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long communityid;
	/**
	 * 
	 */
	private String userid;
	/**
	 * 
	 */
	private String communityTitle;
	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	private String userimg;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String communityImg;
	/**
	 * 
	 */
	private String communityInfo;
	/**
	 * 
	 */
	private Integer memberNumber;
	/**
	 * 
	 */
	private Integer blogNumber;
	/**
	 * 1-正常 2-审核 3-下架
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer communityLike;

}
