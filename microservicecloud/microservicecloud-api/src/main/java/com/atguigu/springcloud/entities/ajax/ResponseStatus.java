package com.atguigu.springcloud.entities.ajax;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName ResponseStatus
 * @Description TODO 前后端交互状态码规范
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/8/7
 * @Version 1.0.0
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum ResponseStatus {

    SUCCESS(0, "SUCCESS"),
    FAIL(-1, "FAIL");

    private final Integer code;
    private final String msg;

}
