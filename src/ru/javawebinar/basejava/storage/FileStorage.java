package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.stream.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class FileStorage extends AbstractStorage<File> {

    protected final File directory;

    private final Serializer serializer;

    public FileStorage(File directory, Serializer serializer) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " не является директорией");
        }
        this.directory = directory;
        this.serializer = serializer;
    }

    protected File[] getFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        return files;
    }

    public int size() {
        return getFiles().length;
    }

    public void clear() {
        for (File file : getFiles()) {
            doDelete(file);
        }
    }

    protected void doDelete(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("Ошибка удаления файла", searchKey.getName());
        }
    }

    protected List<Resume> doCopyAll() {
        List<Resume> ret = new ArrayList<>();
        for (File file : getFiles()) {
            ret.add(doGet(file));
        }
        return ret;
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
            serializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Ошибка сохранения резюме в файл", searchKey.getName());
        }
    }

    protected Resume doGet(File searchKey) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Ошибка чтения резюме из файла", searchKey.getName());
        }
    }
}
