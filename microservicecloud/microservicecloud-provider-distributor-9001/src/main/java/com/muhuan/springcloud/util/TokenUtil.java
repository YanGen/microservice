package com.muhuan.springcloud.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.muhuan.springcloud.entity.User;

/**
 * @ClassName TokenUtil
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/15
 * @Version 1.0.0
 **/
public class TokenUtil {
    /**
     * 生成token 的方法
     * @param user
     * @return
     */
    public static String getToken(User user) throws Exception{
        String token= JWT.create().withAudience(user.getUserAccount())
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}
