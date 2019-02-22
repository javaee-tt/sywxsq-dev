package com.sywxsq.happy.pojo;

/**
 * 分类返回值
 * @author luokangtao
 * @create 2019-02-21 17:01
 */
public class ClassifyResult {

    private long Id; //主键id

    private String classifyName;//分类名称

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

}
