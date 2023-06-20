package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.exec("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.exec("SELECT * FROM resume r WHERE r.uuid = ?", (ps) -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        }, uuid);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.exec("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        }, r.getFullName(), r.getUuid());
    }

    @Override
    public void save(Resume r) {
        sqlHelper.exec("INSERT INTO resume (uuid, full_name) VALUES (?,?)", r.getUuid(), r.getFullName());
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.exec("DELETE FROM resume r WHERE r.uuid =?", (ps) -> {
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        }, uuid);
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
