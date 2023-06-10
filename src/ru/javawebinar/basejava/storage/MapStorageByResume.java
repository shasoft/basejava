package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Array based storage for Resumes
 */
public class MapStorageByResume extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    protected List<Resume> doAll() {
        return new ArrayList<>(storage.values());
    }

    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    protected boolean isExist(Object resume) {
        return resume!=null;
    }

    protected void doSave(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    protected void doUpdate(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    protected void doDelete(Object resume) {
        storage.remove(((Resume)resume).getUuid());
    }

    protected Resume doGet(Object resume) {
        return (Resume)resume;
    }
}
