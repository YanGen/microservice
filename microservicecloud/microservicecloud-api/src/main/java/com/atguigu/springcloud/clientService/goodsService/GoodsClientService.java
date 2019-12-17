package com.atguigu.springcloud.clientService.goodsService;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.clientService.base.BaseServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName GoodsClientService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/3
 * @Version 1.0.0
 **/
@FeignClient(value = "MICROSERVICECLOUD-GOODS-PROVIDER-8004",path = "/goods",fallbackFactory=GoodsClientServiceFallbackFactory.class)
//@FeignClient(value = "MICROSERVICECLOUD-GOODS-PROVIDER-8004",fallbackFactory=GoodsClientServiceFallbackFactory.class)
public interface GoodsClientService extends BaseServiceApi<Goods> {
    @RequestMapping(value = "/deductStock/{id}",method = RequestMethod.POST)
    public ResponseResult deductStock(@PathVariable long id);
}
