package com.muhuan.springcloud.controller;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import com.muhuan.springcloud.service.BaseService;
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

    protected final BaseService<T> service;

    public BaseController(BaseService<T> service) {
        this.service = service;
    }
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody T t){
        T add = service.add(t);
        return ResultGeneratorUtil.getResultSuccessWithData(add);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable int id){
        boolean b = service.deleteById(id);
        return b?ResultGeneratorUtil.getResultSuccessWithData("删除成功！"):ResultGeneratorUtil.getResultFail("删除失败！");
    }

    @PutMapping("/update")
    public ResponseResult update(@RequestBody T t){
        boolean update = service.update(t);
        return update?ResultGeneratorUtil.getResultSuccessWithData("修改成功！"):ResultGeneratorUtil.getResultFail("修改失败！");
    }

    @GetMapping("/get/{id}")
    public ResponseResult getById(@PathVariable int id){
        Object result = service.getById(id);
        return result != null ?ResultGeneratorUtil.getResultSuccessWithData(result):ResultGeneratorUtil.getResultFail("该记录不存在！");
    }
    @GetMapping("/getAll")
    public ResponseResult getAll(){
        List<T> all = service.getAll();
        if (all != null && all.size() > 0)
            return ResultGeneratorUtil.getResultSuccessWithCount(all,all.size());
        return ResultGeneratorUtil.getResultFail("暂无数据！");
    }
}
