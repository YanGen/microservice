package com.atguigu.springcloud.clientController.base;

import com.atguigu.springcloud.clientService.base.BaseServiceApi;
import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.atguigu.springcloud.service.DeptClientService;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/18
 * @Version 1.0.0
 **/
public class BaseController<T extends BaseEntityAndDelete>{
    private BaseServiceApi service;
    public BaseController(BaseServiceApi<T> service) {
        this.service = service;
    }
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody T t){
        return service.insert(t);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable int id){
        return service.deleteById(id);
    }

    @PutMapping("/update")
    public ResponseResult update(@RequestBody T t){
        return service.update(t);
    }

    @GetMapping("/get/{id}")
    public ResponseResult getById(@PathVariable int id){
        return service.getById(id);
    }
    @GetMapping("/getAll")
    public ResponseResult getAll(){
        return service.getAll();
    }
}
