package com.sywxsq.happy.pojo;

/**
 * @author luokangtao
 * @create 2019-02-18 14:47
 */

/**
 * 自定义异常类 
 */
public class SywxsqException extends  RuntimeException {

    private String  message;

    //提供无参数的构造方法
    public SywxsqException(){};

    //提供一个有参数的构造方法，可自动生成
    public SywxsqException(String message){this.message = message;}

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
