package com.sywxsq.happy.pojo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.Serializable;

/**
 * 异常拦截器
 * @author luokangtao
 * @create 2019-02-19 10:40
 */
@ControllerAdvice //@ControllerAdvice是controller的一个辅助类，最常用的就是作为全局异常处理的切面类
public class SywxsqExceptionHandler implements Serializable {

    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class) //拦截到该异常信息
    public SywxsqResult MaxUploadSizeExceededException(Exception e){
        System.out.println("异常信息:"+e.getMessage());//异常信息java.lang.IllegalStateException
        return  new SywxsqResult(false,"上传文件不能超过1MB");

    }
}
