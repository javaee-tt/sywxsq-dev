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
 * 11选5 定时任务
 * @author luokangtao
 * @create 2019-02-25 18:01
 */
@Component
public class TaskQueue {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ElevenToFiveService elevenToFiveService;

    /**
     * (cron = "0 0/5 * * * ?") 每5分钟刷新一次
     * (cron = "0 0/6 8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ?") 8点-23点 每5   分钟刷新一次
     */
//    @Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(cron = "0 0/5 8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ?")
    public void addElevenToFive(){
        //初始化对象
        ElevenToFive toFive = new ElevenToFive();
        toFive.setEndTime(new Date());//当前的结束时间
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
            List<Integer> subList = asList.subList(0, 5);//获取前5位数字
            String toString = subList.toString();//集合转成字符串
            toFive.setNumberResult(toString);//保存到数据库
        }else {
            //查询redis里面的数组
            Object elevenToFive = redisTemplate.boundValueOps("ElevenToFiveDao").get();
            //object转换成数组
            int[]ints= (int[]) elevenToFive;
            //转成字符串类型
            String toString = Arrays.toString(ints);
            toFive.setNumberResult(toString);
            //删除redis里面的出奖顺序
            redisTemplate.delete("ElevenToFiveDao");
        }
        //保存到数据库
        elevenToFiveService.addElevenToFive(toFive);
        System.out.println("当前更新11选5的时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
