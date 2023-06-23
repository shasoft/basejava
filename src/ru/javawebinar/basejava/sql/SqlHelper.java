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
            throw myThrowException(e);
        }
    }

    public void execute(String sql) {
        execute(sql, (ps) -> {
            ps.execute();
            return null;
        });
    }

    public <T> T executeBatch(SqlTransaction<T> action) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T ret = action.process(conn);
                conn.commit();
                return ret;
            } catch (SQLException e) {
                conn.rollback();
                throw myThrowException(e);
            }
        } catch (SQLException e) {
            throw myThrowException(e);
        }
    }

    private StorageException myThrowException(SQLException e) {
        if (e instanceof PSQLException) {

            // http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(e.toString());
            }
        }
        return new StorageException(e.toString(), null);
    }

    public interface SqlTransaction<T> {
        T process(Connection conn) throws SQLException;
    }

    public interface Process<T> {
        T process(PreparedStatement ps) throws SQLException;
    }

}
