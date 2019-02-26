package com.sywxsq.happy.scheduled;

import com.google.common.primitives.Ints;
import com.sywxsq.happy.pojo.ElevenToFive;
import com.sywxsq.happy.service.ElevenToFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author luokangtao
 * @create 2019-02-25 18:01
 */
@Component
public class TaskQueue {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ElevenToFiveService elevenToFiveService;

//    @Scheduled(cron = "0 0/5 * * * ?")
    public void addElevenToFive(){
        //初始化对象
        ElevenToFive toFive = new ElevenToFive();
        toFive.setEndTime(new Date());//本轮结束时间
        toFive.setStartTime(new Date(new Date().getTime()+300000));//下一轮结束时间5分钟后

        //首先去redis里面查询 有没有设定的出奖顺序
        if(redisTemplate.boundValueOps("ElevenToFiveDao").get()==null){
            //自定义11位数字的数组
            int[] ints=new int[]{1,2,3,4,5,6,7,8,9,10,11};
            //初始化list集合
            ArrayList<Integer> list = new ArrayList<Integer>();
            //打乱数组并且转换成集合
            List<Integer> asList = Ints.asList(ints);//0
            Collections.shuffle(asList);//1  打乱
            Collections.shuffle(asList);//2  打乱
            Collections.shuffle(asList);//3  ...
            Collections.shuffle(asList);//4  ...
            Collections.shuffle(asList);//5  ...
            Collections.shuffle(asList);//6  ...
            Collections.shuffle(asList);//7  ...
            Collections.shuffle(asList);//8  ...
            System.out.println("随机后的数组:"+asList);//输出打乱后的数字
            List<Integer> subList = asList.subList(0, 5);//获取前5位数字
            String toString = subList.toString();//转成字符串
            System.out.println("取前五位数组:"+subList);//输出前5位数字
            toFive.setNumberResult(toString);
        }else {
            //查询redis里面的数组
            Object elevenToFive = redisTemplate.boundValueOps("ElevenToFiveDao").get();
            //object转换成数组
            int[]ints= (int[]) elevenToFive;
            //转成字符串类型
            String toString = Arrays.toString(ints);
            toFive.setNumberResult(toString);
            System.out.println("redis里面的数据:"+toString);
            //删除redis里面的出奖顺序
            redisTemplate.delete("ElevenToFiveDao");
        }

        //保存到数据库
        elevenToFiveService.addElevenToFive(toFive);
        System.out.println("当前时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
