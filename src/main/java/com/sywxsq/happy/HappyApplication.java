package com.sywxsq.happy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.sywxsq.happy.dao")
public class HappyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyApplication.class, args);
    }

}

