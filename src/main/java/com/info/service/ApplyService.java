package com.info.service;

import com.info.common.sysenum.ApplyEnum;
import com.info.common.sysenum.StateMsg;
import com.info.entity.ApplyEntity;
import com.info.mapper.ApplyMapper;
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


    public String cancelApply(String id) throws Exception {

        int res = applyMapper.updateStateById(id, ApplyEnum.CANCEL.name());
        return res == 1 ? StateMsg.StateMsg_200.getMsg() :
                StateMsg.StateMsg_500.getMsg();
    }


    public String modifyApply(String id, String formData,
                              MultipartFile file) throws Exception {
        ApplyEntity entity = new ApplyEntity();
        entity.setId(id);
        entity.setFormData(formData);
        entity.setApplyState(ApplyEnum.APPLYING.name());
        if (file == null) {
            //todo image的处理
            entity.setImage("test------>todo ");
        }
        int res = applyMapper.updateApply(entity);
        return res == 1 ? StateMsg.StateMsg_200.getMsg()
                : StateMsg.StateMsg_500.getMsg();
    }


}
