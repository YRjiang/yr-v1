package com.yr.yrv1annotation.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String drivername;
    private static String url;
    private static String user;
    private static String password;

    private static Properties pro = new Properties();

    static{
        try {
            //加载，读取 jdbc.properties 配置的信息
            //pro.load 的作用是把 jdbc.properties 文件中配置的信息，一一 put 到 pro 这个 map 中
            pro.load(ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties"));

            drivername = pro.getProperty("datasource.driverClassName");
            url = pro.getProperty("datasource.url");
            user = pro.getProperty("datasource.username");
            password = pro.getProperty("datasource.password");

            System.out.println("drivername: " + drivername);
            System.out.println("url: " + url);
            System.out.println("user: " + user);
            System.out.println("password: " + password);

            //注册驱动，加载驱动
            Class.forName(drivername);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void closeQuietly(Connection conn){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeQuietly(Statement st){
        try {
            if(st!=null){
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeQuietly(ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeQuietly(Statement st, Connection conn){
        closeQuietly(st);
        closeQuietly(conn);
    }
    public static void closeQuietly(ResultSet rs, Statement st, Connection conn){
        closeQuietly(rs);
        closeQuietly(st);
        closeQuietly(conn);
    }

}
