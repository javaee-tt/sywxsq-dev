package com.sywxsq.happy;


import com.sywxsq.happy.utils.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("=====================================");
        System.out.println("system:"+System.currentTimeMillis());
        System.out.println("date:"+new Date().getTime());
        System.out.println("=====================================");
    }


}

