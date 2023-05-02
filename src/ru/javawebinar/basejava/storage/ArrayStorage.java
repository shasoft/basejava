package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void insertResumeToIndex(int index, Resume r) {
        storage[size] = r;
    }

    protected void deleteResumeFromIndex(int index) {
        if (index != size - 1) {
            storage[index] = storage[size - 1];
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}