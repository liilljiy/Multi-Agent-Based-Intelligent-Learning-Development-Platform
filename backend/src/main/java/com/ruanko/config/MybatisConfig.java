package com.ruanko.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author DYS
 * @version 1.0
 * @Description
 * @DateTime 2024/6/5 22:56
 */
@Configuration
@MapperScan("com.ruanko.mapper") // 指定Mapper接口所在的包路径
public class MybatisConfig {
    // ***
}
