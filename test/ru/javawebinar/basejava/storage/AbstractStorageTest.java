package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.ResumeTestData.createResume;

class AbstractStorageTest {
    protected static final String UUID_1 = "uuid 1";
    protected static final Resume RESUME_1 = createResume(UUID_1, "Иванов 1");
    protected static final String UUID_2 = "uuid 2";
    protected static final Resume RESUME_2 = createResume(UUID_2, "Иванов 2");
    protected static final String UUID_3 = "uuid 3";
    protected static final Resume RESUME_3 = createResume(UUID_3, "Иванов 3");
    protected static final String UUID_4 = "uuid 4";
    protected static final Resume RESUME_4 = createResume(UUID_4, "Иванов 4");
    final protected Storage storage;

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
        Resume newResume = createResume(UUID_1, "Иванов 1");
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
                storage.update(createResume("null","Пустозвонов Василий"))
        );
    }

    private void assertGet(Resume r) {
        assertSame(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}