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

    protected void onSave(Resume r, int index) {
      if (size == STORAGE_LIMIT) {
          throw new StorageException("Storage overflow", r.getUuid());
      }
      insertElement(r, index);
      size++;
    }
    protected void onUpdate(Resume r, int index) {
        storage[index] = r;
    }
    protected void onDelete(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }
    protected Resume onGet(int index) {
        return storage[index];
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
