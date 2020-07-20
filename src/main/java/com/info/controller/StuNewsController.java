package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.ArticleDTO;
import com.info.dto.pc.ImageDTO;
import com.info.service.pc.NewsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/19 / 5:22
 * 小程序端 --新闻界面
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

    @RequestMapping(value = "/news_list",method = RequestMethod.GET)
    public ReturnValue<ArticleDTO> getNewsList(@RequestParam("size") int size){
        ReturnValue<ArticleDTO> result =new ReturnValue<>();
        try{
            List<ArticleDTO> list = newsService.getArticleList(size);
            result.setList(list);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSystemerrormsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取具体的文章信息
     * @param id article id
     * @return void
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ReturnData<ArticleDTO> getNews(@PathVariable("id") String id){
        ReturnData<ArticleDTO> result = new ReturnData<>();
        try{
            ArticleDTO article = newsService.getArticle(id);
            result.setData(article);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
