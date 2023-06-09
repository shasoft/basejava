package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * Array based storage for Resumes
 */
public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> items = new HashMap<>();

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public Resume[] getAll() {
        return items.values().toArray(new Resume[0]);
    }

    protected String getSearchKey(String uuid) {
       return uuid;
    }
    protected boolean isExist(Object searchKey) {
        return items.containsKey((String)searchKey);
    }
    protected void doSave(Resume r, Object searchKey) {
        items.put((String)searchKey,r);
    }
    protected void doUpdate(Resume r, Object searchKey) {
        items.put((String)searchKey,r);
    }
    protected void doDelete(Object searchKey) {
        items.remove((String)searchKey);
    }
    protected Resume doGet(Object searchKey) {
        return items.get((String)searchKey);
    }
}
