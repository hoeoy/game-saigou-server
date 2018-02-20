package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播动画vo
 */
@Data
@NoArgsConstructor
public class LiveVO {
    @ApiModelProperty(value = "1号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m1 = new ArrayList();
    @ApiModelProperty(value = "2号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m2 = new ArrayList();
    @ApiModelProperty(value = "3号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m3 = new ArrayList();
    @ApiModelProperty(value = "4号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m4 = new ArrayList();
    @ApiModelProperty(value = "5号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m5 = new ArrayList();
    @ApiModelProperty(value = "6号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m6 = new ArrayList();
    @ApiModelProperty(value = "7号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m7 = new ArrayList();
    @ApiModelProperty(value = "8号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m8 = new ArrayList();
    @ApiModelProperty(value = "9号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m9 = new ArrayList();
    @ApiModelProperty(value = "10号马每秒钟的位置数组,长度为11，第11位为超出屏幕位", example = "[50,100,150,200,250,300,350,400,500,650,1000]", hidden = false)
    private List<Integer> m10 = new ArrayList();
}
