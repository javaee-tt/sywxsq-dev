package com.sywxsq.happy.dao;

import com.sywxsq.happy.pojo.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 同学/同事/朋友录
 * @author luokangtao
 * @create 2019-02-20 15:32
 */
@Mapper//@Mapper标记该类是一个mybatis的mapper接口,可以被springboot扫描到spring上下文中
public interface FriendDao {

    Integer addFriend(Friend friend);

    List<Friend> findAllFriend(String userID);

    Integer deleteFriend(@Param("userId") String userId, @Param("id") Integer id);

}
