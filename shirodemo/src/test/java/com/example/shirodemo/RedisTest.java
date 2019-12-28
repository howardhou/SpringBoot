package com.example.shirodemo;

import com.example.shirodemo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

        // 设置 30秒失效，30秒之内查询有结果，30秒之后返回为null
        stringRedisTemplate.opsForValue().set("name","tom",30, TimeUnit.SECONDS);
        System.out.println("***************" + stringRedisTemplate.opsForValue().get("name"));

        stringRedisTemplate.opsForValue().set("key","hello world");
        // 用 value 参数覆写(overwrite)给定 key 所储存的字符串值
        stringRedisTemplate.opsForValue().set("key","redis", 6);

        System.out.println("***************" + stringRedisTemplate.opsForValue().get("key"));

        System.out.println(stringRedisTemplate.opsForValue().setIfAbsent("multi1","multi32"));
    }

    @Test
    public void testObject() throws Exception {
        User user=new User("houdongdong", "123456", "aa123456", false);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neo", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
