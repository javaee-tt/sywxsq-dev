package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.SywxsqException;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.utils.EmailUtils;
import com.sywxsq.happy.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author luokangtao
 * @create 2019-02-26 16:17
 */
@RestController
@RequestMapping("/SendEmailController")
public class SendEmailController {

    //redis模版
    @Autowired
    private RedisTemplate redisTemplate;

    //随机验证码字符串
    private final String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 发送邮件
     *
     * @param recipientsEmail 收件人
     * @return
     */
    @RequestMapping("/sendEmail")
    public SywxsqResult sendEmail(String recipientsEmail) {

        //验证邮箱地址的格式正确不正确
        if (ValidatorUtils.isEmail(recipientsEmail) == false) {
            throw new SywxsqException("请输入正确的邮箱地址");
        }


        //如果当前邮箱地址已存在value值
        if (redisTemplate.boundValueOps("YZMcode" + recipientsEmail).get()!=null ){
            throw new SywxsqException("您点击的频率太快了,快要受不了..5分钟后再点..");
        }

        //创建随机验证码
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            stringBuilder.append(ch);
        }
        //创建一个 YZMcode+邮箱地址的  redis的key值  value=随机字符串
        BoundValueOperations yzMcode = redisTemplate.boundValueOps("YZMcode" + recipientsEmail);
        //设置vulue,5分钟,TimeUnit.DAYS:天  TimeUnit.HOURS:小时  TimeUnit.MINUTES:分钟...
        yzMcode.set(stringBuilder, 5, TimeUnit.MINUTES);

        EmailUtils.SendEmail("注册验证码",
                "您的验证码是:" + stringBuilder + ",请您马上激活,有效期为5分钟",
                recipientsEmail);
        return new SywxsqResult(true, "已发送到" + recipientsEmail + "请接收");

    }

    /**
     * 校验验证码
     *
     * @param recipientsEmail 邮箱地址
     * @param YZMcode         验证码
     * @return
     */
    @RequestMapping("/checkEmailCode")
    public SywxsqResult checkEmailCode(String recipientsEmail, String YZMcode) {

        //验证邮箱地址的格式正确不正确
        if (ValidatorUtils.isEmail(recipientsEmail) == false) {
            throw new SywxsqException("请输入正确的邮箱地址");
        }

        //从redis里面查询 如果查找到为空或者null值
        if (redisTemplate.boundValueOps("YZMcode" + recipientsEmail).get() == null) {
            throw new SywxsqException("您的验证码已超过有效期...");
        }

        //获取到验证码
        String string = redisTemplate.boundValueOps("YZMcode" + recipientsEmail).get().toString();

        //判断验证码是否相同
        boolean b = string.equalsIgnoreCase(YZMcode);
        if (!b) {
            return new SywxsqResult(false, "您的验证码输入有误!");
        }
        //删除redis里面的缓存
        redisTemplate.delete("YZMcode" + recipientsEmail);

        //返回成功信息
        return new SywxsqResult(true, "恭喜你验证成功,但是没奖励...");
    }
}
