package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <T> T exec(String sql, Process<T> action, String... args) {
        /*
        System.out.println("sql: " + sql);
        for (String arg : args) {
            System.out.println(" \t\t" + arg);
        }
        //*/

        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setString(i + 1, args[i]);
            }
            return action.process(ps);
        } catch (SQLException e) {
            throw new StorageException(e.toString(), null);
        }
    }

    public void exec(String sql, String... args) {
        exec(sql, (ps) -> {
            ps.execute();
            return null;
        }, args);
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
