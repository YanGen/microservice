package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.clientController.base.BaseController;
import com.atguigu.springcloud.clientService.base.BaseServiceApi;
import com.atguigu.springcloud.clientService.goodsService.GoodsClientService;
import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName GoodsControllerConsumer
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/3
 * @Version 1.0.0
 **/
@RestController
@RequestMapping("/goods")
public class GoodsControllerConsumer extends BaseController<Goods> {

    private GoodsClientService service;

    @PostMapping("/deductStock/{id}")
    public ResponseResult deductStock(@PathVariable long id){
        return service.deductStock(id);
    }

    @Autowired
    public GoodsControllerConsumer(GoodsClientService service) {
        super(service);
        this.service  = service;
    }
}
