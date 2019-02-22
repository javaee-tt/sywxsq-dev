package com.sywxsq.happy.service;

import com.sywxsq.happy.dao.ClassifyDao;
import com.sywxsq.happy.pojo.Classify;
import com.sywxsq.happy.pojo.ClassifyResult;
import com.sywxsq.happy.pojo.SywxsqException;
import org.omg.CORBA.SystemException;
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
        //首先判断当前用户的分类名字是否有重复
        String classifyName = classifyDao.selectClassifyName(classify);
        if(!classifyName.equals("") && classifyName!=null){
            throw new SywxsqException("当前分类名字["+classifyName+"]已存在,请更改");
        }
        //新增分类
        Integer integer = classifyDao.addClassify(classify);
        if (integer == -1) {
            return false;
        }
        return true;
    }

    /**
     * 根据用户id和分类id删除分类
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteClassify(Integer id) {
        String userId="测试人";
        //首先查询当前分类下有没有已经关联数据,如果有不能删除
        Integer friendId = classifyDao.selectClassifyFriend(userId,id);
        if(friendId!=null||friendId.equals("")){
            throw new SywxsqException("当前分类已有关联,不能删除");
        }
        Integer integer = classifyDao.deleteClassify(userId,id);
        if(integer<1){
            return false;
        }
        return true;
    }
}
