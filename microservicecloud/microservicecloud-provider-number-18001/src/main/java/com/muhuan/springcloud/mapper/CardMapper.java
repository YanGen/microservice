package com.muhuan.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muhuan.common.entity.Card;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CardMapper
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/4
 * @Version 1.0.0
 **/
@Mapper
public interface CardMapper extends BaseMapper<Card> {

}
