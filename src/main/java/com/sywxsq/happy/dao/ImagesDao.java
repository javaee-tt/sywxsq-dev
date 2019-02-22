package com.sywxsq.happy.dao;

import com.sywxsq.happy.pojo.Images;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author luokangtao
 * @create 2019-02-14 11:05
 */
@Mapper //@Mapper标记该类是一个mybatis的mapper接口,可以被springboot扫描到spring上下文中
public interface ImagesDao {


   List<Images> findAllImages();

    Integer addImages(Images images);

    List<Images> exists(Images images);

    Integer addSortOne(Images images);

    Integer deleteImages(Integer id);
}
