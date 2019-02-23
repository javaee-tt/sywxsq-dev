package com.sywxsq.happy;

import com.sywxsq.happy.controller.UploadController;
import com.sywxsq.happy.utils.UploadUtils;
import com.sywxsq.happy.utils.ValidatorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyApplicationTests {

    @Test
    public void contextLoads() {
        String s1="545我爱你";
        String s2="王尼玛&";
        boolean b1 = ValidatorUtils.IsChinese(s1);
        boolean b2 = ValidatorUtils.IsChinese(s2);
        System.out.println("验证全部汉字s1:"+b1);
        System.out.println("验证全部汉字s2:"+b2);
        String s3="13713676163";
        String s4="15813707163";
        boolean b3 = ValidatorUtils.IsHandset(s3);
        boolean b4 = ValidatorUtils.IsHandset(s4);
        System.out.println("手机号码b3:"+b3);
        System.out.println("手机号码b4:"+b4);

    }

}

