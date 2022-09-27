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
@TableName("blogs_blog")
public class BlogEntity implements Serializable {
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
	private Long contentid;
	/**
	 * 
	 */
	private String blogTitle;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String userImg;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer viewNumber;
	/**
	 * 
	 */
	private Integer likeNumber;
	/**
	 * 
	 */
	private Integer collectNumber;
	/**
	 * 1-正常 2-审核 3-删除 4-下架
	 */
	private Integer status;
	/**
	 * 1-公开 2-私密
	 */
	private Integer level;
	/**
	 * 
	 */
	private Integer forkNumber;

}
