package com.info.service.pc;

import com.github.tobato.fastdfs.domain.StorePath;
import com.info.common.sysenum.StateMsg;
import com.info.converter.ArticleConverter;
import com.info.converter.ImageConverter;
import com.info.dto.ArticleDTO;
import com.info.dto.pc.ImageDTO;
import com.info.entity.ArticleEntity;
import com.info.entity.ImageEntity;
import com.info.exception.SystemException;
import com.info.mapper.ArticleMapper;
import com.info.mapper.ImageMapper;
import com.info.util.FastClientUtil;
import com.info.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author : yue
 * @Date : 2020/7/12 / 18:02
 */
@Service
public class NewsService {

    private static final String FILE_TYPE=".txt";

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleConverter articleConverter;

    @Autowired
    private ImageConverter imageConverter;

    @Autowired
    private FastClientUtil client;

    @Value("${article-path}")
    private String articlePath;

    public List<ImageDTO> getRecentImg(int size){
        if(size<=0){
            size=3;
        }
        List<ImageEntity> images = imageMapper.queryRecentImage(size);
        return images.stream().map(item->{
            ImageDTO dto = new ImageDTO();
            dto.setId(item.getId());
            dto.setImgGroup(item.getImgGroup());
            dto.setPath(item.getPath());
            return dto;
        }).collect(Collectors.toList());
    }


    public String  uploadImage(MultipartFile file) throws Exception{
        StorePath storePath = client.upload(file);
        if(storePath==null){
            throw new SystemException(StateMsg.StateMsg_105);
        }
        ImageEntity image =new ImageEntity();
        image.setName(file.getOriginalFilename());
        image.setPath(storePath.getPath());
        image.setImgGroup(storePath.getGroup());
        try{
            int i = imageMapper.insertImage(image);
            return i>=0?StateMsg.StateMsg_200.getMsg():StateMsg.StateMsg_500.getMsg();
        }catch (Exception e){
            client.delete(storePath.getGroup(),storePath.getPath());
            throw e;
        }
    }


    public String saveArticle(ArticleDTO article) throws IOException{
        ArticleEntity entity = articleConverter.articleConve(article);
        entity.setFileName(RandomUtil.getRandomStr(8)+FILE_TYPE);
        try{
            File file =new File(articlePath+ entity.getFileName());
            //内存==>disk || outputStream
            FileOutputStream outputStream =new FileOutputStream(file);
            outputStream.write(article.getStream().getBytes("utf-8"));
            outputStream.close();
            //持久化到数据库
            int res = articleMapper.insertArticle(entity);
            return res>0?StateMsg.StateMsg_200.getMsg():StateMsg.StateMsg_500.getMsg();
        }catch (IOException e){
            //如果持久化错误，删除文件
            File file = new File(articlePath+entity.getFileName());
            file.delete();
            throw e;
        }
    }


    public void updateImgDate(String imageId){
        imageMapper.updateModifyTime(imageId);
    }


    public ArticleDTO getArticle(String id) throws Exception{
        ArticleEntity entity = articleMapper.selectArticle(id);
        if(entity==null){
            throw new SystemException(StateMsg.StateMsg_104);
        }
        ArticleDTO result= new ArticleDTO();
        result.setTitle(entity.getAuthor());
        result.setSummary(entity.getSummary());
        result.setAuthor(entity.getAuthor());
        //read file disk ==> memory InputStream
        if(entity.getFileName()==null || entity.getFileName().isEmpty()){
            throw new SystemException(StateMsg.StateMsg_103);
        }
        File file = new File(articlePath + entity.getFileName());
        if(!file.exists()){
            throw  new SystemException(StateMsg.StateMsg_106);
        }
        FileInputStream inputStream = new FileInputStream(file);
         int size = inputStream.available();
         byte[] buffer = new byte[size];
         inputStream.read(buffer);
         inputStream.close();
         result.setStream(new String(buffer,"utf-8"));
        return result;
    }


    public List<ArticleDTO> getArticleList(int size) throws SystemException{
        List<ArticleEntity> data = articleMapper.selectArticleList(0, size);
        if(data ==null || data.isEmpty()){
            throw new SystemException(StateMsg.StateMsg_104);
        }
        return data.stream().map(item->
            articleConverter.articleEntityConve(item))
                .collect(Collectors.toList());
    }
}
