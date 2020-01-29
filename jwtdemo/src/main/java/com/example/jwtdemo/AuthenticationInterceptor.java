package com.example.jwtdemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
//    @Autowired
//    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;  // true表示继续流程
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();

        //检查是否有需要用户权限的注解
        if (method.isAnnotationPresent(Authorization.class)) {
            Authorization auth = method.getAnnotation(Authorization.class);
            if (auth.required()) {
                // 从 http 请求头中取出 token
                String token = request.getHeader("Authorization");
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }

                // 获取 token 中的 user id , user name
                try {
                    String userName = JWT.decode(token).getAudience().get(0);
                    Long userId = JWT.decode(token).getClaim("userId").asLong();

//                    User user = userService.findUserById(userId);
//                    if (user == null) {
//                        throw new RuntimeException("用户不存在，请重新登录");
//                    }
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401 decode error");
                }

                JwtUtils.checkToken(token);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
