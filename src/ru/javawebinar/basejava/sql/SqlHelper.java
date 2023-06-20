package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(Properties storage) {
        connectionFactory = () -> DriverManager.getConnection(
                storage.getProperty("db.url"),
                storage.getProperty("db.user"),
                storage.getProperty("db.password")
        );
    }

    public <T> T exec(String sql, Process<T> action) {
        //System.out.println("sql: " + sql);
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            return action.process(ps);
        } catch (SQLException e) {
            throw new StorageException(e.toString(), null);
        }
    }

    public void exec(String sql) {
        exec(sql, (ps) -> {
            ps.execute();
            return null;
        });
    }

    public interface Process<T> {
        T process(PreparedStatement ps) throws SQLException;
    }
}
