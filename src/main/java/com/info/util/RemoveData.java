package com.info.util;

import java.sql.*;

/**
 * @author : yue
 * @Date : 2020/7/7 / 23:40
 * <p>
 * 转储新浪云服务拷贝的数据库到本系统数据库
 * <p>
 * 备份密码
 */
public class RemoveData {

    public static void main(String[] args) {
        Connection connection;
    }


//    public static void main(String[] args) {
//        Connection connection;
//
//        String driver = "com.mysql.cj.jdbc.Driver";
//
//        String url = "jdbc:mysql://rm-bp136030w7ps9vy96ko.mysql.rds.aliyuncs.com:3306/student_growth?characterEncoding=UTF-8&serverTimezone=GMT";
//
//        String user = "jiangxian";
//
//        String password = "student_growth";
//
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url, user, password);
//            if (!connection.isClosed()) {
//                System.out.println("Succeed connecting to the Database!");
//            }
//            Statement statement = connection.createStatement();
//
//            String select = "select id ,password from student";
//            PreparedStatement insert= connection.prepareStatement("insert into stu_detail(student_id,ID_card) VALUES (?,?)");
//            ResultSet resultSet = statement.executeQuery(select);
//            while (resultSet.next()){
//                String id =resultSet.getString("id");
//                String ID_card = resultSet.getString("password");
//                insert.setString(1,id);
//                insert.setString(2,ID_card);
//                insert.executeUpdate();
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//    }
}
