package com.info.service.pc;

import com.info.common.sysenum.StateMsg;
import com.info.converter.ImageConverter;
import com.info.dto.pc.ImageDTO;
import com.info.entity.ImageEntity;
import com.info.exception.SystemException;
import com.info.mapper.ImageMapper;
import com.info.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.jsoup.select.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author : yue
 * @Date : 2020/7/12 / 18:02
 */
@Service
public class NewsService {

    @Autowired
    private ImageMapper imageMapper;


    @Autowired
    private ImageConverter imageConverter;


    public String saveImage(String fileName,String path){
        ImageEntity entity = new ImageEntity();
        entity.setName(fileName);
        entity.setPath(path);
        int res = imageMapper.insertImage(entity);
        return  res>0? StateMsg.StateMsg_200.getMsg():StateMsg.StateMsg_500.getMsg();
    }

    public List<ImageDTO> getRecentImg(int size){
        if(size<=0){
            size=3;
        }
        List<ImageEntity> images = imageMapper.queryRecentImage(size);
        return images.stream().map(item->
             imageConverter.imageConver(item)
        ).collect(Collectors.toList());
    }


    public void updateImgDate(String name){
        imageMapper.updateModifyTime(name);
    }

}
