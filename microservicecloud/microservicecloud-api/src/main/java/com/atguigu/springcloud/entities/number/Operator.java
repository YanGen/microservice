package com.atguigu.springcloud.entities.number;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName Operator
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/4
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("operator")
public class Operator {
    private String name;

}
