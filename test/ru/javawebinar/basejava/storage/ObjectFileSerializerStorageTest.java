package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.stream.ObjectSerializer;

public class ObjectFileSerializerStorageTest extends AbstractSerializerStorageTest {
    ObjectFileSerializerStorageTest() {
        super( new FileStorage(STORAGE_DIRECTORY,new ObjectSerializer()));
    }
}
