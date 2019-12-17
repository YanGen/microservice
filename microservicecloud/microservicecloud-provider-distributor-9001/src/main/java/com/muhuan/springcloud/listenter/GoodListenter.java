package com.muhuan.springcloud.listenter;

import com.muhuan.springcloud.service.GoodsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName GoodListenter
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/17
 * @Version 1.0.0
 **/
@Component
public class GoodListenter {
    @Autowired
    private GoodsService goodsService;

    @RabbitListener(queues = "goodsQueue")
    public void deductStock(long id) {
        System.out.println("删库存  : " + id);
        goodsService.deductStock(id);
    }
}
