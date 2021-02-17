package com.devilyaos.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 纯jdbc操作
 */
public class OnlyJdbcService {

    public static void main(String[] args) {
        // 连接数据库
        Connection connection = getConnection();
        if (connection == null) {
            System.err.println("===========================");
            System.err.println("获取数据库连接时发生异常");
            System.err.println("===========================");
        }
        PreparedStatement addPst = null;
        PreparedStatement uptPst = null;
        PreparedStatement delPst = null;
        PreparedStatement findPst = null;
        try {
            // CURD+事务示例
            // 下文中不关注返回结果
            connection.setAutoCommit(false);
            // 增
            addPst = connection.prepareStatement("insert into test_table(name, age) values(?, ?)");
            for (int i = 0; i < 10; i++) {
                addPst.setString(1, "test");
                addPst.setInt(2, 20);
                addPst.addBatch();
            }
            int[] resultArr = addPst.executeBatch();
            for (int i : resultArr) {
                System.out.println("结果行" + i + ": " + resultArr[i]);
            }
            connection.commit();
            System.out.println("增完成");
            connection.setAutoCommit(true);
            // 改
            uptPst = connection.prepareStatement("update test_table set name = ? where id > ?");
            uptPst.setString(1, "tt");
            uptPst.setInt(2, 5);
            int count = uptPst.executeUpdate();
            System.out.println("改完成" + count + "条");
            // 删
            delPst = connection.prepareStatement("delete from test_table where id > ?");
            delPst.setInt(1, 5);
            count = delPst.executeUpdate();
            System.out.println("删完成" + count + "条");
            // 查
            findPst = connection.prepareStatement("select name from test_table where id > ?");
            findPst.setInt(1, 0);
            findPst.executeQuery();
            System.out.println("查完成");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("异常了");
        } finally {
            // 释放资源
            releasePrepareStatement(addPst);
            releasePrepareStatement(uptPst);
            releasePrepareStatement(delPst);
            releasePrepareStatement(findPst);
            releaseConnection(connection);
            System.out.println("释放");
        }
    }

    /**
     * 释放连接
     * @param connection 连接实例
     */
    private static void releaseConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 释放PreparedStatement
     * @param pst PreparedStatement实例
     */
    private static void releasePrepareStatement(PreparedStatement pst) {
        try {
            if (pst != null && pst.isClosed()) {
                pst.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 获取数据库连接实例
     * @return 数据库连接实例
     */
    private static Connection getConnection() {
        String url="jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false";
        String user="root";
        String password="testtest";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
