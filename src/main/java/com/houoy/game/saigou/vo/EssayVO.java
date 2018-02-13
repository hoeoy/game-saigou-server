package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 此类没有使用getter和setter方法，保持简洁
 */
@Data
@NoArgsConstructor
public class EssayVO extends SuperVO {
    @ApiModelProperty(required = false,hidden = true)
    private String pk_essay;
    @ApiModelProperty(value = "编码", example = "name",hidden = false)
    private String essay_code;
    @ApiModelProperty(value = "名称", example = "name",hidden = false)
    private String essay_name;

    @ApiModelProperty(value = "副名称", example = "name",hidden = false)
    private String essay_subname;
    @ApiModelProperty(value = "内容", example = "name",hidden = false)
    private String essay_content;

    @ApiModelProperty(value = "是否发布（1是，=0否）", example = "name",hidden = false)
    private Integer is_publish;//>1是，=0否
    @ApiModelProperty(value = "开始时间", example = "name",hidden = false)
    private String ts_start;//活动开始时间
    @ApiModelProperty(value = "结束时间", example = "name",hidden = false)
    private String ts_end;//活动结束时间
    @ApiModelProperty(value = "属于哪个类型", example = "name",hidden = false)
    private String pk_type;//属于哪个类型

    @ApiModelProperty(value = "作者用户pk", example = "name",hidden = false)
    private String pk_person;//属于哪个类型
    @ApiModelProperty(value = "作者用户名称", example = "name",hidden = false)
    private String person_name;//属于哪个类型
    @ApiModelProperty(value = "缩略图路径",hidden = false)
    private String path_thumbnail;

    //冗余字段
    @ApiModelProperty(value = "是否已经关注此条记录的作者.1为是，0为否", example = "1",hidden = false)
    private String has_follow;

    @Override
    public String getPKField() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Object getPKValue() {
        return pk_essay;
    }
}






