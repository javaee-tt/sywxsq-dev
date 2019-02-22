package com.sywxsq.happy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywxsq.happy.dao.FriendDao;
import com.sywxsq.happy.pojo.Friend;
import com.sywxsq.happy.pojo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 同学/同事/朋友录
 * @author luokangtao
 * @create 2019-02-20 15:35
 */
@Service
public class FriendService {

    @Resource
    private FriendDao friendDao;

    /**
     * 新增Friend
     * @param friend
     * @return
     */
    @Transactional
    public boolean addFriend(Friend friend) {
        friend.setCreateTime(new Date());//创建时间
        friend.setCreateBy("测试人");//创建人
        friend.setUserId("测试人");//用户id
        Integer integer = friendDao.addFriend(friend);
        if (integer == -1) {
            return false;
        }
        return true;
    }

    /**
     * 分页查询当前用户的所有朋友录
     * @return
     * @param pageNumber
     * @param pageSize
     */
    public PageResult findAllFriend(Integer pageNumber, Integer pageSize) {
        //伪造用户id查询当前用户的所有记录
        String userID="测试人";
        //调用分页查询插件
        PageHelper.startPage(pageNumber,pageSize);
        //page<e>可以获取总记录数,和当前记录数
        Page<Friend> page = (Page<Friend>) friendDao.findAllFriend(userID);
        //获取总页数
        long total = page.getTotal();
        //获取当前的结果集
        List<Friend> result = page.getResult();
        //返回结果集
        return new PageResult(total,result);
    }
}
