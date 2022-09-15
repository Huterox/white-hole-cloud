package com.huterox.whitehole.whiteholeuser.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 这个就是用户收藏的问题的回答了，我们会直接定位到问题的具体的回答页面。
而，对于用户收藏的问题的话，我们是直接跳转到用户所收藏的问题页面。

由于这些玩意都是收藏的部分，所以的话，我们需要存储一个状态，我们默认状态都是1 也就是正常的，当访问具体的页面
的时候，做一个判断，如果说当前是status，他是异常的例如被删除或者下架了，那么再设置这个玩意为真实的status，用户可以选择删除这个玩意，玩意这个玩意又
正常了，失误删除就可惜了。



 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_collect_ans")
public class CollectAnsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long ansid;
	/**
	 * 
	 */
	private String ansInfo;
	/**
	 * 
	 */
	private String quizTitle;
	/**
	 * 
	 */
	private Long quizid;
	/**
	 * 问题的回答者名字
	 */
	private String ansActor;
	/**
	 * 提问者名字
	 */
	private String quizActor;
	/**
	 * 提问者的id
	 */
	private String quizActorId;
	/**
	 * 问题回答者的id
	 */
	private String ansActorId;
	/**
	 * 
	 */
	private Date creatTime;
	/**
	 * 
	 */
	private Integer ansStatus;

}
