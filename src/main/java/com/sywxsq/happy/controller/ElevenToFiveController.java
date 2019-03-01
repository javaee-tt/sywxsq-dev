package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.ElevenToFive;
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
     * 设置自定义的开奖值
     * @param parseOne  第一位数
     * @param parsetwo  第二位数
     * @param parseThree    第三位数
     * @param parseFour     第四位数
     * @param parseFive     第五位数
     * @return
     */
    @RequestMapping("/setElEleventToFiveValue")
    public SywxsqResult setElEleventToFive(String parseOne,String parsetwo,String parseThree,String parseFour,String parseFive){
        //自定义数组
        String [] str=new String[]{parseOne,parsetwo,parseThree,parseFour,parseFive};
        //数组转字符串
        String toString = Arrays.toString(str);
        //保存到redis里面
        redisTemplate.boundValueOps("ElevenToFiveDao").set(toString);
        //返回设置的开奖信息
        return new SywxsqResult(true,"您设置的开奖值是:"+toString);
    }


    /**
     * 分页查询11选5
     * @return
     */
    @RequestMapping("/findAllElevenToFive")
    public SywxsqResult findAllElevenToFive(Integer pageNumber, Integer pageSize){
        //分页查询全部11选5数据
        PageResult toFive = elevenToFiveService.findAllElevenToFive(pageNumber, pageSize);
        //查询下一次出奖
        ElevenToFive eleventoFive =elevenToFiveService.findNextStartTime();
        //查询成功
        sywxsqResult =new SywxsqResult(true,"查询成功");
        //添加分页查询结果
        sywxsqResult.setPageResult(toFive);
        //查询下一次出奖日期
        sywxsqResult.setElevenToFive(eleventoFive);
        //返回结果集
        return sywxsqResult;
    }

}
