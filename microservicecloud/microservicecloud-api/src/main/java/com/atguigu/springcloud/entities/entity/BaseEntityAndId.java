package com.atguigu.springcloud.entities.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.data.annotation.Id;

/**
 * @ClassName BaseEntityAndId
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/9
 * @Version 1.0.0
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public abstract class BaseEntityAndId<T> extends BaseEntity{
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private T id;
}
