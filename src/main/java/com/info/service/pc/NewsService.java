package com.info.service.pc;

import com.info.common.sysenum.StateMsg;
import com.info.entity.ImageEntity;
import com.info.exception.SystemException;
import com.info.mapper.ImageMapper;
import com.info.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author : yue
 * @Date : 2020/7/12 / 18:02
 */
@Service
public class NewsService {

    @Autowired
    private ImageMapper imageMapper;


    public String saveImage(String fileName,String path){
        ImageEntity entity = new ImageEntity();
        entity.setName(fileName);
        entity.setPath(path);
        int res = imageMapper.insertImage(entity);
        return  res>0? StateMsg.StateMsg_200.toString():StateMsg.StateMsg_500.toString();
    }

}
