package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

/**
 * Based storage for Resumes
 */
public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);
    protected abstract boolean isExist(Object searchKey);
    protected abstract void doSave(Resume r, Object searchKey);
    protected abstract void doUpdate(Resume r, Object searchKey);
    protected abstract void doDelete(Object searchKey);
    protected abstract Resume doGet(Object searchKey);

    public void update(Resume r) {
        Object searchKey =  getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey =  getExistingSearchKey(uuid);
        doDelete(searchKey);
    }
    public Resume get(String uuid) {
        Object searchKey =  getExistingSearchKey(uuid);
        return doGet(searchKey);
    }
    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
    protected Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
