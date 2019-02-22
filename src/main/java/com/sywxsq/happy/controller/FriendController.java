package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.*;
import com.sywxsq.happy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 同学/同事/朋友录
 * @author luokangtao
 * @create 2019-02-20 15:33
 */
@RestController
@RequestMapping("/FriendController")
public class FriendController {

    @Autowired
    private FriendService friendService;

    private SywxsqResult sywxsqResult =null;//结果集

    /**
     * 新增同学/同事/朋友录
     * @param friend
     * @return
     */
    @RequestMapping("/addFriend")
    public SywxsqResult addFriend(@RequestBody Friend friend){
        if(friend.getFriendName()==null){
            throw new SywxsqException("姓名不能为空!");
        }
        if(friend.getSex()==null){
            throw new SywxsqException("性别不能为空!");
        }
        boolean b=friendService.addFriend(friend);
        if(b){
            sywxsqResult = new SywxsqResult(true,"新增friend成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"新增friend失败");
        }
        return sywxsqResult;
    }

    /**
     * 查询当前用户的朋友录
     * @param pageNumber 当前页
     * @param pageSize 当前页显示多少条数据
     * @return
     */
    @RequestMapping("/findAllFriend")
    public SywxsqResult findAllFriend(Integer pageNumber,Integer pageSize){
        PageResult friend = friendService.findAllFriend(pageNumber, pageSize);
        sywxsqResult = new SywxsqResult(true, "查询成功");
        sywxsqResult.setPageResult(friend);
        return sywxsqResult;
    }

}
