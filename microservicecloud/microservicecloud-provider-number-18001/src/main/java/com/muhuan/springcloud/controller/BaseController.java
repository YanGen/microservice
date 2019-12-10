package com.muhuan.springcloud.controller;

import com.muhuan.api.bean.ajax.ResponseResult;
import com.muhuan.api.bean.entity.BaseEntityAndDelete;
import com.muhuan.api.bean.file.FileUploadStandard;
import com.muhuan.api.util.IdGenetatorUtil;
import com.muhuan.api.util.ResultGeneratorUtil;
import com.muhuan.springcloud.service.BaseService;
import com.muhuan.springcloud.util.FieldAnnotationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/18
 * @Version 1.0.0
 **/

@RequiredArgsConstructor
public class BaseController<T extends BaseEntityAndDelete>{

    protected final BaseService<T> service;

    @Value("${file.limit}")
    protected long fileLimit = 0L;
    @Value("${file.netUrl}")
    protected String netPath = "";
    protected final String localBasePath = System.getProperty("user.dir");


    @PostMapping("/insert")
    public ResponseResult insert(T t){
        t= FieldAnnotationUtil.create(t);
        T add = service.add(t);
        return ResultGeneratorUtil.getResultSuccessWithData(add);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable int id){
        boolean b = service.deleteById(id);
        return b? ResultGeneratorUtil.getResultSuccessWithData("删除成功！"): ResultGeneratorUtil.getResultFail("删除失败！");
    }

    @PutMapping("/update")
    public ResponseResult update(T t){
        t=FieldAnnotationUtil.create(t);
        boolean update = service.update(t);
        return update? ResultGeneratorUtil.getResultSuccessWithData("修改成功！"): ResultGeneratorUtil.getResultFail("修改失败！");
    }

    @GetMapping("/get/{id}")
    public ResponseResult getById(@PathVariable long id){
        Object result = service.getById(id);
        return result != null ? ResultGeneratorUtil.getResultSuccessWithData(result): ResultGeneratorUtil.getResultFail("该记录不存在！");
    }

    @GetMapping("/getAll")
    public ResponseResult getAll(){
        List<T> all = service.getAll();
        if (all != null && all.size() > 0)
            return ResultGeneratorUtil.getResultSuccessWithCount(all,all.size());
        return ResultGeneratorUtil.getResultFail("暂无数据！");
    }

    @PostMapping("/file/upload")
    public ResponseResult upload(MultipartFile file, String fileFolder){
        FileUploadStandard uploadStandard = new FileUploadStandard();
        //获取文件后缀名（.png）
        String originalFilename = file.getOriginalFilename();
        int begin = originalFilename.lastIndexOf(".");
        int last = originalFilename.length();
        String fileSuffix = originalFilename.substring(begin, last);
        String fileName = "IMG_" + IdGenetatorUtil.generateUuid() + fileSuffix;
        uploadStandard.setFileSize(file.getSize());//设置文件大小
        uploadStandard.setFile(file);//设置文件夹域
        uploadStandard.setFileName(fileName);//设置文件夹名（xxx.png）
        uploadStandard.setLimited(fileLimit);//设置文件上传大小限制（单位字节）
        if (fileFolder != null){
            uploadStandard.setLocalPath(localBasePath + "/file/" + fileFolder);//设置本地保存路径
        }else {
            uploadStandard.setLocalPath(localBasePath + "/file");
        }
        uploadStandard.setNetUrl(netPath + "/" + fileName);//设置网络路径
        //实现保存
        FileUploadStandard fileUploadStandard = service.uploadFile(uploadStandard);
        return ResultGeneratorUtil.getResultFail(fileUploadStandard);
    }
}
