package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ListStorage extends AbstractStorage {
    protected List<Resume> items = new ArrayList<>();

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    protected List<Resume> doAll() {
        return new ArrayList<>(items);
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey != -1;
    }

    protected void doSave(Resume r, Object searchKey) {
        items.add(r);
    }

    protected void doUpdate(Resume r, Object searchKey) {
        items.set((Integer) searchKey, r);
    }

    protected void doDelete(Object searchKey) {
        items.remove((int) (Integer) searchKey);
    }

    protected Resume doGet(Object searchKey) {
        return items.get((Integer) searchKey);
    }
}
