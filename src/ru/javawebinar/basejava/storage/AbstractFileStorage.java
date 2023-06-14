package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Array based storage for Resumes
 */
abstract public class AbstractFileStorage extends AbstractStorage {

     protected File dir;
	
	public AbstractFileStorage(File dir) {
        this.dir = dir;
    }

    protected abstract void doWrite(Resume r, File file);

    protected abstract Resume doRead(File file);

    public int size() {
        String[] list = dir.list();
        if (list == null) {
            return 0;
        }
        return list.length;
    }

    public void clear() {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    protected void doDelete(File searchKey) {
        searchKey.delete();
    }

    protected List<Resume> doAll() {
        File[] files = dir.listFiles();
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
        return new File(dir, uuid);
    }

    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    protected void doSave(Resume r, File searchKey) {
        doWrite(r, searchKey);
    }

    protected void doUpdate(Resume r, File searchKey) {
        doDelete(searchKey);
        doWrite(r, searchKey);
    }

    protected Resume doGet(File searchKey) {
        return doRead(searchKey);
    }
}
