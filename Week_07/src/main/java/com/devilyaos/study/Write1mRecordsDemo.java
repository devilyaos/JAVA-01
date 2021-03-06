package com.devilyaos.study;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Write1mRecordsDemo {

    // 约17s左右, 如果需要提高插入性能, 需要不断调整每次插入的数据量, 拼接成的sql根据values数量不同执行时长也不同
    // 当sql太长, 会导致执行失败或数据库宕机
    // 可以设置多线程, id使用程序生成, 通过设置不同步长或起始点进行多线程插入提高效率
    public static void main(String[] args) {
        Connection connection = getHikariConnection();
        PreparedStatement addPst = null;
        PreparedStatement countPst = null;
        try {
            assert connection != null;
            addPst = connection.prepareStatement("insert into test_table(name, age) values(?, ?)");
            long startTime = System.currentTimeMillis();
            for (int i = 1; i < 101; i++) {
                System.out.println("第 " + i + " / 100批");
                for (int j = 1; j < 10001;j++) {
                    addPst.setString(1, String.format("test%d", i % 10000));
                    addPst.setInt(2, i);
                    addPst.addBatch();
                }
                addPst.executeBatch();
            }
            long costTime = System.currentTimeMillis() - startTime;
            System.out.println("共计耗时 " + costTime + " ms");
            countPst = connection.prepareStatement("select count(1) as co from test_table");
            ResultSet rs = countPst.executeQuery();
            long count = 0;
            while (rs.next()) {
                count = rs.getLong("co");
            }
            System.out.println("共计 " + count + " 条记录");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (addPst != null && !addPst.isClosed()) {
                    addPst.close();
                }
                if (countPst != null && !countPst.isClosed()) {
                    countPst.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库连接池配置
     * @return 连接池中的链接
     */
    private static Connection getHikariConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
        config.setUsername("root");
        config.setPassword("testtest");
        // 开启批量插入重写优化
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("characterEncoding", "utf8");
        config.addDataSourceProperty("useSSL", "false");
        HikariDataSource dataSource = new HikariDataSource(config);
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
