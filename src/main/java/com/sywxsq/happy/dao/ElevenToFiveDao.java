package com.sywxsq.happy.dao;

import com.sywxsq.happy.pojo.ElevenToFive;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 11选5
 * @author luokangtao
 * @create 2019-02-25 17:16
 */
@Mapper//@Mapper标记该类是一个mybatis的mapper接口,可以被springboot扫描到spring上下文中
public interface ElevenToFiveDao {


    /**
     * 新增11选5
     * @param toFive
     * @return
     */
    Integer addElevenToFive(ElevenToFive toFive);

    /**
     * 查询所有11选5
     * @return
     */
    List<ElevenToFive> findAllElevenToFive();

    /**
     * 查询下一次出奖时间
     * @return
     */
    ElevenToFive findNextStartTime();
}
