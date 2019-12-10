package com.atguigu.springcloud.entities.number;

import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.atguigu.springcloud.entities.number.Annotation.FieldAnnotation;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName Card
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/4
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("select_card")
@FieldAnnotation
public class Card extends BaseEntityAndDelete<Integer> {
    @TableField("i_number")
    private String iNumber;
    private Float price;
    private Integer operatorId;
    private Integer provinceId;
    private Integer cityId;
    private String includedFee;
}
