package com.sywxsq.happy.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义的返回结果集
 * @author luokangtao
 * @create 2019-02-15 11:47
 */
public class SywxsqResult implements Serializable {

    private Boolean success;//是否成功

    private String message;//返回信息

    private List<Images> images;//图片列表

    private List<ClassifyResult> classifyList;//分类

    private List<Friend> friendList;//分类

    private PageResult pageResult;//分页查询结果

    private ElevenToFive elevenToFive;//11选5

    public ElevenToFive getElevenToFive() {
        return elevenToFive;
    }

    public void setElevenToFive(ElevenToFive elevenToFive) {
        this.elevenToFive = elevenToFive;
    }

    public SywxsqResult(Boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public SywxsqResult(Boolean success, String message, List<Images> images) {
        this.success = success;
        this.message = message;
        this.images = images;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<ClassifyResult> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<ClassifyResult> classifyList) {
        this.classifyList = classifyList;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }
}
