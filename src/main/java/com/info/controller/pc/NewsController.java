package com.info.controller.pc;

import com.info.common.ReturnData;
import com.info.common.sysenum.StateMsg;
import com.info.dto.ArticleDTO;
import com.info.service.pc.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : yue
 *  2020/7/12 / 13:34
 * 首页新闻要讯接口
 */
@RestController
@RequestMapping("/news")
public class NewsController {

//    @Value("${uploadpath-root-dir}")
//    private String filePath;


//    @Value("${article-path}")
//    private String articlePath;

    @Autowired
    private NewsService newsService;


    @RequestMapping("/upload")
    public ReturnData<String> uploadFile(@RequestParam("file") MultipartFile file) {
        ReturnData<String> result = new ReturnData<>();
        try {
            String data = newsService.uploadImage(file);
            result.setData(data);
        } catch (Exception e) {
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 文件下载
     * @param response void
     * @param name fileName
     */
//    @GetMapping("/download/{name}")
//    public void getImage(HttpServletResponse response,
//                         @PathVariable("name") String name) {
//        try{
//            response.setContentType("image/jpeg;charset=utf-8");
//            response.setHeader( "Content-Disposition",String.format("attachment;filename=\"%s",name));
//            ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(Files.readAllBytes(Paths.get(filePath).resolve(name)));
//            outputStream.flush();
//            outputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


//    @RequestMapping(value = "/edit_article",method = RequestMethod.POST)
//    public ReturnData<String> editArticle(@RequestBody ArticleDTO article){
//        ReturnData<String> result = new ReturnData<>();
//        try{
//            String data = newsService.saveArticle(article);
//            result.setData(data);
//        }catch (Exception e){
//            result.setStateMsg(StateMsg.StateMsg_500);
//            result.setSysError(e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }

}
