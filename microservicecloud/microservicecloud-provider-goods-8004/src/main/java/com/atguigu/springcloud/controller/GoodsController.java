package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.cache.MybatisRedisCache;
import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.goods.Goods;
import com.atguigu.springcloud.service.GoodsService;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods> {

    private final GoodsService service;
    public GoodsController(GoodsService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/deductStock/{id}")
    public ResponseResult deductStock(@PathVariable long id){

        return service.deductStock(id);
    }

    @Autowired
	private DiscoveryClient client;
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery()
    {
        List<String> list = client.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-GOODS");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }
}
