package com.sywxsq.happy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luokangtao
 * @create 2019-02-25 17:03
 */
@RestController
@RequestMapping("/ElevenToFiveController")
public class ElevenToFiveController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置自定义的值
     * @param values
     */
    @RequestMapping("/setElEleventToFiveValue")
    public void setElEleventToFive(String values){
        System.out.println(values);
        //redisTemplate.boundValueOps("ElevenToFiveDao").set(values);
    }
}
