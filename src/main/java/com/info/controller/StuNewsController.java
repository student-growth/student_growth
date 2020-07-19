package com.info.controller;

import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.pc.ImageDTO;
import com.info.service.pc.NewsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/19 / 5:22
 * 小程序端
 */
@RestController
@RequestMapping("/stu_news")
public class StuNewsController {


    @Autowired
    private NewsService newsService;

    @ApiOperation("获取轮播图图片列表")
    @RequestMapping(value = "/swiper",method = RequestMethod.GET)
    public ReturnValue<ImageDTO>  getCarouselList(@RequestParam("size") int size){
        ReturnValue<ImageDTO> result = new ReturnValue<>();
        try{
            List<ImageDTO> data = newsService.getRecentImg(size);
            result.setList(data);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSystemerrormsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
