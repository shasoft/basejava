package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Based storage for Resumes
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractStorage<T> implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    protected abstract T getSearchKey(String uuid);

    protected abstract boolean isExist(T searchKey);

    protected abstract void doSave(Resume r, T searchKey);

    protected abstract void doUpdate(Resume r, T searchKey);

    protected abstract void doDelete(T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        T searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        T searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        T searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        T searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> items = doCopyAll();
        Collections.sort(items, RESUME_COMPARATOR);
        return items;
    }

    protected T getExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected T getNotExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
