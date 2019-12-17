package com.muhuan.springcloud.service;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import com.muhuan.springcloud.mapper.GoodsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GoodService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@Service
public class GoodsService extends BaseService<Goods> {

    private final GoodsMapper mapper;

    public GoodsService(GoodsMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
    @Transactional
    public ResponseResult deductStock(long id) {
        Goods goods = getById(id);
        int stock = goods.getStock() - 1;
        goods.setStock(stock);
        boolean update = update(goods);
        return ResultGeneratorUtil.getResultSuccess();
    }
}
