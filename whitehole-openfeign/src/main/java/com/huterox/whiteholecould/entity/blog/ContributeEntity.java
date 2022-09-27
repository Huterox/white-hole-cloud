package com.huterox.whiteholecould.entity.blog;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客的贡献者列表 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_contribute")
public class ContributeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 博客的创建者
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long blogid;
	/**
	 * 贡献者的id
	 */
	private String contributeUserid;
	/**
	 * 贡献者的名称
	 */
	private String contributeUserNickname;
	/**
	 * 
	 */
	private String contributeUserImg;

}
