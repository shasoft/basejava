package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    public static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected List<Resume> doCopyAll()
    {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected boolean isExist(Integer searchKey) {
        return  searchKey >= 0;
    }

    protected void doSave(Resume r, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r,  searchKey);
        size++;
    }

    protected void doUpdate(Resume r, Integer searchKey) {
        storage[ searchKey] = r;
    }

    protected void doDelete(Integer searchKey) {
        fillDeletedElement( searchKey);
        storage[size - 1] = null;
        size--;
    }

    protected Resume doGet(Integer searchKey) {
        return storage[ searchKey];
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
