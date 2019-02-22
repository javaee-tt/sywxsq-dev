package com.sywxsq.happy.service;

import com.sywxsq.happy.dao.ClassifyDao;
import com.sywxsq.happy.pojo.Classify;
import com.sywxsq.happy.pojo.ClassifyResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 分类
 * @author luokangtao
 * @create 2019-02-21 11:13
 */
@Service
public class ClassifyService {

    @Resource
    private  ClassifyDao classifyDao;

    /**
     * 查询当前用户下的分类
     * @return
     */
    public List<ClassifyResult> selectUserClassifyList() {
        String userId="测试人"; //伪造的
        List<ClassifyResult> classifyList=classifyDao.selectUserClassifyList(userId);
        return classifyList;
    }

    /**
     * 新增分类
     * @param classify
     * @return
     */
    @Transactional
    public boolean addClassify(Classify classify) {
        classify.setCreateTime(new Date());//创建时间
        classify.setCreateBy("测试人");//创建人
        classify.setUserId("测试人");//用户名
       Integer integer = classifyDao.addClassify(classify);
        if (integer == -1) {
            return false;
        }
        return true;
    }
}
