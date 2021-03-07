package com.devilyaos.study;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ChangeDatasourceUtil {

    @Resource
    private DataSource readSource;

    @Resource
    private DataSource writeSource;

    private Connection readConnection;

    private Connection writeConnection;

    public <T> T query(String sql, Class<T> clazz) {
        try {
            return clazz.cast(getReadConnection().prepareStatement(sql).executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int execute(String sql) {
        try {
            return getWriteConnection().prepareStatement(sql).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    private Connection getReadConnection() {
        if (readConnection == null) {
            synchronized (this) {
                if (readConnection == null) {
                    try {
                        return readSource.getConnection();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
        return readConnection;
    }

    private Connection getWriteConnection() {
        if (writeConnection == null) {
            synchronized (this) {
                if (writeConnection == null) {
                    try {
                        return writeSource.getConnection();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
        return writeConnection;
    }
}
