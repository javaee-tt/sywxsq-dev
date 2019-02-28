package com.sywxsq.happy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywxsq.happy.dao.ElevenToFiveDao;
import com.sywxsq.happy.pojo.ElevenToFive;
import com.sywxsq.happy.pojo.Images;
import com.sywxsq.happy.pojo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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


    public boolean addElevenToFive(ElevenToFive toFive) {

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
        PageHelper.startPage(pageNumber,pageSize);
        Page<ElevenToFive> page = (Page<ElevenToFive>) elevenToFiveDao.findAllElevenToFive();
        long total = page.getTotal();
        List<ElevenToFive> result = page.getResult();
        return new PageResult(total,result);
    }

    /**
     * 查询下一次出奖的时间
     * @return
     */
    public ElevenToFive findNextStartTime() {
       ElevenToFive elevenToFive = elevenToFiveDao.findNextStartTime();
       //如果下一轮开始时间小于当前时间
       if(elevenToFive.getStartTime().getTime()<new Date().getTime()){
           elevenToFive.setStartTime(null);
       }
        return elevenToFive;
    }
}
