package com.atguigu.springcloud.clientService.base;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BaseServiceApi
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
public interface BaseServiceApi<T> {
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody T t);
    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.DELETE)
    public ResponseResult deleteById(@PathVariable(value = "id") int id);
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody T t);
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable(value = "id") int id);
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseResult getAll();
}
