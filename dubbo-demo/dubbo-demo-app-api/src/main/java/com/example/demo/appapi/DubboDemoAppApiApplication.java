package com.example.demo.appapi;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "dubbo-demo-api", autoRefreshed = true)
public class DubboDemoAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoAppApiApplication.class, args);
    }

}
