package com.atguigu.springcloud.entities.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/9
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class BaseEntity implements Serializable, Cloneable {
    @Field("insert_time")
    @JsonSerialize(using = ToStringSerializer.class)
    private long insertTime;
    @Field("update_time")
    @JsonSerialize(using = ToStringSerializer.class)
    private long updateTime;
}
