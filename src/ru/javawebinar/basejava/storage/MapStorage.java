package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Array based storage for Resumes
 */
public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    protected void doSave(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    protected void doUpdate(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }
}
