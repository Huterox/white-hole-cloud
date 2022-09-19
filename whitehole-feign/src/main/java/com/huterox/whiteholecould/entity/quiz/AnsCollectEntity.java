package com.huterox.whiteholecould.entity.quiz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这个是问题回答的收藏，有哪些用户收藏了，我们的筛选标准和我们这个的问题的标准是一样的
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_ans_collect")
public class AnsCollectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 收藏者的一个id
	 */
	@TableId
	private String userid;
	/**
	 * 回答者的一个id
	 */
	private String ansUserid;
	/**
	 * 回答的一个id
	 */
	private Long ansid;
	/**
	 * 收藏者的一个昵称
	 */
	private String userNickname;
	/**
	 * 
	 */
	private Date createTime;

}
