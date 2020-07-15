package com.info.util;


import com.info.dto.StudentInfoDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/7 / 23:40
 * <p>
 * 转储新浪云服务拷贝的数据库到本系统数据库
 * <p>
 *
 */
public class RemoteData {

    //数据迁移

//    public static void main(String[] args) {
//        ExcelUtil util =new ExcelUtil();
//        //C:\Users\yue\Desktop
//        try{
//            HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(new File("C:/Users/yue/Desktop/student.xls")));
//            book.getSheetAt(0);
//            List<StudentInfoDto> list = util.getList(book.getSheetAt(0), StudentInfoDto.class);
//            System.out.println(list.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
////    public static void main(String[] args) {
////        Connection connection;
//    }
//
//
////    public static void main(String[] args) {
////        Connection connection;
////
////        String driver = "com.mysql.cj.jdbc.Driver";
////
////        String url = "jdbc:mysql://rm-bp136030w7ps9vy96ko.mysql.rds.aliyuncs.com:3306/student_growth?characterEncoding=UTF-8&serverTimezone=GMT";
////
////        String user = "jiangxian";
////
////        String password = "student_growth";
////
////        try {
////            Class.forName(driver);
////            connection = DriverManager.getConnection(url, user, password);
////            if (!connection.isClosed()) {
////                System.out.println("Succeed connecting to the Database!");
////            }
////            Statement statement = connection.createStatement();
////
////            String select = "select id ,password from student";
////            PreparedStatement insert= connection.prepareStatement("insert into stu_detail(student_id,ID_card) VALUES (?,?)");
////            ResultSet resultSet = statement.executeQuery(select);
////            while (resultSet.next()){
////                String id =resultSet.getString("id");
////                String ID_card = resultSet.getString("password");
////                insert.setString(1,id);
////                insert.setString(2,ID_card);
////                insert.executeUpdate();
////            }
////
////        } catch (Exception e) {
////            System.out.println(e.toString());
////        }
////    }
}
