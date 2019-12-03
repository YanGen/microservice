package com.atguigu.springcloud.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.atguigu.springcloud.annotation.NeedToken;
import com.atguigu.springcloud.annotation.PassToken;
import com.atguigu.springcloud.entity.User;
import com.atguigu.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/15
 * @Version 1.0.0
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws UnsupportedEncodingException {
        String token = httpServletRequest.getHeader("sign");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                System.out.println("跳过认证");
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(NeedToken.class)) {
            NeedToken userLoginToken = method.getAnnotation(NeedToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userAccount;
                try {
                    System.out.println("获取 token 中的 user id");
                    userAccount = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("token：401");
                }
                User manager = userService.getByAccout(userAccount);
                if (manager == null) {
                    System.out.println("用户不存在，请重新登录");
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                System.out.print("验证token");
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(manager.getUserPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token:401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
