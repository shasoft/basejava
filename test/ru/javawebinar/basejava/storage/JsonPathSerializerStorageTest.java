package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.stream.JsonSerializer;

public class JsonPathSerializerStorageTest extends AbstractSerializerStorageTest {
    JsonPathSerializerStorageTest() {
        super( new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(),new JsonSerializer()));
    }
}
