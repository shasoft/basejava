package com.basejava;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static public final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    private int findIndex(String uuid, boolean printError) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        if (printError) {
            System.out.println("Резюме " + uuid + " отсутствует");
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length) {
            int index = findIndex(r.uuid, false);
            if (index < 0) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Резюме " + r.uuid + " уже имеется в хранилище");
            }
        } else {
            System.out.println("Переполнение хранилища");
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.uuid, true);
        if (index >= 0) {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid, true);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid, true);
        if (index >= 0) {
            size--;
            if (size > 0) {
                storage[index] = storage[size];
            }
            storage[size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
