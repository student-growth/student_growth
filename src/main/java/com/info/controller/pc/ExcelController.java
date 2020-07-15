package com.info.controller.pc;

import com.info.common.ReturnData;
import com.info.common.sysenum.StateMsg;
import com.info.dto.StudentInfoDto;
import com.info.service.pc.ExcelService;
import com.info.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author : yue
 *  2020/7/15 / 16:31
 *  Excel 导入导出操作
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelUtil util;

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/importStu",method = RequestMethod.POST)
    private ReturnData<String> importStudentInfo(@RequestParam("file") MultipartFile excel){
        ReturnData<String> result =new ReturnData<>();
        try{
            List<StudentInfoDto> list = util.importExcel(excel, StudentInfoDto.class);
            String data = excelService.saveStudents(list);
            result.setData(data);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
