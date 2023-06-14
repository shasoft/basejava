package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.stream.ObjectSerializer;

public class ObjectPathSerializerStorageTest extends AbstractSerializerStorageTest {
    ObjectPathSerializerStorageTest() {
        super( new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(),new ObjectSerializer()));
    }
}
