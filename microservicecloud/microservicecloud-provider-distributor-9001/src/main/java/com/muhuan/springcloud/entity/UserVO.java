package com.muhuan.springcloud.entity;

import com.atguigu.springcloud.entities.entity.BaseEntityAndId;
import lombok.Data;

/**
 * @ClassName UserVO
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/11/12
 * @Version 1.0.0
 **/
@Data
public class UserVO extends BaseEntityAndId<Long> {
    private String userAccount;
    private String userName;
    private String lastLoginTime;
    private String token;
}
