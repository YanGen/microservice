package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName GoodsClientService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/3
 * @Version 1.0.0
 **/
@FeignClient(value = "MICROSERVICECLOUD-GOODS8004",fallbackFactory=GoodsClientServiceFallbackFactory.class)
public interface GoodsClientService {

    @RequestMapping(value = "/goods/getAll",method = RequestMethod.GET)
    public Object getAll();
}
