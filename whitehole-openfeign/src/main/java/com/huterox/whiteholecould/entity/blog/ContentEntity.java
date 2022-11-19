package com.huterox.whiteholecould.entity.blog;

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
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_content")
public class ContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long blogid;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long contentid;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	private String version;

}
