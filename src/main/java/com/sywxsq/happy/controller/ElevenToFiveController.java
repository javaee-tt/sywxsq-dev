package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.PageResult;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.service.ElevenToFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void setElEleventToFive(String values){
        System.out.println(values);
        //redisTemplate.boundValueOps("ElevenToFiveDao").set(values);
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
