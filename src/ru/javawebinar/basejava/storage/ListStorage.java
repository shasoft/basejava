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

    public Resume[] getAll() {
        return items.toArray(new Resume[items.size()]);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected void onSave(Resume r, int index) {
        items.add(r);
    }

    protected void onUpdate(Resume r, int index) {
        items.set(index, r);
    }

    protected void onDelete(int index) {
        items.remove(index);
    }

    protected Resume onGet(int index) {
        return items.get(index);
    }
}
