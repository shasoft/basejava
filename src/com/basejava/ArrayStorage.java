package com.basejava;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Переполнение хранилища");
        } else {
            int index = findIndex(r.uuid);
            if (index >= 0) {
                System.out.println("Резюме " + r.uuid + " уже имеется в хранилище");
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.uuid);
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Резюме " + r.uuid + " отсутствует");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Резюме " + uuid + " отсутствует");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            size--;
            if (index != size) {
                storage[index] = storage[size];
            }
            storage[size] = null;
        } else {
            System.out.println("Резюме " + uuid + " отсутствует");
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
