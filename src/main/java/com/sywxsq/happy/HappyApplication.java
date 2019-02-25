package com.sywxsq.happy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling //开启定时任务
@SpringBootApplication
@EnableTransactionManagement  //开始事务
@MapperScan("com.sywxsq.happy.dao")
public class HappyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyApplication.class, args);
    }

}

