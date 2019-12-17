package com.muhuan.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muhuan.springcloud.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/14
 * @Version 1.0.0
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
