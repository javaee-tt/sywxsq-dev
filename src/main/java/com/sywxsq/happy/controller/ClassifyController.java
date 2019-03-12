package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.Classify;
import com.sywxsq.happy.pojo.ClassifyResult;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luokangtao
 * @create 2019-02-21 11:15
 */
@RestController
@RequestMapping("/ClassifyController")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    //结果集
    private SywxsqResult sywxsqResult =null;

    /**
     * 查询用户的所有分类
     * @return
     */
    @RequestMapping("/selectUserClassifyList")
    public SywxsqResult selectUserClassifyList(){
        List<ClassifyResult> classifyList = classifyService.selectUserClassifyList();
        sywxsqResult =new SywxsqResult(true,"查询成功");
        sywxsqResult.setClassifyList(classifyList);
        return  sywxsqResult;
    }

    /**
     * 添加分类
     * @param classify
     * @return
     */
    @RequestMapping("/addClassify")
    public SywxsqResult addClassify(@RequestBody Classify classify){
        boolean b = classifyService.addClassify(classify);
        if(b){
            sywxsqResult = new SywxsqResult(true,"新增分类成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"新增分类失败");
        }
        return sywxsqResult;
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping("/deleteClassify")
    public SywxsqResult deleteClassify(Integer id){
        boolean b = classifyService.deleteClassify(id);
        if(b){
            sywxsqResult = new SywxsqResult(true,"删除分类成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"删除分类失败");
        }
        return sywxsqResult;
    }

}
