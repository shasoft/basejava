package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
abstract public class AbstractFileStorage extends AbstractStorage {

    protected final File directory;

    public AbstractFileStorage(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " не является директорией");
        }
        this.directory = directory;
    }

    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Ошибка получения списка файлов", directory.getName());
        }
        return list.length;
    }

    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Ошибка получения списка файлов", directory.getName());
        }
        for (File file : files) {
            doDelete(file);
        }
    }

    protected void doDelete(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("Ошибка удаления файла", searchKey.getName());
        }
    }

    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if (files != null) {
            List<Resume> ret = new ArrayList<>(files.length);
            for (File file : files) {
                ret.add(doGet(file));
            }
            return ret;
        }
        return new ArrayList<>(0);
    }

    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    protected void doSave(Resume r, File searchKey) {
        try {
            searchKey.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Ошибка создания файла", searchKey.getName());
        }
        doUpdate(r, searchKey);
    }

    protected void doUpdate(Resume r, File searchKey) {
        try {
            doWrite(r, searchKey);
        } catch (IOException e) {
            throw new StorageException("Ошибка сохранения резюме в файл", searchKey.getName());
        }
    }

    protected Resume doGet(File searchKey) {
        try {
            return doRead(searchKey);
        } catch (IOException e) {
            throw new StorageException("Ошибка чтения резюме из файла", searchKey.getName());
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
