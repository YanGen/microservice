package com.atguigu.springcloud.entities.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @ClassName BaseEntityAndDelete
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
public class BaseEntityAndDelete <T> extends BaseEntityAndId<T> {
    @Field("delete_flag")
    protected Integer deleteFlag;
    @JsonIgnore
    @TableField(exist = false)
    @Transient
    protected List<Class<?>>  foreignKeys;

}
