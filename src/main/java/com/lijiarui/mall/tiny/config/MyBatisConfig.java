package com.lijiarui.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by lijiarui on 2019/4/8.
 */
@Configuration
@MapperScan("com.lijiarui.mall.tiny.mbg.mapper")
public class MyBatisConfig {
}
