package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void insertResumeToIndex(int index, Resume r) {

        int indexForInsert = -index - 1;
        int countShift = size - indexForInsert;
        System.arraycopy(storage, indexForInsert, storage, indexForInsert + 1, countShift);
        storage[indexForInsert] = r;
    }

    protected void deleteResumeFromIndex(int index) {
        int countShift = size - index - 1;
        if (countShift > 0) {
            System.arraycopy(storage, index + 1, storage, index, countShift);
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
