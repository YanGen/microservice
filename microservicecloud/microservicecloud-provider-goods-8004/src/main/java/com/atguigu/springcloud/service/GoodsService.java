package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.service.BaseService;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@Service
public class GoodsService extends BaseService<Goods> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public GoodsService(BaseMapper<Goods> mapper) {
        super(mapper);
    }
    @Transactional
    public ResponseResult deductStock(long id) {

        String deductId = String.valueOf(id);

        String clientId = UUID.randomUUID().toString();

        boolean upd = false; //后置upd标志

        try {
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(deductId, clientId, 30, TimeUnit.SECONDS);

            if (!result) {
                return ResultGeneratorUtil.getResultFail("没有拿到锁！");
            }

            String objPre = stringRedisTemplate.opsForValue().get(deductId+" stock");
            if (objPre == null) {
                Goods goods = getById(id);
                int stock = goods.getStock();
                if (stock < 1) {
                    stringRedisTemplate.opsForValue().set(deductId+" stock", 0 + "");
                    return ResultGeneratorUtil.getResultFail("库存不够！");
                }
                stock = stock - 1;
                stringRedisTemplate.opsForValue().set(deductId+" stock", stock +"");
                upd = true;
                return ResultGeneratorUtil.getResultFail("库存-1！");
            } else {
                int stock = Integer.parseInt(objPre);
                stringRedisTemplate.opsForValue().set(deductId+" stock", stock - 1 +"");
                upd = true;

                return ResultGeneratorUtil.getResultFail("库存-1！");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(deductId))){
                stringRedisTemplate.delete(deductId);
            }
            if (upd){
                //异步UPD
                rabbitTemplate.convertAndSend("goodsDirectExchange","goodsQueue",id);
            }
        }
    }
}
