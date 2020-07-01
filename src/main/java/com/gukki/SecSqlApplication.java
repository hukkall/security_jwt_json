package com.gukki;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gukki.mapper")
public class SecSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecSqlApplication.class, args);
    }

}
