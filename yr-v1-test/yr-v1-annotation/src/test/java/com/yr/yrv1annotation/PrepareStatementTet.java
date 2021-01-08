package com.yr.yrv1annotation;

import com.yr.yrv1annotation.util.JDBCUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PrepareStatementTet {

    public static void main(String[] args) {

        try {
            Connection connection = JDBCUtils.getConnection();

            String sql = "select * from book o where o.id = ?";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, 1);

            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("name: " + name);
            }

            JDBCUtils.closeQuietly(pst, connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String s = properties.getProperty("datasource.driverClassName");
            System.out.println("s: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void prepareStatementTest() {
        Connection con = null;
        try {
            // 注册JDBC驱动，JAVA1.5以后 JDBC自动加载驱动了  所以这句代码可以省略；
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // 提供地址用户名密码并获得连接对象
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?characterEncoding=utf-8&serverTimezone=GMT%2B8&generateSimpleParameterMetadata=true&rewriteBatchedStatements=true",
                    "root", "Root123@");

            if (!con.isClosed())
                // 连接成功提示
                System.out.println("Successfully connected to MySQL server using TCP/IP...");
            // 有Connection对象创建PreparedStatement
            PreparedStatement ps= con.prepareStatement("select * from book o where o.id > ?");
            // 设置参数,参数索引位置是从1开始（Hibernate参数索引位置是从0开始）
            ps.setInt(1, 2);//过滤itemid大于10的记录
            ResultSet rs = ps.executeQuery();
            // 循环读取结果集的每一行的每一列
            while (rs.next()) {
                // 打印数据
                // System.out.print(rs.getString("id")+";");
                // System.out.print(rs.getString("book_store_id")+";");
                System.out.print(rs.getString("name")+"\n");
                // .out.print(rs.getString("author")+";");
                // System.out.println(rs.getString("price")+";");
                // System.out.print(rs.getString("topic")+";");
                // System.out.print(rs.getString("publish_date")+";");
            }
            // 关闭
            con.close();
            ps.close();
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
