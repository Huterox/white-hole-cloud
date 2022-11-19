package com.huterox.whiteholecould.entity.blog;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 当用户同意之后，那么博文对应的内容就会被覆盖，同时在blog的贡献表当中显示提交用户的信息！
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_pull_content")
public class PullContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 提交者id
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long contentid;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date createTime;

}
