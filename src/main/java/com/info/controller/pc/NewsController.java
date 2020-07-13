package com.info.controller.pc;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.pc.CarouselDTO;
import com.info.service.pc.NewsService;
import com.info.util.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author : yue
 *  2020/7/12 / 13:34
 * 首页新闻要讯接口
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Value("${uploadpath-root-dir}")
    private String uploadPath;

    @Autowired
    private NewsService newsService;

    @ApiOperation("上传图片")
    @RequestMapping("/upload")
    public ReturnData<String> uploadCarouselImage(@RequestParam("imageFile")MultipartFile img){
        ReturnData<String> result =new ReturnData<>();
        if(img.isEmpty()){
            result.setStateMsg(StateMsg.StateMsg_102);
            return result;
        }
        String fullFileName = RandomUtil.getRandomStr(6) + "_" +
                img.getOriginalFilename();
        File file  = new File(uploadPath+fullFileName);
        if(!file.getParentFile().exists()){
            if(!file.getParentFile().mkdirs()){
                result.setStateMsg(StateMsg.StateMsg_204);
            }
        }
        try{

            String data = newsService.saveImage(fullFileName, uploadPath);
            img.transferTo(file);
            result.setData(data);
        }catch (Exception e){
           result.setStateMsg(StateMsg.StateMsg_500);
           result.setSysError(e.getMessage());
           e.printStackTrace();
       }
       return result;
    }

    @ApiOperation("获取轮播图文件列表")
    @RequestMapping(value = "/carouselList",method = RequestMethod.GET)
    public ReturnValue<CarouselDTO>  getCarouselList(@RequestParam("size") int size){
        ReturnValue<CarouselDTO> result = new ReturnValue<>();
        if(size<=0){
            size=3;
        }
        try{
            //todo get carousel image list;
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSystemerrormsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }





}
