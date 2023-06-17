package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.stream.XmlSerializer;

public class XmlPathSerializerStorageTest extends AbstractSerializerStorageTest {
    XmlPathSerializerStorageTest() {
        super( new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(),new XmlSerializer()));
    }
}
