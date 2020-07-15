package com.info.controller.pc;

import com.info.common.ReturnData;
import com.info.common.sysenum.StateMsg;
import com.info.dto.StudentInfoDto;
import com.info.formbean.pc.ExportStuFormBean;
import com.info.service.StudentService;
import com.info.service.pc.ExcelService;
import com.info.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Autowired
    private StudentService studentService;

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

    @RequestMapping(value = "/outputStu",method = RequestMethod.GET)
    private ReturnData<String> outputStu(@RequestBody ExportStuFormBean param,
                                         HttpServletResponse response){
        ReturnData<String> result = new ReturnData<>();
        String fileName = "student.xls";
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment; filename="+fileName);
            //todo 查询
            //todo HSSFWorkbook workbook = util.exportExcel(StudentInfoDto.class, list, "Student.xls");
            //todo workbook.write(response.getOutputStream());
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
