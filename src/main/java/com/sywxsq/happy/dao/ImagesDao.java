package com.sywxsq.happy.dao;

import com.sywxsq.happy.pojo.Images;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author luokangtao
 * @create 2019-02-14 11:05
 */
@Mapper //@Mapper标记该类是一个mybatis的mapper接口,可以被springboot扫描到spring上下文中
public interface ImagesDao {

    /**
     * 查询图片
     * @return
     */
   List<Images> findAllImages();

    /**
     * 新增图片
     * @param images
     * @return
     */
    Integer addImages(Images images);

    /**
     *判断当前sort是否存在
     * @param images
     * @return
     */
    List<Images> exists(Images images);

    /**
     *如果存在当前sort+1
     * @param images
     * @return
     */
    Integer addSortOne(Images images);

    /**
     * 根据主键id删除图片
     * @param id
     * @return
     */
    Integer deleteImages(Integer id);
}
