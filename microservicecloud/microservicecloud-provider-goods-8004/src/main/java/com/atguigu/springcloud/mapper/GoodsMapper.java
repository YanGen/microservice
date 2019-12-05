package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entities.goods.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName GoodsMapper
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/5
 * @Version 1.0.0
 **/
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
