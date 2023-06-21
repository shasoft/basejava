package ru.javawebinar.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.exception.ExistStorageException;
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

    public <T> T execute(String sql, Process<T> action) {
        //System.out.println("sql: " + sql);
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            return action.process(ps);
        } catch (SQLException e) {
            myThrowException(e);
            return null;
        }
    }

    public void execute(String sql) {
        execute(sql, (ps) -> {
            ps.execute();
            return null;
        });
    }

    public <T> T executeBatch(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                myThrowException(e);
            }
        } catch (SQLException e) {
            myThrowException(e);
        }
        return null;
    }

    private void myThrowException(SQLException e) {
        if (e instanceof PSQLException) {

            // http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e.toString());
            }
        }
        throw new StorageException(e.toString(), null);
    }

    public interface SqlTransaction<T> {
        T execute(Connection conn) throws SQLException;
    }

    public interface Process<T> {
        T process(PreparedStatement ps) throws SQLException;
    }

}
