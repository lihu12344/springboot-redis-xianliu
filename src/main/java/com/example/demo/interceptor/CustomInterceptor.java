package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Objects;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key=request.getRequestURI();
        if(redisTemplate.boundValueOps(key).get()==null){
            redisTemplate.boundValueOps(key).set("1", Duration.ofMinutes(1L));
            return true;
        }else {
            redisTemplate.opsForValue().increment(key);
        }

        if(Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(key)))>5){
            response.sendRedirect("/hello2");
        }

        return true;
    }
}
