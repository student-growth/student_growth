package com.info.controller.pc;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.pc.ImageDTO;
import com.info.service.pc.NewsService;
import com.info.util.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author : yue
 *  2020/7/12 / 13:34
 * 首页新闻要讯接口
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Value("${uploadpath-root-dir}")
    private String filePath;

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
        String fullFileName = img.getOriginalFilename();
        File file  = new File(filePath+fullFileName);
        if(!file.getParentFile().exists()){
            if(!file.getParentFile().mkdirs()){
                result.setStateMsg(StateMsg.StateMsg_204);
            }
        }
        //如果文件重复则删除重复的文件
        try{

            if(Files.exists(Paths.get(filePath).resolve(fullFileName))){
                File existFile = new java.io.File(filePath+fullFileName);
                existFile.delete();
                newsService.updateImgDate(fullFileName);
            }else{
                newsService.saveImage(fullFileName, filePath);
            }
            img.transferTo(file);
        }catch (Exception e){
           result.setStateMsg(StateMsg.StateMsg_500);
           result.setSysError(e.getMessage());
           e.printStackTrace();
       }
       return result;
    }


    @ApiOperation("文件下载")
    @GetMapping("/download/{name}")
    public void getImage(HttpServletResponse response,
                         @PathVariable("name") String name) {
        try{
            response.setContentType("image/jpeg;charset=utf-8");
            response.setHeader( "Content-Disposition",String.format("attachment;filename=\"%s",name));
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(Files.readAllBytes(Paths.get(filePath).resolve(name)));
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation("访问图片")
    @GetMapping("/img/{name}")
    public ResponseEntity<Object> getImage(@PathVariable("name") String name){
        try{
            File file = new File(filePath+name);
            InputStreamResource source = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add ( "Content-Disposition",String.format("attachment;filename=\"%s",name));
            headers.add ( "Cache-Control","no-cache,no-store,must-revalidate" );
            headers.add ( "Pragma","no-cache" );
            headers.add ( "Expires","0" );

            return  ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType ( "application/txt" ))
                    .body(source);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @ApiOperation("获取轮播图图片列表")
    @RequestMapping(value = "/imgList",method = RequestMethod.GET)
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
