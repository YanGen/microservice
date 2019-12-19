package com.muhuan.springcloud.service;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import com.muhuan.springcloud.mapper.GoodsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName GoodService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@Service
public class GoodsService extends BaseService<Goods> {
    private static final Logger Log = LoggerFactory.getLogger(GoodsService.class);

    private final GoodsMapper mapper;

    public GoodsService(GoodsMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
    @Transactional
    public boolean deductStock(long id) {
        Goods goods = getById(id);
        int stock = goods.getStock() - 1;
        goods.setStock(stock);
        boolean update = update(goods);
        return update;
    }


}
