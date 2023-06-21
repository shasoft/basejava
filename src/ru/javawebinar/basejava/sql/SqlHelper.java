package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <T> T exec(String sql, Process<T> action) {
        //System.out.println("sql: " + sql);
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            return action.process(ps);
        } catch (SQLException e) {
            // ERROR:  23505: duplicate key
            if (Objects.equals(e.getSQLState(), "23505")) {
                throw new ExistStorageException(e.toString());
            }
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
