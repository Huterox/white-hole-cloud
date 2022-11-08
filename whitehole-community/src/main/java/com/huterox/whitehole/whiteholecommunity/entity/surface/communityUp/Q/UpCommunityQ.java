package com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q;


import com.huterox.whiteholecould.entity.community.CommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpCommunityQ {

    @NotEmpty
    private String url;
    @NotEmpty
    @Length(min = 10,max = 150,message="长度不符合要求")
    private String desc;
    @NotEmpty
    @Length(min = 4,max = 20,message="长度不符合要求")
    private String name;
    @NotEmpty
    private String userid;
}
