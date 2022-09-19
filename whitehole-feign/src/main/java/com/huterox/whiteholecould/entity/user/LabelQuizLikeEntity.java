package com.huterox.whiteholecould.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_label_quiz_like")
public class LabelQuizLikeEntity implements Serializable {
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
