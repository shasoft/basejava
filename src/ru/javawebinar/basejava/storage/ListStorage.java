package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> items = new ArrayList<>();

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    protected List<Resume> doCopyAll() {
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

    protected boolean isExist(Integer searchKey) {
        return (Integer) searchKey != -1;
    }

    protected void doSave(Resume r, Integer searchKey) {
        items.add(r);
    }

    protected void doUpdate(Resume r, Integer searchKey) {
        items.set((Integer) searchKey, r);
    }

    protected void doDelete(Integer searchKey) {
        items.remove((int) (Integer) searchKey);
    }

    protected Resume doGet(Integer searchKey) {
        return items.get((Integer) searchKey);
    }
}
