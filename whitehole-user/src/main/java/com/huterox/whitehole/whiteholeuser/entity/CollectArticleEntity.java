package com.huterox.whitehole.whiteholeuser.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 这个表是用来存储用户的收藏的博文的。这边是多了一个该文章的作者，减少了博文的修改时间。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_collect_article")
public class CollectArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long articleid;
	/**
	 * 
	 */
	private String articleTitle;
	/**
	 * 
	 */
	private String articleActor;
	/**
	 * 
	 */
	private String articleInfo;
	/**
	 * 
	 */
	private String articleImg;
	/**
	 * 
	 */
	private Date creatTime;
	/**
	 * 
	 */
	private Integer articleStatus;

}
