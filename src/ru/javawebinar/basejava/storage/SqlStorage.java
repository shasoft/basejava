package ru.javawebinar.basejava.storage;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(Properties storage) {
        sqlHelper = new SqlHelper(storage);
    }

    @Override
    public void clear() {
        sqlHelper.exec("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.exec("SELECT * FROM resume r WHERE r.uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.exec("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.exec("INSERT INTO resume (uuid, full_name) VALUES (?,?)", (ps) -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            try {
                ps.executeUpdate();
            } catch (PSQLException e) {
                // ERROR:  23505: duplicate key
                if (Objects.equals(e.getSQLState(), "23505")) {
                    throw new ExistStorageException(r.getUuid());
                }
                throw e;
            }
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.exec("DELETE FROM resume r WHERE r.uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.exec("SELECT * FROM resume r ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> ret = new ArrayList<>();
            while (rs.next()) {
                ret.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return ret;
        });
    }

    @Override
    public int size() {
        return sqlHelper.exec("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
