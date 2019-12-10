package com.muhuan.springcloud.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muhuan.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/7
 * @Version 1.0.0
 **/
@Service
public class OrderService extends BaseService<Order> {
    @Autowired
    public OrderService(BaseMapper<Order> mapper) {
        super(mapper);
    }
}
