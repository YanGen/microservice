package com.muhuan.springcloud.handler;

import com.muhuan.api.bean.ajax.ResponseResult;
import com.muhuan.api.util.ResultGeneratorUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.lang.model.type.UnknownTypeException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.MissingResourceException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 *         chao <chen.yc@uniteddata.com>
 * @Date 2019/10/22
 * @Version 1.0.1
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class,Exception.class})
    public ResponseResult globalExceptionHandler(Exception e){
        return ResultGeneratorUtil.getResultFail("服务器出错啦！错误信息为:" + e.getMessage());
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseResult exceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("请求资源不存在！");
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseResult arithmeticExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现算数异常啦！");
    }

    @ExceptionHandler(MissingResourceException.class)
    public ResponseResult missingResourceExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现资源丢失异常啦！");
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseResult classNotFoundExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现类无法找到异常啦！");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseResult nullPointerExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现空指针异常啦！");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult illegalArgumentExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现非法声明异常啦！");
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseResult arrayIndexOutOfBoundsExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现数组越界异常啦");
    }

    @ExceptionHandler(UnknownTypeException.class)
    public ResponseResult unknownTypeExceptionHandler(Exception e) {
        return ResultGeneratorUtil.getResultFail("出现未知类型异常啦，请检查数据参数类型是否正确！");
    }

    @ExceptionHandler(EOFException.class)
    public ResponseResult EOFExceptionHandler(Exception e){
        return ResultGeneratorUtil.getResultFail("出现文件流未关闭异常！");
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseResult fileNotFoundException(Exception e){
        return ResultGeneratorUtil.getResultFail("出现文件未找到异常！");
    }
}
