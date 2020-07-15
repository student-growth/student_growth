package com.info.util;

import com.info.annotation.ExcelColumn;
import com.info.dto.ScoreDTO;
import com.info.dto.StudentInfoDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
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


    public <R> List<R> importExcel(MultipartFile file, Class<R> clazz)
            throws Exception{
        Workbook workbook = getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        return  getList(sheet, clazz);
    }

    public  MultipartFile  exportExcel(List<T> clazz)
            throws IOException{
        //todo export
        return null;
    }

    public <R> List<R> getList(Sheet sheet, Class<R> clazz)
            throws Exception{
        List<R> result  = new ArrayList<>();

        int rowNum = sheet.getPhysicalNumberOfRows();
        Row title = sheet.getRow(0);
        int colNum = title.getPhysicalNumberOfCells();
        for(int i=1;i<rowNum;i++){
            Map<String,Object> value= new HashMap<>(rowNum-1);
            Row obj = sheet.getRow(i);
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

}
