package com.info.util;

import com.info.annotation.ExcelColumn;
import com.info.common.sysenum.StateMsg;
import com.info.exception.SystemException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author : yue
 * 2020/7/15 / 0:32
 */

@Component
public class ExcelUtil {
    private  static final  String XLS = ".xls";
    private  static final  String XLSX = ".xlsx";


    /**
     * 导入excel
     * @param file void
     * @param clazz void
     * @param <R> void
     * @return void
     * @throws Exception void
     */
    public <R> List<R> importExcel(MultipartFile file, Class<R> clazz)
            throws Exception{
        Workbook workbook = getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        return  getList(sheet, clazz);
    }


    public <R> HSSFWorkbook exportExcel(Class<R> clazz, List<R> list, String fileName)
            throws Exception{
        if(fileName==null || fileName.isEmpty()){
            throw new SystemException(StateMsg.StateMsg_103);
        }

        HSSFWorkbook workbook =new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        createSheet(sheet,clazz,list);
        return workbook;
    }

    private <T> void createSheet(Sheet sheet,Class<T> clazz,List<T> list)
            throws Exception{
        int colIndex = 0;
        int rowIndex = 0;
        while(rowIndex<=list.size()){
            Row row = sheet.createRow(rowIndex);
            //create title
            if(rowIndex==0){
                for(Field field:clazz.getDeclaredFields()){
                    Cell cell = row.createCell(colIndex++);
                    cell.setCellValue(field.getAnnotation(ExcelColumn.class).name());
                }
                colIndex=0;
            }
            //create table body
            else{
                T item = list.get(rowIndex-1);
                for(Field field:clazz.getDeclaredFields()){
                    Cell cell = row.createCell(colIndex++);
                    String methodName = "get"+field.getName().substring(0,1).toUpperCase()+
                            field.getName().substring(1);
                    Method method = clazz.getMethod(methodName);
                    Object value = method.invoke(item);
                    if(field.getType()==String.class){
                        cell.setCellValue((String)value);
                    }else if(field.getType()==Double.class){
                        cell.setCellValue((Double)value);
                    }
                }
                colIndex=0;
            }
            rowIndex++;
        }
    }


    private <R> List<R> getList(Sheet sheet, Class<R> clazz)
            throws Exception{
        List<R> result  = new ArrayList<>();
        int rowNum = sheet.getLastRowNum();
        Row title = sheet.getRow(0);
        if(title==null){
            throw new SystemException(StateMsg.StateMsg_106);
        }
        int colNum = title.getPhysicalNumberOfCells();
        for(int i=1;i<rowNum;i++){
            Map<String,Object> value= new HashMap<>();
            Row obj = sheet.getRow(i);
            if (obj.getCell(0)==null){
                break;
            }
            for(int j=0;j<colNum;j++){
                value.put(title.getCell(j).getStringCellValue(),
                       parseCell(obj.getCell(j)));
            }
            R object = getObject(clazz, value);
            result.add(object);
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    private Object parseCell(Cell cell){
        if(cell==null){
            return null;
        }
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return cell.getStringCellValue();
        }
    }

    private <R> R getObject(Class<R> clazz, Map<String,Object> table)
            throws Exception{
        R dto = clazz.newInstance();
        for(Field field:clazz.getDeclaredFields()){
            String methodName = "set"+field.getName().substring(0,1).toUpperCase()+
                    field.getName().substring(1);
            Method method = clazz.getMethod(methodName, field.getType());
            Object value = table.get(field.getAnnotation(ExcelColumn.class).name());
            if(value!=null) {
                method.invoke(dto,value);
            }
        }
        return dto;
    }

    private Workbook getWorkbook(MultipartFile file)
        throws IOException{
        String fileName = file.getOriginalFilename();
        if(fileName==null || fileName.isEmpty()){
            throw new IOException("file is EMPTY!");
        }
        if(fileName.endsWith(XLS)){
            return new HSSFWorkbook(file.getInputStream());
        }else if(fileName.endsWith(XLSX)){
            return new XSSFWorkbook(file.getInputStream());
        }else{
            throw new IOException("unknown file format :" + fileName);
        }
    }

//    public static void main(String[] args) {
//        ExcelUtil util = new ExcelUtil();
//        List<StudentInfoDto> list =new ArrayList<>();
//        StudentInfoDto student1 = new StudentInfoDto();
//        student1.setId("123");
//        student1.setName("leesure123");
//        student1.setDepartment("软件学院");
//        student1.setMajor("网络工程");
//        student1.setGrade("2015级");
//        list.add(student1);
//        StudentInfoDto student2 = new StudentInfoDto();
//        student2.setId("345");
//        student2.setName("leesure345");
//        student2.setDepartment("软件学院");
//        student2.setMajor("网络工程");
//        student2.setGrade("2015级");
//        list.add(student2);
//        try{
//            util.exportExcel(StudentInfoDto.class,list,"student.xls");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
