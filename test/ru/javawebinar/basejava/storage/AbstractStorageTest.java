package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractStorageTest {
    final protected Storage storage;

    protected static final String UUID_1 = "uuid 1";
    protected static final Resume RESUME_1 = new Resume(UUID_1);

    protected static final String UUID_2 = "uuid 2";
    protected static final Resume RESUME_2 = new Resume(UUID_2);

    protected static final String UUID_3 = "uuid 3";
    protected static final Resume RESUME_3 = new Resume(UUID_3);

    protected static final String UUID_4 = "uuid 4";
    protected static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test
    void getAllSorted() {
        assertSize(3);
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actual = storage.getAllSorted();
        assertIterableEquals(actual, expected);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () ->
                storage.delete(UUID_1)
        );
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () ->
                storage.delete("null")
        );
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () ->
                storage.get("null")
        );
    }

    @Test
    public void updateNotExist() {
        assertThrows(NotExistStorageException.class, () ->
                storage.update(new Resume("null"))
        );
    }

    private void assertGet(Resume r) {
        assertSame(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}