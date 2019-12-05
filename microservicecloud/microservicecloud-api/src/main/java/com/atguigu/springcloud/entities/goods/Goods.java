package com.atguigu.springcloud.entities.goods;

import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("t_goods")
public class Goods extends BaseEntityAndDelete<Long> {

    private String name;

}
