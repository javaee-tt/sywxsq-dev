package com.sywxsq.happy.exception;
import com.sywxsq.happy.pojo.SywxsqResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;

/**
 * 异常拦截器
 * @author luokangtao
 * @create 2019-02-19 10:40
 */
@ControllerAdvice //@ControllerAdvice是controller的一个辅助类，最常用的就是作为全局异常处理的切面类
public class SywxsqExceptionHandler implements Serializable {

    private Logger logger = LogManager.getLogger();

    //上传文件超出限制
    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class) //拦截到该异常信息
    public SywxsqResult MaxUploadSizeExceededException(Exception e){
        logger.info("异常信息(上传文件不能超过1MB):"+e.getMessage());//异常信息java.lang.IllegalStateException
        return  new SywxsqResult(false,"上传文件不能超过1MB");
    }

    //接受非法参数
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class) //拦截到该异常信息
    public SywxsqResult IllegalArgumentException(Exception e){
        logger.info("异常信息(异常表明向方法传递了一个不合法或不正确的参数):"+e.getMessage());//异常信息java.lang.IllegalArgumentException
        return  new SywxsqResult(false,"接收非法参数");
    }

    //类型不匹配
    @ResponseBody
    @ExceptionHandler(InputMismatchException.class) //拦截到该异常信息
    public SywxsqResult InputMismatchException(Exception e){
        logger.info("异常信息(接收的数据类型与实际输入的类型不匹配):"+e.getMessage());//异常信息java.lang.InputMismatchException
        return  new SywxsqResult(false,"接收的数据类型与实际输入的类型不匹配");
    }

    //算数运算异常,如除数为零
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class) //拦截到该异常信息
    public SywxsqResult ArithmeticException(Exception e){
        logger.info("异常信息(算数运算异常,如除数为零):"+e.getMessage());//异常信息java.lang.ArithmeticException
        return  new SywxsqResult(false,"算数运算异常,如除数为零");
    }

    //数组小于或者大于数组的长度
    @ResponseBody
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class) //拦截到该异常信息
    public SywxsqResult ArrayIndexOutOfBoundsException(Exception e){
        logger.info("异常信息(数组小于或者大于数组的长度):"+e.getMessage());//异常信息java.lang.ArrayIndexOutOfBoundsException
        return  new SywxsqResult(false,"数组小于或者大于数组的长度");
    }


    //空指针异常
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public SywxsqResult NullPointerException(Exception e){
        logger.info("异常信息(空指针异常，操作一个 null 对象的方法或属性时会抛出这个异常。):"+e.getMessage());//异常信息java.lang.NullPointerException
        return  new SywxsqResult(false,"空指针异常，操作一个 null 对象的方法或属性时会抛出这个异常。");
    }

    //内存异常异常
    @ResponseBody
    @ExceptionHandler(OutOfMemoryError.class)
    public SywxsqResult OutOfMemoryError(Exception e){
        logger.info("异常信息(内存异常异常，是指要分配的对象的内存超出了当前最大的堆内存，需要调整堆内存大小(-Xmx)以及优化程序。):"+e.getMessage());//异常信息java.lang.OutOfMemoryError
        return  new SywxsqResult(false,"内存异常异常，是指要分配的对象的内存超出了当前最大的堆内存，需要调整堆内存大小(-Xmx)以及优化程序。");
    }

    //io流异常
    @ResponseBody
    @ExceptionHandler(IOException.class)
    public SywxsqResult IOException(Exception e){
        logger.info("异常信息(读写磁盘文件、网络内容的时候经常会生的一种异常，这种异常是受检查异常，需要进行手工捕获。):"+e.getMessage());//异常信息java.lang.IOException
        return  new SywxsqResult(false,"读写磁盘文件、网络内容的时候经常会生的一种异常，这种异常是受检查异常，需要进行手工捕获。");
    }

    //文件找不到异常
    @ResponseBody
    @ExceptionHandler(FileNotFoundException.class)
    public SywxsqResult FileNotFoundException(Exception e){
        logger.info("异常信息(文件找不到异常，如果文件不存在就会抛出这种异常。其实是 IOException 的子类，同样是受检查异常，需要进行手工捕获。):"+e.getMessage());//异常信息java.lang.IOException
        return  new SywxsqResult(false,"文件找不到异常，如果文件不存在就会抛出这种异常。其实是 IOException 的子类，同样是受检查异常，需要进行手工捕获。");
    }

    //类找不到异常
    @ResponseBody
    @ExceptionHandler(ClassNotFoundException.class)
    public SywxsqResult ClassNotFoundException(Exception e){
        logger.info("异常信息(类找不到异常,这是在加载类的时候抛出来的，即在类路径下不能加载指定的类。):"+e.getMessage());//异常信息java.lang.IOException
        return  new SywxsqResult(false,"类找不到异常,这是在加载类的时候抛出来的，即在类路径下不能加载指定的类。");
    }

    //类转换异常
    @ResponseBody
    @ExceptionHandler(ClassCastException.class)
    public SywxsqResult ClassCastException(Exception e){
        logger.info("异常信息(类转换异常，将一个不是该类的实例转换成这个类就会抛出这个异常。):"+e.getMessage());//异常信息java.lang.IOException
        return  new SywxsqResult(false,"类转换异常，将一个不是该类的实例转换成这个类就会抛出这个异常。");
    }

    //没有这个方法异常
    @ResponseBody
    @ExceptionHandler(NoSuchMethodException.class)
    public SywxsqResult NoSuchMethodException(Exception e){
        logger.info("异常信息(没有这个方法异常，一般发生在反射调用方法的时候。):"+e.getMessage());//异常信息java.lang.IOException
        return  new SywxsqResult(false,"没有这个方法异常，一般发生在反射调用方法的时候。");
    }

    //索引越界异常
    @ResponseBody
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public SywxsqResult IndexOutOfBoundsException(Exception e){
        logger.info("异常信息(索引越界异常，当操作一个字符串或者数组的时候经常遇到的异常。):"+e.getMessage());//异常信息java.lang.IOException
        return  new SywxsqResult(false,"索引越界异常，当操作一个字符串或者数组的时候经常遇到的异常。");
    }
}
