package com.sywxsq.happy.service;

import com.sywxsq.happy.dao.ElevenToFiveDao;
import com.sywxsq.happy.pojo.ElevenToFive;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
