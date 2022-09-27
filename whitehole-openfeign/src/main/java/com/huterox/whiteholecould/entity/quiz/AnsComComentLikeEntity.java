package com.huterox.whiteholecould.entity.quiz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_ans_com_coment_like")
public class AnsComComentLikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 给评论的评论点赞的人
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
	private Date creatTime;
	/**
	 * 被评论的评论的id
	 */
	private Long commentid;
	/**
	 * 被评论的评论的作者的id
	 */
	private String commentUserid;
	/**
	 * 被评论的评论的作者的nickname
	 */
	private String commentNickname;

}
