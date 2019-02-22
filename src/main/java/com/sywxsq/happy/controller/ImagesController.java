package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.Images;
import com.sywxsq.happy.pojo.SywxsqException;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 图片
 * @author luokangtao
 * @create 2019-02-14 11:00
 */
@RestController  //是Controller+ResponseBody结合
@RequestMapping("/ImagesController")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    private SywxsqResult sywxsqResult =null;//结果集

    /**
     * 添加图片
     * @param images
     * @return
     */
    @RequestMapping("/addImages")
    public SywxsqResult addImages(@RequestBody Images images){

        if(images.getImgUrl()==null){
            throw new SywxsqException("请上传图片,图片不能为空!");
        }
        boolean b=imagesService.addImages(images);
        if(b){
            sywxsqResult = new SywxsqResult(true,"新增图片成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"新增图片失败");
        }
        return sywxsqResult;
    }

    /**
     * 查询所有的相片
     * @return
     */
    @RequestMapping("/findAllImages")
    public SywxsqResult findAllImages(){
        List<Images> images =imagesService.findAllImages();
        return new SywxsqResult(true,"查询成功",images);
    }

    /**
     * 删除图片
     * @param id  根据id删除数据库信息
     * @param imgUrl 根据地址删除linux里面的图片
     * @return
     */
    @RequestMapping("/deleteImages")
    public SywxsqResult deleteImages(Integer id,String imgUrl){
        if(id==null || id.equals("")){
            throw new SywxsqException("id值不能为空");
        }
        if(imgUrl==null||imgUrl.equals("")){
            throw  new SywxsqException("imgUrl不能为空");
        }
        boolean b=imagesService.deleteImages(id,imgUrl);
        if(b){
            sywxsqResult = new SywxsqResult(true,"删除图片成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"删除图片失败");
        }
        return sywxsqResult;
    }

}
