package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
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

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }
    protected void doSave(Resume r, Object searchKey) {
      if (size == STORAGE_LIMIT) {
          throw new StorageException("Storage overflow", r.getUuid());
      }
      insertElement(r, (Integer)searchKey);
      size++;
    }
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer)searchKey] = r;
    }
    protected void doDelete(Object searchKey) {
        fillDeletedElement((Integer)searchKey);
        storage[size - 1] = null;
        size--;
    }
    protected Resume doGet(Object searchKey) {
        return storage[(Integer)searchKey];
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
