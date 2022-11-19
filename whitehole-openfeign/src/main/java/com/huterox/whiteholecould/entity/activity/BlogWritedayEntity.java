package com.huterox.whiteholecould.entity.activity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@Data
@TableName("activity_blog_writeday")
public class BlogWritedayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
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
	private Integer writeDays;
	/**
	 * 
	 */
	private String achievement;

}
