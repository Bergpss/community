package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 标识，表示是配置文件
public class CommunityApplication {

	public static void main(String[] args) {
        // 底层启动的是Tomcat，自动创建了Spring容器
        // Spring容器自动扫描某些包下的某些bean，将这些bean装配到容器
		SpringApplication.run(CommunityApplication.class, args);
	}

}
