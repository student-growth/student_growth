package com.info.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.info.common.sysenum.ApplyEnum;
import com.info.common.sysenum.StateMsg;
import com.info.entity.ApplyEntity;
import com.info.mapper.ApplyMapper;
import com.info.util.FastClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : yue
 * @Date : 2020/9/4 / 19:41
 */
@Service
public class ApplyService {

    @Autowired
    private ApplyMapper applyMapper;


    @Autowired
    private FastClientUtil fastClient;


    public String cancelApply(String id) throws Exception {

        int res = applyMapper.updateStateById(id, ApplyEnum.CANCEL.name());
        return res == 1 ? StateMsg.StateMsg_200.getMsg() :
                StateMsg.StateMsg_500.getMsg();
    }


    public String modifyApply(String id, String formData,
                              MultipartFile file) throws Exception {

        ApplyEntity entity = applyMapper.selectById(id);
        //更新数据
        StorePath upload = fastClient.upload(file);
        try {

            //上传新的照片
            entity.setId(id);
            entity.setFormData(formData);
            entity.setApplyState(ApplyEnum.APPLYING.name());
            //存储照片
            entity.setImage(upload.getGroup() + "/" + upload.getPath());

            String imagePath = entity.getImage();
            int index=imagePath.indexOf("/");
            String group = imagePath.substring(0, index);
            String path = imagePath.substring(index + 1);
            int res = applyMapper.updateApply(entity);
            //删除撤销图片
            try{
                fastClient.delete(group, path);
            }catch (FdfsServerException ignored){}

            return res == 1 ? StateMsg.StateMsg_200.getMsg()
                    : StateMsg.StateMsg_500.getMsg();

        } catch (Exception e) {
            //如果上传出现错误，删除上传的照片
            fastClient.delete(upload.getGroup(), upload.getPath());
            throw e;
        }
    }


}
