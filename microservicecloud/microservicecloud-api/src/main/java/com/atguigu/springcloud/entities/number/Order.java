package com.atguigu.springcloud.entities.number;

import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName Order
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/4
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("select_order")
public class Order extends BaseEntityAndDelete<Integer> {
    private Integer numberId; //哪个号码的

    private String processed;
    private String status;
    private String payChanel; //支付途径
    private String customer; // 用户名字
    private String contact; // 联系方式
    private String qq;
    private String wx;
    private String location;

}
