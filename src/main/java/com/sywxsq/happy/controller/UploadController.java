package com.sywxsq.happy.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sywxsq.happy.constant.ServiceConstant;
import com.sywxsq.happy.pojo.SywxsqException;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



/**
 * 上传文件
 * @author luokangtao
 * @create 2019-02-15 11:35
 */
@RestController
@RequestMapping("/uploadController")
public class UploadController {


    @Value("${FILE_IMAGES_URL}")
    private String FILE_IMAGES_URL;


    /**
     * 上传文件到linux
     * @param file 源文件
     * @return
     */
    @RequestMapping("/uploadImages")
    public SywxsqResult uploadImages(MultipartFile file){

        //获取源文件名字  比如: xxx.jpg 获取到"."位置
        int lastIndexOf = file.getOriginalFilename().lastIndexOf(".")+1;

        if(lastIndexOf==0){
            //抛出异常
            throw new SywxsqException("文件格式有误"+file.getOriginalFilename());
        }
        //获取后缀名 过滤文件格式  切割后缀名
        String substring = file.getOriginalFilename().substring(lastIndexOf);

        //字符串比较的过程中忽略大小写 (根据阿里巴巴的规范定义常量格式)
        if(!substring.equalsIgnoreCase(ServiceConstant.IMG_FROMAT.JPG)||
                !substring.equalsIgnoreCase(ServiceConstant.IMG_FROMAT.PNG)||
                !substring.equalsIgnoreCase(ServiceConstant.IMG_FROMAT.GIF)){
            //抛出异常
            throw new SywxsqException("上传文件格式只支持jpg,png,gif");
        }
        //获取源文件名称
        String filename = file.getOriginalFilename();
        //自定义的工具类获取随机文件名
        String uuidName = UploadUtils.getUUIDName(filename);
        //他可以和文件服务器建立联系
        Client client = Client.create();
        //linux存放文件地址+文件名.后缀名
        String imgUrl = FILE_IMAGES_URL + uuidName;

        try {
            //使用client和文件服务器建立联系
            WebResource resource = client.resource(imgUrl);
            //上传文件
            resource.put(file.getBytes());
        } catch (IOException e) {
            throw new SywxsqException("网络超时,上传图片失败!");
        }
        //返回自定义的结果集
        return new SywxsqResult(true,imgUrl);
    }

    /**
     * 删除linux文件
     * @param str 文件地址
     * @return
     */
    public boolean delete(String str){
        //他可以和文件服务器建立联系
        Client client = Client.create();
        //使用client和文件服务器建立联系
        WebResource resource = client.resource(str);
        //删除文件
        resource.delete();
        //返回结果集
        return true;
    }


}
