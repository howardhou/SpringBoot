package com.example.druiddemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 扫描指定包下的dao，这样就不用每个dao interface上面写@Mapper了
@MapperScan(basePackages = "com.example.druiddemo.dao")
public class MyBatisConfig {
}
