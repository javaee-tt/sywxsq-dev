package com.sywxsq.happy.dao;

import com.sywxsq.happy.pojo.Classify;
import com.sywxsq.happy.pojo.ClassifyResult;
import org.apache.ibatis.annotations.Mapper;

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
}
