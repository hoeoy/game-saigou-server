package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author andyzhao
 */
@Data
@NoArgsConstructor
public class PersonVO extends SuperVO {

    @ApiModelProperty(value = "用户pk",hidden = false)
    private String pk_person;
    @ApiModelProperty(value = "用户编码",hidden = false)
    private String person_code;
    @ApiModelProperty(value = "格言",hidden = false)
    private String person_alias;
    @ApiModelProperty(value = "用户名称",hidden = false)
    private String person_name;
    @ApiModelProperty(value = "密码",hidden = false)
    private String password;
    @ApiModelProperty(value = "手机号",hidden = false)
    private String mobile;
    @ApiModelProperty(value = "邮箱",hidden = false)
    private String email;
    @ApiModelProperty(hidden = true)
    private String identity;
    @ApiModelProperty(value = "年龄",hidden = false)
    private String age;
    @ApiModelProperty(hidden = true)
    private String address;
    @ApiModelProperty(hidden = true)
    private String birthday;
    @ApiModelProperty(hidden = true)
    private String birthplace;
    @ApiModelProperty(hidden = true)
    private String country;
    @ApiModelProperty(hidden = true)
    private String province;
    @ApiModelProperty(hidden = true)
    private String city;
    @ApiModelProperty(hidden = true)
    private String town;
    @ApiModelProperty(hidden = true)
    private String village;
    @ApiModelProperty(hidden = true)
    private String education;
    @ApiModelProperty(hidden = true)
    private String job;
    @ApiModelProperty(hidden = true)
    private String income;
    @ApiModelProperty(hidden = true)
    private String has_house;
    @ApiModelProperty(hidden = true)
    private String has_car;
    @ApiModelProperty(hidden = true)
    private String marriage;
    @ApiModelProperty(hidden = true)
    private String emotion_count;
    @ApiModelProperty(hidden = true)
    private String mate_type;
    @ApiModelProperty(hidden = true)
    private String habbit;
    @ApiModelProperty(hidden = true)
    private String family;
    @ApiModelProperty(hidden = true)
    private String health;
    @ApiModelProperty(hidden = true)
    private String single_long;
    @ApiModelProperty(hidden = true)
    private String enable_marriagetime;
    @ApiModelProperty(hidden = true)
    private String idea_love;
    @ApiModelProperty(hidden = true)
    private String idea_value;
    @ApiModelProperty(hidden = true)
    private String idea_goal;
    @ApiModelProperty(hidden = true)
    private String idea_swear;

    @ApiModelProperty(hidden = true)
    private String img1_comment;
    @ApiModelProperty(hidden = true)
    private String img2_comment;
    @ApiModelProperty(hidden = true)
    private String img3_comment;
    @ApiModelProperty(hidden = true)
    private String img4_comment;
    @ApiModelProperty(hidden = true)
    private String img5_comment;

    @ApiModelProperty(hidden = true)
    private String portraitPath;
    @ApiModelProperty(hidden = true)
    private byte[] portrait;
    @ApiModelProperty(hidden = true)
    private byte[] img1;
    @ApiModelProperty(hidden = true)
    private byte[] img2;
    @ApiModelProperty(hidden = true)
    private byte[] img3;
    @ApiModelProperty(hidden = true)
    private byte[] img4;
    @ApiModelProperty(hidden = true)
    private byte[] img5;
    @ApiModelProperty(hidden = true)
    private String safe_state;

    @Override
    public Object getPKValue() {
        return pk_person;
    }

    @Override
    public String getPKField() {
        return "pk_person";
    }


    @Override
    public String getTableName() {
        return "im_person";
    }
}
