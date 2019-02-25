package com.sywxsq.happy.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 同学/同事/朋友录
 * @author luokangtao
 * @create 2019-02-20 15:20
 */
public class Friend implements Serializable {

    @Excel(name="id",orderNum ="0")
    private long id; //主键id

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date createTime;//创建时间

    private String createBy;//创建人

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date updateTime;//修改时间

    private String updateBy;//修改人

    @Excel(name="姓名",orderNum = "2")
    private String friendName;//姓名

    private String userId;//用户id

    @Excel(name="分类名称",orderNum = "3")
    private String classifyId;//分类id

    @Excel(name="性别",orderNum = "4")
    private String sex;//性别

    @Excel(name="关系",orderNum = "5")
    private String relation;//关系

    @Excel(name="手机号码",orderNum = "6",width = 20)
    private String phone;//手机号码

    @Excel(name="微信",orderNum = "7",width = 20)
    private String wechat;//微信

    @Excel(name="QQ",orderNum = "8",width = 20)
    private String qq;//QQ

    @Excel(name="地址",orderNum = "9",width = 40)
    private String site;//地址

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

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", friendName='" + friendName + '\'' +
                ", userId='" + userId + '\'' +
                ", classifyId='" + classifyId + '\'' +
                ", sex='" + sex + '\'' +
                ", relation='" + relation + '\'' +
                ", phone='" + phone + '\'' +
                ", wechat='" + wechat + '\'' +
                ", qq=" + qq +
                ", site='" + site + '\'' +
                '}';
    }
}
