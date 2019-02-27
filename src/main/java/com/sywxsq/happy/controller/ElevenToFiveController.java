package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.PageResult;
import com.sywxsq.happy.pojo.SywxsqException;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.service.ElevenToFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 11选5
 * @author luokangtao
 * @create 2019-02-25 17:03
 */
@RestController
@RequestMapping("/ElevenToFiveController")
public class ElevenToFiveController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    ElevenToFiveService elevenToFiveService;

    private SywxsqResult sywxsqResult;

    /**
     * 设置自定义的值
     * @param values
     */
    @RequestMapping("/setElEleventToFiveValue")
    public SywxsqResult setElEleventToFive(String[] values){
        //判断数组是否有重复的值
        for(int i=0;i<values.length;i++){
            if(i<4){
                if(values[i].toString().equals(values[i+1].toString())){
                    throw new SywxsqException("<"+values[i]+">是重复的值,数值是唯一不可重复的,取值范围应从1到11");
                } } }
        String toString = Arrays.toString(values);

        redisTemplate.boundValueOps("ElevenToFiveDao").set(values);
        return new SywxsqResult(true,"您设置的开奖值是:"+toString);
    }


    /**
     * 分页查询11选5
     * @return
     */
    @RequestMapping("/findAllElevenToFive")
    public SywxsqResult findAllElevenToFive(Integer pageNumber, Integer pageSize){
        PageResult toFive = elevenToFiveService.findAllElevenToFive(pageNumber, pageSize);
        sywxsqResult =new SywxsqResult(true,"查询成功");
        sywxsqResult.setPageResult(toFive);
        return sywxsqResult;
    }

}
