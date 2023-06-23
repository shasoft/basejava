package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute(
                "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid = ? ", ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContactToResume(rs, r);
                    } while (rs.next());

                    return r;
                });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.executeBatch(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContactsFromDb(r.getUuid());
            insertContactsToDb(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.executeBatch(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContactsToDb(conn, r);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
        deleteContactsFromDb(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executeBatch((conn) -> {
                    Map<String, Resume> resumes = new LinkedHashMap<>();
                    PreparedStatement ps;
                    ResultSet rs;

                    ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                    }

                    ps = conn.prepareStatement("SELECT * FROM contact");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        addContactToResume(rs, resumes.get(rs.getString("resume_uuid")));
                    }

                    return new ArrayList<>(resumes.values());
                }
        );
        /*
        return sqlHelper.execute(
                "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid" +
                        "  ORDER BY full_name, uuid", ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Resume> resumes = new LinkedHashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        if (!resumes.containsKey(uuid)) {
                            resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                        }
                        addContactToResume(rs, resumes.get(uuid));
                    }
                    return new ArrayList<>(resumes.values());
                });
         */
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void deleteContactsFromDb(String uuid) {
        sqlHelper.execute("DELETE FROM contact r WHERE r.resume_uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            ps.executeUpdate();
            return null;
        });
    }

    private void insertContactsToDb(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContactToResume(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        if (type != null) {
            r.addContact(
                    ContactType.valueOf(type),
                    rs.getString("value")
            );
        }
    }
}
