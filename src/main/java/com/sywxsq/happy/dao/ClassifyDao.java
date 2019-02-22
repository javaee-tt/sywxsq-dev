package com.sywxsq.happy.dao;

import com.sywxsq.happy.pojo.Classify;
import com.sywxsq.happy.pojo.ClassifyResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类
 * @author luokangtao
 * @create 2019-02-21 11:13
 */
@Mapper//@Mapper标记该类是一个mybatis的mapper接口,可以被springboot扫描到spring上下文中
public interface ClassifyDao {

    /**
     * 查询当前用户下的分类
     * @param userId
     * @return
     */
    List<ClassifyResult> selectUserClassifyList(String userId);

    /**
     * 新增分类
     * @param classify
     * @return
     */
    Integer addClassify(Classify classify);

    /**
     * 当存用户当前新增分类名字是否存在
     * @param classify
     * @return
     */
    String selectClassifyName(Classify classify);

    /**
     * 根据用户id和分类id删除分类
     * @param userId
     * @param id
     * @return
     */
    Integer deleteClassify(String userId, Integer id);

    /**
     *
     * @param id
     * @return
     */
    Integer selectClassifyFriend(@Param("userId") String userId, @Param("id") Integer id);
}
