package com.sywxsq.happy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 11选5
 * @author luokangtao
 * @create 2019-02-25 16:56
 */
public class ElevenToFive implements Serializable {

    private long id; //主键id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date endTime;//这一轮结束时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date startTime;//下一轮开始时间

    private String numberResult;//开奖号码

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getNumberResult() {
        return numberResult;
    }

    public void setNumberResult(String numberResult) {
        this.numberResult = numberResult;
    }

    @Override
    public String toString() {
        return "ElevenToFive{" +
                "id=" + id +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", numberResult='" + numberResult + '\'' +
                '}';
    }
}
