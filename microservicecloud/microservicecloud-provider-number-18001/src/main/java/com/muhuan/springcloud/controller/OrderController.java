package com.muhuan.springcloud.controller;

import com.muhuan.api.bean.ajax.ResponseResult;
import com.muhuan.api.util.ResultGeneratorUtil;
import com.muhuan.common.entity.Order;
import com.muhuan.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/7
 * @Version 1.0.0
 **/

@RestController
@RequestMapping("/order")
//@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")

public class OrderController  extends BaseController<Order>{
    @Autowired
    public OrderController(OrderService service) {
        super(service);
    }

    /**
     * 新增选号管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseResult add(@RequestParam("numberId")Integer numberId, @RequestParam("customer")String customer,
                              @RequestParam("contact")String contact, @RequestParam("payChannel")String payChannel,
                              @RequestParam("location")String location, @RequestParam("qq") String qq,
                              @RequestParam("wx") String wx
                              ) {
        long time = System.currentTimeMillis()/1000;

        Order order = new Order(numberId, "创建", "创建", payChannel, customer, contact, qq, wx, location);
       order.setInsertTime(time);
       order.setUpdateTime(time);
       service.add(order);
        return ResultGeneratorUtil.getResultSuccessWithData("成功");
    }
    /**
     * 新增选号管理
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public ResponseResult insert(Order order ) {
        long time = System.currentTimeMillis()/1000;
       order.setInsertTime(time);
       order.setUpdateTime(time);
       service.add(order);
        return ResultGeneratorUtil.getResultSuccessWithData("成功");
    }
}
