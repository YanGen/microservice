package com.atguigu.springcloud.entities.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName EntityStatus
 * @Description TODO 数据库实体类记录标记
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/10
 * @Version 1.0.0
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum EntityStatus {
    DELETE(1), //删除标记
    EXIST(0);   //存在标记

    private final Integer flag;
}
