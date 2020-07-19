package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ImageEntity;
import com.info.mapper.provider.ImageProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/12 / 17:28
 */
public interface ImageMapper extends BaseMapper<ImageEntity> {


    /**
     * 持久化图片
     */
    @InsertProvider(type = com.info.mapper.provider.ImageProvider.class,
            method = "saveImage")
    int insertImage(@Param("image") ImageEntity entity);




    @Results(id = "imgMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "img_group",property = "imgGroup"),
            @Result(column = "path",property = "path")
    })
    @SelectProvider(type = ImageProvider.class, method = "selectRecentImage")
    List<ImageEntity> queryRecentImage(@Param("size") int size);


    @Update("update image set modify_time = sysdate() where name = #{name}")
    int updateModifyTime(@Param("name") String name);

}
