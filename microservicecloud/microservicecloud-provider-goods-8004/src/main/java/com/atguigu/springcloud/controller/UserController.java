package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.entity.User;
import com.atguigu.springcloud.entity.UserVO;
import com.atguigu.springcloud.service.UserService;
import com.atguigu.springcloud.util.EntityUtil;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import com.atguigu.springcloud.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/14
 * @Version 1.0.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController<User> {
    private final UserService userService;
    @Autowired
    public UserController(UserService service) {
        super(service);
        userService = service;
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody Map<String,Object> map){
        String account = (String) map.get("userAccount");
        String password = (String) map.get("userPassword");
        if (account==null || password==null || "".equals(account.trim()) | "".equals(password.trim()))
            throw new RuntimeException("非法访问，请填写正确的账号和密码！");
        System.out.println("登录：" + account + ":" + password);
        User login = userService.login(account, password);
        if (login == null) throw new RuntimeException("账号或密码错误，登录失败！");
        UserVO userVO = null;
        try {
            userVO = EntityUtil.toEntity(login, UserVO.class);
            userVO.setToken(TokenUtil.getToken(login));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录转换异常!");
        }

        return ResultGeneratorUtil.getResultSuccessWithData(userVO);
    }

    @PostMapping("/register")
    public ResponseResult insert(@RequestBody User user){
        userService.add(user);
        return ResultGeneratorUtil.getResultSuccessWithData("注册成功");
    }


    @Override
    public ResponseResult update(User user) {
        return super.update(user);
    }
    @Override
    public ResponseResult getById(int id) {
        return super.getById(id);
    }

}
