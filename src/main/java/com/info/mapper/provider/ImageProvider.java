package com.info.mapper.provider;

import com.info.entity.ImageEntity;
import com.info.util.RandomUtil;
import javafx.scene.control.Tab;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yue
 * @since : 2020/7/12 / 17:44
 */
public class ImageProvider {

    private static String TABLE_NAME="image";
    private static String ID =TABLE_NAME+".id";
    private static String NAME =TABLE_NAME+".name";
    private static String GROUP =TABLE_NAME+".img_group";
    private static String PATH =TABLE_NAME+".path";

    public String saveImage(Map<String ,Object> params){
        ImageEntity entity = (ImageEntity) params.get("image");
        entity.setId(RandomUtil.getRandomNum(6));
        StringBuffer sql = new StringBuffer()
                .append("insert into ").append(TABLE_NAME)
                .append("(")
                .append(ID).append(",")
                .append(NAME).append(",")
                .append(GROUP).append(",")
                .append(PATH).append(") values (")
                .append("'").append(entity.getId()).append("'").append(",")
                .append("'").append(entity.getName()).append("'").append(",")
                .append("'").append(entity.getImgGroup()).append("'").append(",")
                .append("'").append(entity.getPath()).append("'")
                .append(")");
        new SQL();
        return sql.toString();
    }


    public String selectRecentImage(Map<String,Object> params){
        int size = (int)params.get("size");
        StringBuilder sql = new StringBuilder();
        sql.append("select ")
                .append(ID).append(",")
                .append(GROUP).append(",")
                .append(PATH)
                .append("\n from ").append(TABLE_NAME)
                .append("\n where is_delete=0")
                .append("\n order by modify_time desc")
                .append("\n limit 0,").append(size);

        return sql.toString();
    }
}
