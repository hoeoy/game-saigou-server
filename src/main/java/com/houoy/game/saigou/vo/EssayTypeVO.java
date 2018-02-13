package com.houoy.game.saigou.vo;

import com.houoy.common.vo.TreeVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章分类目录管理
 */
@Data
@NoArgsConstructor
public class EssayTypeVO extends TreeVO<EssayTypeVO> {
    @ApiModelProperty(required = false,hidden = true)
    private String pk_type;
    @ApiModelProperty(value = "编码", example = "name",hidden = false)
    private String type_code;
    @ApiModelProperty(value = "名称", example = "name",hidden = false)
    private String type_name;
    @ApiModelProperty(value = "描述", example = "name",hidden = false)
    private String type_desc;
    @ApiModelProperty(required = false,hidden = true)
    private String pk_parent;

    //冗余字段
    @ApiModelProperty(required = false,hidden = true)
    private String text;

    @Override
    public String getPKField() {
        return "pk_type";
    }

    @Override
    public String getTableName() {
        return "cms_essay_type";
    }

    @Override
    public String getParentPKField() {
        return "pk_parent";
    }

    @Override
    public Object getPKValue() {
        return pk_type;
    }

}
