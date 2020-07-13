package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ImageEntity;
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



    @Select("select id,name,path,create_time from image where is_delete=0 order by modify_time desc " +
            "limit 0,#{size}")
    List<ImageEntity> queryRecentImage(@Param("size") int size);


    @Update("update image set modify_time = sysdate() where name = #{name}")
    int updateModifyTime(@Param("name") String name);

}
