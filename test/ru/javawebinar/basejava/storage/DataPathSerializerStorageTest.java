package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.stream.DataSerializer;

public class DataPathSerializerStorageTest extends AbstractSerializerStorageTest {
    DataPathSerializerStorageTest() {
        super( new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(),new DataSerializer()));
    }
}
