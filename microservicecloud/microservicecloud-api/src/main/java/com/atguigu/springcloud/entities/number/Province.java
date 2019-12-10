package com.atguigu.springcloud.entities.number;

import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName Province
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/7
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("select_province")
public class Province  extends BaseEntityAndDelete<Integer> {
    private String province;
}
