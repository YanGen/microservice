package com.atguigu.springcloud.clientService.goodsService;

import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.clientService.base.BaseServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

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

}
