package com.info.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.info.common.sysenum.StateMsg;
import com.info.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author : yue
 * @Date : 2020/7/17 / 0:48
 */
@Component
public class FastClientUtil {

    @Autowired
    private FastFileStorageClient storageClient;



    public StorePath upload(MultipartFile file) throws IOException,SystemException{
        String fileName = file.getOriginalFilename();
        if(fileName==null || fileName.isEmpty()){
            throw new SystemException(StateMsg.StateMsg_103);
        }
        return storageClient.uploadFile(file.getInputStream(),file.getSize(),
                FileUtil.getFileExtension(fileName),null);
    }

    public StorePath upload(File file) throws IOException,SystemException{
        String fileName = file.getName();
        FileInputStream stream = new FileInputStream(file);
        return storageClient.uploadFile(stream, file.length(),
                FileUtil.getFileExtension(fileName), null);
    }

    public byte[] download(String group,String path) throws Exception{
        return  storageClient.downloadFile(group, path, new DownloadByteArray());

    }




}
