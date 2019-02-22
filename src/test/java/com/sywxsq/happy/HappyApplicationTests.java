package com.sywxsq.happy;

import com.sywxsq.happy.controller.UploadController;
import com.sywxsq.happy.utils.UploadUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("这是一个输出开始:");

        System.out.println(UploadUtils.getUUIDName("abc.jpg"));

        System.out.println("这是一个输出结束!");

        UploadController uploadController = new UploadController();

        String str = "http://120.78.188.168:8080/img/AE89D64CA3AC449CB64C8ED58C9D35BB.jpg";

        boolean delete = uploadController.delete(str);//调用删除功能

        System.out.println(delete);

    }

}

