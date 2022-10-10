package com.huterox.whitehole.whiteholeuser.entity.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 这个是用户感兴趣的博文标签
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_label_blog_like")
public class LabelBlogLikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private String label1;
	/**
	 * 
	 */
	private String label2;
	/**
	 * 
	 */
	private String label3;
	/**
	 * 
	 */
	private String label4;
	/**
	 * 
	 */
	private String label5;
	/**
	 * 
	 */
	private String label6;
	/**
	 * 
	 */
	private String label7;
	/**
	 * 
	 */
	private String label8;

}
