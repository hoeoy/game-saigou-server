package com.houoy.game.saigou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台返回Bean
 *
 * @author guyj3@citic.com
 * @create 2017-03-29 15:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 业务表示代码
     */
    private Integer code;
    /**
     * 业务提示信息
     */
    private String msg;

    /**
     * 业务操作返回结果
     */
    private T content;

}
