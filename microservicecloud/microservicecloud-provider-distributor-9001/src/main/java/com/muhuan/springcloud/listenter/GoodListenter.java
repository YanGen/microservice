package com.muhuan.springcloud.listenter;

import com.muhuan.springcloud.service.EmailService;
import com.muhuan.springcloud.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private EmailService emailService;

    private static final Logger Log = LoggerFactory.getLogger(GoodListenter.class);

    @RabbitListener(queues = "goodsQueue")
    public void deductStock(long id) {
        Log.info("减库存:" + id);
        boolean update = goodsService.deductStock(id);
        String from = "zorage@163.com";
        String to = "zorage@qq.com";
        String subject = "提醒";
        String text = "库存减-1";
        if(!update) text = "减库存失败";
        emailService.sendSimpleEmail(from,to,subject,text);
    }
}
