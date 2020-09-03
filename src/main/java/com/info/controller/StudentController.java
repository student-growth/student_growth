package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.*;
import com.info.entity.Student;
import com.info.formbean.CommentFormBean;
import com.info.formbean.PageBean;
import com.info.formbean.ProcessFormBean;
import com.info.formbean.StudentFormBean;
import com.info.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.util.List;
import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:19
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService stuService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnData<StudentInfoDto> login(@RequestBody StudentFormBean info) {
        ReturnData<StudentInfoDto> result = new ReturnData<>();
        if (null == info) {
            result.setStateMsg(StateMsg.StateMsg_101);
            return result;
        }
        try {
            StudentInfoDto student = stuService.getStudentInfo(info.getId(), info.getPassword());
            result.setData(student);

        } catch (Exception e) {
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    public ReturnData<String> modifyPassword(@RequestBody StudentFormBean info) {
        ReturnData<String> result = new ReturnData<>();
        if (info.getId() == null || info.getPassword() == null ||
                info.getNewPassword() == null) {
            result.setStateMsg(StateMsg.StateMsg_101);
            return result;
        }
        try {
            String data = stuService.updatePassword(info.getId(), info.getPassword(), info.getNewPassword());
            result.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
        }
        return result;
    }


    @ApiOperation("分页获取学生列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ReturnValue<Student> getStudentList(@RequestBody PageBean page) {
        ReturnValue<Student> res = new ReturnValue<>();
        try {
            res = stuService.getStudentList(page);
        } catch (Exception e) {
            res.setStateMsg(StateMsg.StateMsg_500);
            res.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }


    @ApiOperation("查询学生成绩")
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public ReturnData<Map<String, List<ScoreDTO>>> getScoreById(@RequestParam("id") String id) {
        ReturnData<Map<String, List<ScoreDTO>>> result = new ReturnData<>();
        //check param
        if (null == id || id.isEmpty()) {
            result.setStateMsg(StateMsg.StateMsg_101);
            return result;
        }
        try {
            Map<String, List<ScoreDTO>> data = stuService.groupQueryScore(id);
            result.setData(data);
            result.setStateMsg(StateMsg.StateMsg_200);
        } catch (Exception e) {
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ReturnData<FileDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                          @RequestParam("id") String id) {
        ReturnData<FileDTO> result = new ReturnData<>();
        try {
            FileDTO dto = stuService.uploadFile(file, id);
            result.setData(dto);
        } catch (Exception e) {
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //提交申请
    @RequestMapping(value = "/submitApply",method = RequestMethod.POST)
    public ReturnData<String> submitApply(@RequestParam("file") MultipartFile file,
                                          @RequestParam("formData") String formData,
                                          @RequestParam("userId") String userId,
                                          @RequestParam("applyId") String applyId,
                                          @RequestParam("formTemp") String formTemp){
        ReturnData<String> result = new ReturnData<>();
        try{
            ApplyDTO applyDTO = new ApplyDTO();
            applyDTO.setApplyId(applyId);
            applyDTO.setStudentId(userId);
            applyDTO.setFormData(formData);
            applyDTO.setFormTemp(formTemp);
            String data = stuService.submitApply(applyDTO, file);
            result.setData(data);

        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //@RequestBody FileDTO fileInfo,
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response) {
        //todo 添加前端传来的参数  /usr/include/fastdfs /usr/include/fastcommon
        try {
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + "a.jpg");
            byte[] stream = stuService.downloadFile("group1", "M00/00/00/rBGh4F8RP2mAFI9cAAUPnIaxxmg087.jpg");
            response.getOutputStream().write(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //学生评价
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ReturnData<String> comment(@RequestBody CommentFormBean info) {
        ReturnData<String> result = new ReturnData<>();
        try {
            String data = stuService.assess(info.getId(), info.getOther(),
                    info.getPsychology(), info.getMoral());
            result.setData(data);
        } catch (Exception e) {
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    @RequestMapping(value = "/getProcessList",method = RequestMethod.POST)
    public ReturnValue<ApplyDTO> getProcessList(@RequestBody ProcessFormBean bean){
        ReturnValue<ApplyDTO> result =new ReturnValue<>();
        try{
            List<ApplyDTO> data = stuService.getProcessList(bean);
            result.setList(data);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }
    //查询四六级成绩
    @RequestMapping(value = "/getCETScore",method = RequestMethod.GET)
    public ReturnData<Map<String,List<CETScoreDTO>>> getCETScore(@RequestParam("id") String id){
        ReturnData<Map<String,List<CETScoreDTO>>> result =new  ReturnData<>();
        try{
            Map<String, List<CETScoreDTO>> allCETScore = stuService.getAllCETScore(id);
            result.setData(allCETScore);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }

}
