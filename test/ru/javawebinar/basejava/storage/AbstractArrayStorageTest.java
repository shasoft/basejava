package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

class AbstractArrayStorageTest {
    final private Storage storage;

    private static final String UUID_1 = "uuid 1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid 2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid 3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid 4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
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
        assertEquals(storage.size(),3);
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(storage.size(),0);
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume,storage.get(UUID_1));
    }

    @Test
    void getAll() {
        assertEquals(3, storage.size());
        Resume[] array = storage.getAll();
        assertSame(RESUME_1, array[0]);
        assertSame(RESUME_2, array[1]);
        assertSame(RESUME_3, array[2]);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertEquals(storage.size(),4);
        assertGet(RESUME_4);
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(storage.size(),2);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void testStorageException() {
        try {
            while(storage.size()<AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Error");
        }
        assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });
    }

    @Test
    public void testNotExistStorageException()  {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete("null");
        });
    }

    @Test
    public void testExistStorageException()  {
        assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_1));
        });
    }

    private void assertGet(Resume r) {
        assertSame(r, storage.get(r.getUuid()));
    }
}