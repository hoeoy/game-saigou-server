package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 直播动画vo
 */
@Data
@NoArgsConstructor
public class LiveVO extends SuperVO {
    @ApiModelProperty(value = "1号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m1;
    @ApiModelProperty(value = "2号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m2;
    @ApiModelProperty(value = "3号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m3;
    @ApiModelProperty(value = "4号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m4;
    @ApiModelProperty(value = "5号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m5;
    @ApiModelProperty(value = "6号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m6;
    @ApiModelProperty(value = "7号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m7;
    @ApiModelProperty(value = "8号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m8;
    @ApiModelProperty(value = "9号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m9;
    @ApiModelProperty(value = "10号马每秒钟的位置数组", example = "[50,100,150,200,250,300,350,400,500,650]", hidden = false)
    private Integer m10;

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
        return null;
    }
}
