package com.sywxsq.happy.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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

        int lastIndexOf = file.getName().lastIndexOf(".")+1;
        if(lastIndexOf==0){
            throw new SywxsqException("文件格式有误"+file.getName());
        }
        String substring = file.getName().substring(lastIndexOf);//切割后缀名
        if(substring.equalsIgnoreCase("jpg")||
                substring.equalsIgnoreCase("png")||
                substring.equalsIgnoreCase("gif")){

        }

        String filename = file.getOriginalFilename();//获取源文件名称
        String uuidName = UploadUtils.getUUIDName(filename);//自定义的工具类获取随机文件名
        Client client = Client.create();//他可以和文件服务器建立联系
        String imgUrl = FILE_IMAGES_URL + uuidName;//linux存放文件地址+文件名.后缀名

        try {
            WebResource resource = client.resource(imgUrl);//使用client和文件服务器建立联系
            resource.put(file.getBytes());//上传文件
        } catch (IOException e) {
            throw new SywxsqException("网络超时,上传图片失败!");
        }

        return new SywxsqResult(true,imgUrl);//返回自定义的结果集
    }

    /**
     * 删除linux文件
     * @param str 文件地址
     * @return
     */
    public boolean delete(String str){
        Client client = Client.create();//他可以和文件服务器建立联系
        WebResource resource = client.resource(str);//使用client和文件服务器建立联系
        resource.delete();//删除文件
        return true;
    }


}
