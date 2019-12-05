package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;
import com.atguigu.springcloud.service.GoodsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName GoodsControllerConsumer
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/3
 * @Version 1.0.0
 **/
@RestController
@RequestMapping("/goods")
public class GoodsControllerConsumer {
    @Autowired
    private GoodsClientService service;


    @RequestMapping(value = "/getAll")
    public Object getAll()
    {
        return this.service.getAll();
    }
}
