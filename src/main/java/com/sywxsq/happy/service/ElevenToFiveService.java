package com.sywxsq.happy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywxsq.happy.dao.ElevenToFiveDao;
import com.sywxsq.happy.pojo.ElevenToFive;
import com.sywxsq.happy.pojo.PageResult;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 11选5
 * @author luokangtao
 * @create 2019-02-25 17:18
 */
@Service
public class ElevenToFiveService {

    @Resource
    private  ElevenToFiveDao elevenToFiveDao;

    /**
     * 新增11选5
     * @param toFive
     * @return
     */
    public boolean addElevenToFive(ElevenToFive toFive) {
        //新增11选5记录
        Integer integer=elevenToFiveDao.addElevenToFive(toFive);
        if (integer == -1) {
            return false;
        }
        return true;
    }

    /**
     * 查询全部11选5
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult findAllElevenToFive(Integer pageNumber, Integer pageSize) {
        //分页工具
        PageHelper.startPage(pageNumber,pageSize);
        //分页查询
        Page<ElevenToFive> page = (Page<ElevenToFive>) elevenToFiveDao.findAllElevenToFive();
        //获取总记录数
        long total = page.getTotal();
        //获取当前页结果集
        List<ElevenToFive> result = page.getResult();
        //返回结果集
        return new PageResult(total,result);
    }

    /**
     * 查询下一次出奖的时间
     * @return
     */
    public ElevenToFive findNextStartTime() {
       ElevenToFive elevenToFive = elevenToFiveDao.findNextStartTime();
       //如果下一轮开始时间小于当前时间
       if(elevenToFive.getStartTime().getTime()<System.currentTimeMillis()){
           elevenToFive.setStartTime(null);
       }
        return elevenToFive;
    }
}
