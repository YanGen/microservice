package com.atguigu.springcloud.entities.ajax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName ResponseResult
 * @Description TODO 前后端交互响应结果集Bean
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/8/6
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseResult<T> {
    private String msg;
    private int code;
    private T data;
}
