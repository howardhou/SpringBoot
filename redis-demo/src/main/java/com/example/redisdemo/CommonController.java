package com.example.redisdemo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private String redis_city_name = "redis-demo:city-name";

    @GetMapping("/cityname")
    public String getCityName(){

        stringRedisTemplate.opsForValue().set(redis_city_name, "shanghai");

        String cityName = stringRedisTemplate.opsForValue().get(redis_city_name);

        return cityName;
    }

}
