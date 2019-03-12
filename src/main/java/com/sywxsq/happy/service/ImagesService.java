package com.sywxsq.happy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywxsq.happy.dao.ImagesDao;
import com.sywxsq.happy.pojo.Images;
import com.sywxsq.happy.pojo.PageResult;
import com.sywxsq.happy.pojo.SywxsqException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 图片业务层
 * @author luokangtao
 * @create 2019-02-14 11:03
 */
@Service
public class ImagesService {

    @Resource
    private ImagesDao imagesDao;

    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 分页查询全部相片
     * @return
     */
    public PageResult findAllImages(Integer pageNumber, Integer pageSize) {
        //分页插件
        PageHelper.startPage(pageNumber,pageSize);
        //分页查询
        Page<Images> page = (Page<Images>) imagesDao.findAllImages();
        //获取总记录数
        long total = page.getTotal();
        //获取当前页结果集
        List<Images> result = page.getResult();
        return new PageResult(total,result);
    }

    /**
     * 新增相片
     * @param images
     * @return
     */
    @Transactional
    public boolean addImages(Images images) {

        images.setCreateTime(new Date());//创建时间
        images.setCreateBy("测试人");//创建人
        images.setUserId("测试人");//用户id
        if(images.getImgTitle()==null){ //如果图片标题等于null
            images.setImgTitle(simpleDateFormat2.format(new Date()));//把当前时间yyyy-MM-dd设置为图片标题
        }
        if(images.getSort()!=null){//如果sort不等于null,则判断他是否重复
            List<Images> list = imagesDao.exists(images);//查询sort是否已经存在
            if(list.size()>0){
              Integer integer = imagesDao.addSortOne(images);//等于和大于sort的全部+1
              if(integer==-1){
                  throw new SywxsqException("大于或者等于sort的自增+1失败");
              }
            }
        }
        Integer integer = imagesDao.addImages(images);
        if (integer == -1) {
            return false;
        }
        return true;
    }

    /**
     * 删除图片
     * @param id
     * @param imgUrl
     * @return
     */
    @Transactional
    public boolean deleteImages(Integer id, String imgUrl) {
        Integer integer = imagesDao.deleteImages(id);
        if (integer<=0){
            throw new SywxsqException("删除图片失败");
        }else {
            try {
                //根据图片地址删除linux里面图片
                Class aClass = Class.forName("com.sywxsq.happy.controller.UploadController");//反射获取类
                Method delete = aClass.getMethod("delete", String.class);//获取方法
                Object o = aClass.newInstance();//实例化
                delete.invoke(o,imgUrl);//执行这个方法
            } catch (Exception e) {
                throw  new SywxsqException("删除Linux图片失败");
            }
        }
        return true;
    }
}
