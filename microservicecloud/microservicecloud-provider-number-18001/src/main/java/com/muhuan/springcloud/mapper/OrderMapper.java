package com.muhuan.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muhuan.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrderMapper
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/7
 * @Version 1.0.0
 **/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
