package com.joe.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.joe.code.mapper")
public class BusinessCodeGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessCodeGenerateApplication.class, args);
    }

}
