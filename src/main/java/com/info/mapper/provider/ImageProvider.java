package com.info.mapper.provider;

import com.info.entity.ImageEntity;
import com.info.util.RandomUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/12 / 17:44
 */
public class ImageProvider {

    public String saveImage(Map<String ,Object> params){
        ImageEntity entity = (ImageEntity) params.get("image");
        entity.setId(RandomUtil.getRandomNum(6));
        StringBuffer sql = new StringBuffer()
                .append("insert into image(id,name,path) values")
                .append("(")
                .append("'").append(entity.getId()).append("'").append(",")
                .append("'").append(entity.getName()).append("'").append(",")
                .append("'").append(entity.getPath()).append("'")
                .append(")");

        return sql.toString();
    }
}
