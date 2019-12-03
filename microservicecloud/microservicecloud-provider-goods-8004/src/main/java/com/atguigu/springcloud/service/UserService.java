package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.User;
import com.atguigu.springcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/14
 * @Version 1.0.0
 **/
@Service
public class UserService extends BaseService<User> {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper mapper) {
        super(mapper);
        userMapper = mapper;
    }

    @Override
    public User add(User user){
        String password = DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes());
        user.setUserPassword(password);
        //检查账号是否存在
        HashMap<String, Object> query = new HashMap<>(8);
        query.put("user_account", user.getUserAccount());
        List<User> users = super.getByMap(query);
        //注册
        if (users == null || users.size() == 0) return super.add(user);
        else throw new RuntimeException("注册失败，该账号已存在！");
    }

    public User login(String account,String password){
        //查询
        Map<String ,Object> params = new HashMap<>(8);
        params.put("user_account",account);
        List<User> users = super.getByMap(params);
        if (users == null || users.size() == 0) throw new RuntimeException("账号不存在！");
        User user = users.get(0);
        boolean hasLogin = user.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()));
        if (!hasLogin) throw new RuntimeException("密码错误，登录失败！");
        //更新登陆时间
        User updateUser = user.cloneUser();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Timestamp.valueOf(LocalDateTime.now()).getTime()));
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        updateUser.setUpdateTime(time);
        int i = userMapper.updateById(updateUser);
        if (i <= 0) throw new RuntimeException("登录失败！");
        return users.size()>0?user:null;
    }

    public User getByAccout(String userAccount) {
        Map<String ,Object> params = new HashMap<>();
        params.put("user_account", userAccount);
        List<User> users = mapper.selectByMap(params);
        return users.size() == 1?users.get(0) :null;
    }
}
