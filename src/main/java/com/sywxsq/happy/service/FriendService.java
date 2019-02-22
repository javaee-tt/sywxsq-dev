package com.sywxsq.happy.service;

import com.sywxsq.happy.dao.FriendDao;
import com.sywxsq.happy.pojo.Friend;
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
     * 查询当前用户的所有朋友录
     * @return
     */
    public List<Friend> findAllImages() {
        String userID="测试人";
        List<Friend> friendList = friendDao.findAllImages(userID);
        return friendList;
    }
}
