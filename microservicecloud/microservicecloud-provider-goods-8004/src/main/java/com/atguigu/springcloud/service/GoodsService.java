package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.goods.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@Service
public class GoodsService extends BaseService<Goods> {
    public GoodsService(BaseMapper<Goods> mapper) {
        super(mapper);
    }
}
