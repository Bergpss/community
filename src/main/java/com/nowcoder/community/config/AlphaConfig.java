package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 04, 10:16
 */

@Configuration // 配置类
public class AlphaConfig {
    // 42:31

    // 装配SimpleDateFormat
    // bean的名字就是方法名：simpleDateFormat
    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
