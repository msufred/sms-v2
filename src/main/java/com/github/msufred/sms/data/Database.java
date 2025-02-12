package com.github.msufred.sms.data;

import com.github.msufred.sms.Settings;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author gemini1991
 */
public final class Database {

    private final Settings settings;
    private Connection connection;

    public Database(Settings settings) throws ClassNotFoundException, SQLException {
        this.settings = settings;
        initConnection();
        createTables();
        updateTables();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Properties props = new Properties();
        props.put("user", settings.getDatabaseSetting("user"));
        props.put("password", settings.getDatabaseSetting("password"));
        connection = DriverManager.getConnection(settings.getDatabaseSetting("url"), props);
    }

    private void createTables() throws SQLException {
        if (connection == null) throw new NullPointerException("Connection is null");
        for (String sql : DBTables.createTableSql()) {
            try (Statement s = connection.createStatement()) {
                s.execute(sql);
            }
        }
    }

    private void updateTables() throws SQLException {
        if (connection == null) throw new NullPointerException("Connection is null");
        for (String sql : DBTables.updatesSql()) {
            try (Statement s = connection.createStatement()) {
                s.execute(sql);
            }
        }
    }

    public ResultSet executeQueryWithResult(String sql) throws SQLException {
        if (connection == null) throw new NullPointerException("Connection is null");
        Statement s = connection.createStatement();
        return s.executeQuery(sql);
    }

    public boolean executeQuery(String sql) throws SQLException {
        if (connection == null) throw new NullPointerException("Connection is null");
        try (Statement s = connection.createStatement()) {
            return s.executeUpdate(sql) > 0;
        }
    }

    public long executeQueryReturnId(String sql) throws SQLException {
        if (connection == null) throw new NullPointerException("Connection is null");
        try (PreparedStatement s = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int rows = s.executeUpdate();
            if (rows == 0) return -1;
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
            else return -1;
        }
    }

    public boolean restore(int id, String column, String table) throws SQLException {
        if (connection == null) throw new NullPointerException("Connection is null");
        String sql = String.format("UPDATE %s SET %s=? WHERE id=?", table, column);
        try (PreparedStatement s = connection.prepareStatement(sql)) {
            s.setString(1, null);
            s.setInt(2, id);
            return s.executeUpdate() > 0;
        }
    }

    public void close() throws SQLException {
        if (connection != null) connection.close();
    }
}
