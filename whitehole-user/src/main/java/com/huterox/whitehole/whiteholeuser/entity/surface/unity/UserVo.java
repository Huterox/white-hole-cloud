package com.huterox.whitehole.whiteholeuser.entity.surface.unity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userid;
    private String nickname;
    private String info;
    private String userImg;
    private String creatTime;


}
