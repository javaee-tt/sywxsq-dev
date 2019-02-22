package com.sywxsq.happy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 图片类
 * @author luokangtao
 * @create 2019-02-14 11:05
 */
//实体类需要实现序列化
public class Images implements Serializable {

    private long id; //主键id

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date createTime;//创建时间

    private String createBy;//创建人

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date updateTime;//修改时间

    private String updateBy;//修改人

    private String userId;//用户id

    private String imgUrl;//图片地址

    private String imgTitle;//图片标题

    private String imgDescription;//图片描述

    private Integer sort;//图片排序

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgDescription() {
        return imgDescription;
    }

    public void setImgDescription(String imgDescription) {
        this.imgDescription = imgDescription;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", userId='" + userId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgTitle='" + imgTitle + '\'' +
                ", imgDescription='" + imgDescription + '\'' +
                ", sort=" + sort +
                '}';
    }
}
